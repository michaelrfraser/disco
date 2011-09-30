/*
 *   Copyright 2011 Calytrix Technologies
 *
 *   This file is part of Calytrix Disco.
 *
 *   Calytrix Disco is free software; you can redistribute it and/or modify
 *   it under the terms of the Common Developer and Distribution License (CDDL) 
 *   as published by Sun Microsystems. For more information see the LICENSE file.
 *   
 *   Use of this software is strictly AT YOUR OWN RISK!!!
 *   If something bad happens you do not have permission to come crying to me.
 *   (that goes for your lawyer as well)
 *
 */
package com.calytrix.disco.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.calytrix.disco.DiscoException;
import com.calytrix.disco.config.DiscoProperties;
import com.calytrix.disco.event.IPDUListener;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * This class is responsible for connecting to the local network and queuing up received PDUs
 * for later processing.
 */
public class PDUReader
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int PDU_MAX_SIZE = 1024 * 1024 * PDUHeader.PDU_HEADER_LENGTH;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private Logger logger;
	private DiscoProperties properties;
	
	private DatagramSocket socket;
	private int multicastTTL;
	private int multicastTrafficClass;
	private Thread receiveThread;
	private Thread dispatchThread;
	private PDUCodec m_pduCodec;
	private BlockingQueue<PDU> dispatchQueue;
	private Set<IPDUListener> listenerSet;
	private Lock listenerLock;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public PDUReader( DiscoProperties properties )
	{
		// basic initialization
		this.logger = properties.getLogger( "disco.network" );
		this.properties = properties;
		
		this.socket = null;
		this.receiveThread = null;
		this.dispatchQueue = new LinkedBlockingQueue<PDU>( Integer.MAX_VALUE );
		this.listenerSet = new HashSet<IPDUListener>();
		this.listenerLock = new ReentrantLock();
		this.m_pduCodec = new PDUCodec();
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Begins receiving PDUs from the network and dispatching them to registered
	 * IPDUListeners (see {@link #addPDUListener(IPDUListener)})
	 * 
	 * @throws DiscoException thrown if the PDUReader could not be started on
	 * due to a network configuration error.
	 */
	public void start() throws DiscoException
	{
		logger.debug( "Starting PDUReader" );
		
		if( socket == null )
		{
			InetAddress disAddress = properties.getNetworkAddress();
			int disPort = properties.getNetworkPort();
			NetworkInterface iface = properties.getNetworkInterface();
			
			try
			{
				// How we initialise the socket depends on the address type
				// that is configured
				if( disAddress.isMulticastAddress() )
					socket = socketStartMulticast( disAddress, disPort, iface );
				else
					socket = socketStartBroadcast( disAddress, disPort, iface );
				
				logger.info( "PDUReader started!" );
			}
			catch( IOException ioe )
			{
				logger.error( "Could not start PDUReader", ioe );
				throw new DiscoException( ioe );
			}
						
			Runnable receiver = new PDUReceiver();
			receiveThread = new Thread( receiver, "DiscoPDUReceiver" );
			receiveThread.start();
			
			Runnable dispatcher = new PDUDispatcher();
			dispatchThread = new Thread( dispatcher, "DiscoPDUDispatcher" );
			dispatchThread.start();
		}
		else
		{
			// Already started!
			logger.warn( "WARN: Unable to start PDUReader as it has already been started" );
		}
	}
	
	/**
	 * Stops receiving PDUs from the network.
	 */
	public void stop()
	{
		logger.debug( "Stopping PDUReader" );
		
		if( socket != null )
		{
			socket.close();
			socket = null;
			
			try
			{
				logger.trace( "Stopping DiscoPDUReceiver thread" );
				
				// Interrupt the receiver and wait for it to stop
				receiveThread.interrupt();
				receiveThread.join();
				
				logger.debug( "Stopped DiscoPDUReceiver thread!" );
			}
			catch( InterruptedException ie )
			{
				logger.warn( "WARN: Unable to stop the DiscoPDUReceiver thread", ie );
			}
			
			try
			{
				logger.trace( "Stopping DiscoPDUDispatcher thread" );
				
				// Interrupt the PDU dispatcher and wait for it to stop
				dispatchThread.interrupt();
				dispatchThread.join();
				
				logger.debug( "Stopped DiscoPDUDispatcher thread!" );
			}
			catch( InterruptedException ie )
			{
				logger.warn( "Unable to stop the DiscoPDUDispatcher thread", ie );
			}
		}
		else
		{
			// no-op, already stopped
			
		}
	}
	
	/**
	 * Registers the provided IPDUListener with this class to receive 
	 * notifications when PDUs are received off the network
	 * 
	 * @param listener The IPDUListener to register with this class
	 */
	public void addPDUListener( IPDUListener listener )
	{
		listenerLock.lock();
		listenerSet.add( listener );
		listenerLock.unlock();
	}
	
	/**
	 * Unregisters the provided IPDUListener with this class so that it no 
	 * longer receives notifications when PDUs are received off the network
	 * 
	 * @param listener The IPDUListener to register with this class
	 */
	public void removePDUListener( IPDUListener listener )
	{
		listenerLock.lock();
		listenerSet.remove( listener );
		listenerLock.unlock();
	}
	
	/**
	 * Starts the internal socket in multicast mode
	 * 
	 * @param address The multicast address to listen on
	 * @param port The multicast port to listen on
	 * @param iface The network interface to communicate through
	 * 
	 * @return A DatagramSocket representing the multicast socket connected
	 * to the desired address
	 * 
	 * @throws IOException thrown if there was an error connecting the socket
	 */
	private DatagramSocket socketStartMulticast( InetAddress address, 
	                                             int port, 
	                                             NetworkInterface nic ) throws IOException
	{
		MulticastSocket asMulticast = new MulticastSocket( port );
		asMulticast.setTimeToLive( multicastTTL );
		asMulticast.setTrafficClass( multicastTrafficClass );
		
		SocketAddress disAddress = new InetSocketAddress( address, port );
		asMulticast.joinGroup( disAddress, nic );
		
		return asMulticast;	
	}
	
	/**
	 * Starts the internal socket in broadcast mode
	 * 
	 * @param address The broadcast address to listen on
	 * @param port The broadcast port to listen on
	 * @param iface The network interface to communicate through
	 * 
	 * @return A DatagramSocket representing the broadcast socket connected
	 * to the desired address
	 * 
	 * @throws IOException thrown if there was an error connecting the socket
	 */
	private DatagramSocket socketStartBroadcast( InetAddress address,
	                                             int port, 
	                                             NetworkInterface iface ) throws IOException
	{
		throw new IllegalStateException( "Not currently supported" );
	}

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	
	/**
	 * PDU Receiver runnable. Receives PDU packets off the socket, deserialises
	 * them into objects and places them onto the dispatch queue.   
	 */
	private class PDUReceiver implements Runnable
	{
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run()
		{
			logger.info( "DiscoPDUReceiver thread started!" );
			boolean keepReceiving = true;
			
			// Initialise the buffer and datagram packet
			byte[] rawBytes = new byte[ PDU_MAX_SIZE ];
			ByteArrayInputStream bais = new ByteArrayInputStream( rawBytes );
			DISInputStream dis = new DISInputStream( bais );
			DatagramPacket packet = new DatagramPacket( rawBytes, PDU_MAX_SIZE );
			
			while( keepReceiving )
			{
				try
				{
					dis.reset();

					// Receive into DatagramPacket
					socket.receive( packet );
					logger.trace( "Read " + packet.getData().length + " bytes from DIS socket" );

					// Deserialise the PDU and place it on the dispatch queue
					PDU pdu = m_pduCodec.readPDU( dis );
					if( pdu != null )
					{
						logger.trace( "PDU deserialised successfully, adding to dispatch queue" );
						dispatchQueue.put( pdu );
					}
				}
				catch( InterruptedException ie )
				{
					// Interrupted, so stop receiving and fall through
					keepReceiving = false;
					logger.debug( "DiscoPDUReceiver shutting down due to interrupt" );
				}
				catch( IOException ioe )
				{
					// IOException occured, so stop receiving and fall through
					keepReceiving = false;
					logger.debug( "DiscoPDUReceiver shutting down due to socket closure" );
				}
			}
			
			logger.info( "DiscoPDUReceiver thread shutdown!" );
		}
	}
	
	/**
	 * PDU Dispatcher runnable. Takes PDU objects off the dispatch queue and
	 * notifies IPDUListeners that they have been received
	 */
	private class PDUDispatcher implements Runnable
	{
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run()
		{
			logger.info( "DiscoPDUDispatcher thread started!" );
			
			boolean running = true;
			
			// Process while running, or if the queue has content
			while( running || dispatchQueue.size() > 0 )
			{
				try
				{
					// Get the next notification from the notification queue
					PDU pdu = dispatchQueue.take();
					logger.trace( "PDU taken from dispatch queue, notifying listeners" );

					// Broadcast to all listeners
					listenerLock.lock();
					for( IPDUListener listener : listenerSet )
						listener.pduReceived( pdu );
					listenerLock.unlock();
				}
				catch( InterruptedException ie )
				{
					// Interrupted, so stop running
					running = false;
					logger.debug( "DiscoPDUDispatcher draining due to interrupt" );
				}
			}
		}
	}
}
