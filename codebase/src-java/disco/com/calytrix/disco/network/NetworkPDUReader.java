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
import java.net.NetworkInterface;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.calytrix.disco.DiscoException;
import com.calytrix.disco.config.DiscoProperties;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.PDUFactory;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.service.IPDUFilter;
import com.calytrix.disco.service.IPDUListener;
import com.calytrix.disco.service.IPDUReaderService;
import com.calytrix.disco.util.DISSizes;

/**
 * This class is responsible for connecting to the local network and receiving PDU data from it.
 * Received data is converted to in memory PDU object instances and dispatched to 
 * <code>IPDUListener</code>s who have registered with this <code>NetworkPDUReader</code>.
 */
public class NetworkPDUReader implements IPDUReaderService
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
		
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private Logger logger;
	private DiscoProperties properties;
	
	private DatagramSocket socket;
	private Thread receiveThread;
	private Thread dispatchThread;
	private PDUFactory m_pduFactory;
	private BlockingQueue<PDU> dispatchQueue;
	private Set<IPDUListener> listenerSet;
	private Lock listenerLock;
	private Set<IPDUFilter> receiveFilters;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type NetworkPDUReader with provided configuration properties
	 * 
	 * @param properties The property set to configure this PDUReader with
	 */
	public NetworkPDUReader( DiscoProperties properties )
	{
		// basic initialization
		this.logger = properties.getLogger( "disco.pduReader" );
		this.properties = properties;
		
		this.socket = null;
		this.receiveThread = null;
		this.dispatchQueue = new LinkedBlockingQueue<PDU>( Integer.MAX_VALUE );
		this.listenerSet = new HashSet<IPDUListener>();
		this.listenerLock = new ReentrantLock();
		this.m_pduFactory = new PDUFactory( properties );
		this.receiveFilters = new HashSet<IPDUFilter>();
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Specifies whether this PDUReader is currently receiving PDUs off the network and dispatching
	 * notification events to its listeners. 
	 * 
	 * @return true if this PDUReader is currently receiving and dispatching, otherwise false
	 */
	@Override
	public synchronized boolean isStarted()
	{
		return socket != null;
	}
	
	/**
	 * Begins receiving PDUs from the network and dispatching them to registered
	 * IPDUListeners (see {@link #addPDUListener(IPDUListener)})
	 * 
	 * @throws DiscoException thrown if the PDUReader could not be started on
	 * due to a network configuration error.
	 */
	@Override
	public synchronized void start() throws DiscoException
	{
		logger.debug( "ATTEMPT: Starting PDUReader" );
		
		if( !isStarted() )
		{
			InetAddress disAddress = properties.getNetworkAddress();
			int disPort = properties.getNetworkPort();
			InetSocketAddress hostAddress = new InetSocketAddress( disAddress, disPort );
			NetworkInterface iface = properties.getNetworkInterface();
			
			try
			{
				// How we initialise the socket depends on the address type
				// that is configured
				if( disAddress.isMulticastAddress() )
					socket = NetworkUtilities.socketStartMulticast( hostAddress, iface );
				else
					socket = NetworkUtilities.socketStartBroadcast( hostAddress, iface );
				
				logger.info( "SUCCESS: PDUReader started!" );
			}
			catch( IOException ioe )
			{
				logger.error( "FAIL Could not start PDUReader", ioe );
				throw new DiscoException( ioe );
			}
			
			// Start the PDU Receiver thread
			Runnable receiver = new PDUReceiver();
			receiveThread = new Thread( receiver, "DiscoPDUReceiver" );
			receiveThread.start();
			
			// Start the PDU Dispatcher thread
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
	@Override
	public synchronized void stop()
	{
		logger.debug( "Stopping PDUReader" );
		
		if( isStarted() )
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
	@Override
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
	@Override
	public void removePDUListener( IPDUListener listener )
	{
		listenerLock.lock();
		listenerSet.remove( listener );
		listenerLock.unlock();
	}
	
	/**
	 * Adds the specified IPDUFilter to this PDUReader's filter set. The logic contained within 
	 * the specified IPDUFilter will be called upon to decide whether PDUs received from the network
	 * should be processed further or discarded.<br/>
	 * <br/>
	 * <b>Note:</b> IPDUFilters may only be added while the PDUReader is stopped.
	 * 
	 * @param filter The IPDUFilter to add to this PDUReader's filter set
	 * 
	 * @throws IllegalStateException Thrown if addPDUFilter() was called while the PDUReader was
	 * started
	 */
	@Override
	public void addPDUFilter( IPDUFilter filter )
	{
		if( !isStarted() )
		{
			receiveFilters.add( filter );
		}
		else
		{
			String message = "Can not modify PDU filter set while the PDUReader is started";
			throw new IllegalStateException( message );
		}
		
	}
	
	
	/**
	 * Removes the specified IPDUFilter to this PDUReader's filter set.<br/>
	 * <br/>
	 * <b>Note:</b> IPDUFilters may only be removed while the PDUReader is stopped.
	 * 
	 * @param filter The IPDUFilter to remove from this PDUReader's filter set
	 * 
	 * @throws IllegalStateException Thrown if removePDUFilter() was called while the PDUReader was
	 * started
	 */
	@Override
	public void removePDUFilter( IPDUFilter filter )
	{
		if( !isStarted() )
		{
			receiveFilters.remove( filter );
		}
		else
		{
			String message = "Can not modify PDU filter set while the PDUReader is started";
			throw new IllegalStateException( message );
		}
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
			logger.info( "INFO: DiscoPDUReceiver thread started!" );
			boolean keepReceiving = true;
			
			// Initialise the buffer and datagram packet
			byte[] rawBytes = new byte[ DISSizes.PDU_MAX_SIZE ];
			ByteArrayInputStream bais = new ByteArrayInputStream( rawBytes );
			DISInputStream dis = new DISInputStream( bais );
			DatagramPacket packet = new DatagramPacket( rawBytes, DISSizes.PDU_MAX_SIZE );
			
			while( keepReceiving )
			{
				try
				{
					dis.reset();

					// Receive into DatagramPacket
					socket.receive( packet );

					// FILTER PHASE ONE: A packet has been received, allow the filters to 
					// accept/deny it based on information at the DatagramPacket level
					boolean processHeader = true;
					for( IPDUFilter filter : receiveFilters )
						processHeader &= filter.onPacketReceived( packet );
					
					if( processHeader )
					{
						PDUHeader header = m_pduFactory.createPDUHeader( dis );
						
						// FILTER PHASE TWO: The PDUHeader has been deserialised from the received
						// datagram packet. Allow the filters to accept/deny it based on information
						// at the header level
						boolean processContents = true;
						for( IPDUFilter filter : receiveFilters )
							processContents &= filter.onPDUHeaderReceived( header );
						
						if( processContents )
						{
    						// Deserialise the PDU
    						PDU pdu = m_pduFactory.createPDU( header );
    						pdu.readContent( dis );
    						
    						if( pdu != null )
    						{
    							// FILTER PHASE THREE: The full PDU has been deserialised from the 
    							// received datagram packet. Allow the filters to accept/deny it 
    							// based on information at the PDU level
    							boolean dispatchPDU = true;
    							for( IPDUFilter filter : receiveFilters )
    								dispatchPDU &= filter.onPDUReceived( pdu );
    							
    							if( dispatchPDU )
    								dispatchQueue.put( pdu );
    						}
						}
					}
				}
				catch( InterruptedException ie )
				{
					// Interrupted, so stop receiving and fall through
					keepReceiving = false;
					logger.debug( "INFO: DiscoPDUReceiver shutting down due to interrupt" );
				}
				catch( IOException ioe )
				{
					// IOException occured, so stop receiving and fall through
					keepReceiving = false;
					logger.debug( "INFO: DiscoPDUReceiver shutting down due to socket closure" );
				}
			}
			
			logger.info( "INFO: DiscoPDUReceiver thread shutdown!" );
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
			logger.info( "INFO: DiscoPDUDispatcher thread started!" );
			
			boolean running = true;
			
			// Process while running, or if the queue has content
			while( running || dispatchQueue.size() > 0 )
			{
				try
				{
					// Get the next notification from the notification queue
					PDU pdu = dispatchQueue.take();
					logger.trace( "INFO: PDU taken from dispatch queue, notifying listeners" );

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
					logger.debug( "INFO: DiscoPDUDispatcher draining due to interrupt" );
				}
			}
		}
	}
}
