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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;

import org.apache.log4j.Logger;

import com.calytrix.disco.DiscoException;
import com.calytrix.disco.config.DiscoProperties;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.service.IPDUWriterService;

/**
 * This class is responsible for sending PDUs to a network endpoint
 */
public class NetworkPDUWriter implements IPDUWriterService
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
	private InetSocketAddress hostAddress;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type NetworkPDUWriter with provided configuration properties
	 * 
	 * @param properties The property set to configure this NetworkPDUWriter with
	 */
	public NetworkPDUWriter( DiscoProperties properties )
	{
		// basic initialization
		this.logger = properties.getLogger( "disco.pduWriter" );
		this.properties = properties;
		
		this.socket = null;
		this.hostAddress = null;
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean isStarted()
	{
		return socket != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void start() throws DiscoException
	{
		logger.debug( "ATTEMPT: Starting NetworkPDUWriter" );
		
		if( !isStarted() )
		{
			InetAddress disAddress = properties.getNetworkAddress();
			int disPort = properties.getNetworkPort();
			hostAddress = new InetSocketAddress( disAddress, disPort );
			NetworkInterface iface = properties.getNetworkInterface();
						
			try
			{
				// How we initialise the socket depends on the address type that is configured
				if( disAddress.isMulticastAddress() )
					socket = NetworkUtilities.socketStartMulticast( hostAddress, iface );
				else
					socket = NetworkUtilities.socketStartBroadcast( hostAddress, iface );
				
				logger.info( "SUCCESS: NetworkPDUWriter started!" );
			}
			catch( IOException ioe )
			{
				logger.error( "FAIL: Could not start NetworkPDUWriter", ioe );
				throw new DiscoException( ioe );
			}
		}
		else
		{
			// Already started!
			logger.warn( "WARN: Unable to start NetworkPDUWriter as it has already been started" );
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void stop()
	{
		logger.debug( "ATTEMPT: Stopping NetworkPDUWriter" );
		
		if( isStarted() )
		{
			socket.close();
			socket = null;
			hostAddress = null;
			
			logger.info( "SUCCESS: Stopped NetworkPDUWriter" );
		}
		else
		{
			// no-op, already stopped
			logger.warn( "WARN: Unable to stop NetworkPDUWriter as it has not been started" );
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void write( PDU pdu ) throws DiscoException
	{
		if( isStarted() )
		{
			// Create a DISOutputStream to write to
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		DISOutputStream dos = new DISOutputStream( baos );
    		
    		try
    		{
    			// Firstly write the PDU header to the stream
    			pdu.writeHeader( dos );
    			
    			// Now write the PDU content to the stream
    			pdu.writeContent( dos );
    			
    			// Get the underlying byte array and wrap it in a Datagram packet
    			byte[] payload = baos.toByteArray();
    			DatagramPacket packet = 
    				new DatagramPacket( payload, 0, payload.length, hostAddress );
    			
    			// Send the packet
    			socket.send( packet );
    		}
    		catch ( IOException ioe )
    		{
    			throw new DiscoException( ioe );
    		}
		}
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
