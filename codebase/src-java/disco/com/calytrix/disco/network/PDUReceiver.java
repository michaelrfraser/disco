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

import java.io.IOException;
import java.net.MulticastSocket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import com.calytrix.disco.DiscoException;
import com.calytrix.disco.config.DiscoProperties;

/**
 * This class is responsible for connecting to the local network and queuing up received PDUs
 * for later processing.
 */
public class PDUReceiver
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private Logger logger;
	
	private Queue<byte[]> receivedPackets;

	// networking variables
	private MulticastSocket multicastSocket;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public PDUReceiver( DiscoProperties properties ) throws DiscoException
	{
		// basic initialization
		this.logger = DiscoProperties.getLogger( "disco.network" );
		this.receivedPackets = new LinkedBlockingQueue<byte[]>();
		
		// configure networking
		this.configure( properties );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	private void configure( DiscoProperties properties ) throws DiscoException
	{
		// initialize the socket
		try
		{
			this.multicastSocket = new MulticastSocket( properties.getFullNetworkAddress() );
		}
		catch( IOException ioex )
		{
			throw new DiscoException( ioex );
		}
	}

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
