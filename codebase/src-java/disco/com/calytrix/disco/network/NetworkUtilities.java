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
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

/**
 * A utility class that provides common Network functionality
 */
public class NetworkUtilities
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Starts the internal socket in multicast mode
	 * 
	 * @param hostAddress The multicast address to listen on
	 * @param iface The network interface to communicate through
	 * 
	 * @return A DatagramSocket representing the multicast socket connected
	 * to the desired address
	 * 
	 * @throws IOException thrown if there was an error connecting the socket
	 */
	public static DatagramSocket socketStartMulticast( InetSocketAddress hostAddress,
	                                                   NetworkInterface nic ) throws IOException
	{
		MulticastSocket asMulticast = new MulticastSocket( hostAddress.getPort() );
//		asMulticast.setTimeToLive( multicastTTL );
//		asMulticast.setTrafficClass( multicastTrafficClass );
		
		asMulticast.joinGroup( hostAddress, nic );
		
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
	public static DatagramSocket socketStartBroadcast( InetSocketAddress hostAddress,
	                                                   NetworkInterface iface ) throws IOException
	{
		throw new IllegalStateException( "Not currently supported" );
	}
}
