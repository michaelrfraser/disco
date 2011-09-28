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
package com.calytrix.disco.config;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.calytrix.disco.DiscoException;

/**
 * Central storage location for all Disco-related properties. This class consumes either the 
 * system properties, or a user-provided property set and provides convenience methods for
 * extracting the various disco-related property values.
 * <p/>
 * The keys and defaults for the various properties are provided as static variables.
 */
public class DiscoProperties
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	/** This is set to false, but if we're running a unit test, it'll be set to true, and if
	    that is the case, there is a bunch of stuff we won't do to make testing easier */
	public static boolean IS_UNIT_TEST = false;

	/** Logging properties should only be once. This variable helps us track that */ 
	private static boolean IS_LOGGING_CONFIGURED = false;

	///////////////// System Property Keys /////////////////
	// version properties
	public static final String PROP_MAJOR_VERSION            = "app.version";
	public static final String PROP_MAJOR_VERSION_DEFAULT    = "0.0.0";
	public static final String PROP_BUILD_NUMBER             = "svn.revision";
	public static final String PROP_BUILD_NUMBER_DEFAULT     = "0";

	// logging properties
	public static final String PROP_LOG_LEVEL                = "disco.loglevel";
	public static final String PROP_LOG_LEVEL_DEFAULT        = "OFF";
	/** if value of property is "off", "none", "false" or "disabled", no log file will be used */
	public static final String PROP_LOG_FILE                 = "disco.logfile";
	public static final String PROP_LOG_FILE_DEFAULT         = "disabled";
	
	// networking properties
	public static final String PROP_NETWORK_ADDRESS          = "disco.network.address";
	public static final String PROP_NETWORK_ADDRESS_DEFAULT  = "226.0.1.1";
	public static final String PROP_NETWORK_PORT             = "disco.network.port";
	public static final String PROP_NETWORK_PORT_DEFAULT     = "3000";
	public static final String PROP_NETWORK_IFACE		 	 = "disco.network.iface";
	public static final String PROP_NETWORK_IFACE_DEFAULT	 = "localhost";

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private Properties properties;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Creates a new Disco Properties set by wrapping around the general system properties.
	 */
	public DiscoProperties()
	{
		this( System.getProperties() );
	}

	/**
	 * Creates a new Disco Properties set by wrapping around the provided property set
	 */
	public DiscoProperties( Properties properties )
	{
		this.properties = properties;
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////// Logging /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	/**
	 * Gets the logger with the provided name, ensuring that it has been appropriately
	 * configured according to the stored configuration properteis.
	 */
	public Logger getLogger( String name )
	{
		// if we haven't yet, do the basic logging configuration initialization
		if( IS_LOGGING_CONFIGURED == false )
		{
			String stringLevel = properties.getProperty( PROP_LOG_LEVEL, PROP_LOG_LEVEL_DEFAULT );
			Level loglevel = Log4jUtilities.validateLevel( stringLevel );
			if( loglevel != Level.OFF )
			{
				// enable the console
				Log4jUtilities.enableConsole();
				
				// enable file logging if configured
				String logfile = properties.getProperty(PROP_LOG_FILE, PROP_LOG_FILE_DEFAULT).trim();
				if( !logfile.equalsIgnoreCase("off") &&
					!logfile.equalsIgnoreCase("false") &&
					!logfile.equalsIgnoreCase("none") &&
					!logfile.equalsIgnoreCase("disabled") )
				{
					// we're good, enable it
					Log4jUtilities.enableFile( logfile );
				}
			}

			// apply the appropriate log level to the root disco logger so that others
			// that come after it will inherit that level
			Logger.getLogger("disco").setLevel( loglevel );
			DiscoProperties.IS_LOGGING_CONFIGURED = true;
		}

		// logging configuration has already been done, just return the logger
		return Logger.getLogger( name );
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// Networking ////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Returns the full address the disco framework should use to send and receive packets on.
	 * This combined the values from {@link #getNetworkAddress()} and {@link #getNetworkPort()}.
	 * If there is a problem parsing any of the values from the configuration properties, an
	 * exception will be thrown.
	 */
	public SocketAddress getFullNetworkAddress() throws DiscoException
	{
		return new InetSocketAddress( getNetworkAddress(), getNetworkPort() );
	}

	/**
	 * Gets the configured value for {@link #PROP_NETWORK_ADDRESS} and returns it as an
	 * {@link InetAddress}. If there is a problem parsing the address, an exception is thrown. 
	 */
	public InetAddress getNetworkAddress() throws DiscoException
	{
		try
		{
			return InetAddress.getByName( properties.getProperty(PROP_NETWORK_ADDRESS,
			                                                     PROP_NETWORK_ADDRESS_DEFAULT) );
		}
		catch( UnknownHostException exception )
		{
			throw new DiscoException( exception );
		}
	}

	/**
	 * Return the integer value for the property {@link #PROP_NETWORK_PORT}. 
	 */
	public int getNetworkPort()
	{
		String value = properties.getProperty( PROP_NETWORK_PORT, PROP_NETWORK_PORT_DEFAULT );
		return Integer.parseInt( value );
	}

	/**
	 * Gets the configured value for {@link #PROP_NETWORK_IFACE} and returns it 
	 * as an {@link NetworkInterface}. If there is a problem parsing the address, 
	 * an exception is thrown. 
	 */
	public NetworkInterface getNetworkInterface()
	{
		String ifaceName = properties.getProperty( PROP_NETWORK_IFACE,
		                                           PROP_NETWORK_IFACE_DEFAULT );
		
		try
		{
			return NetworkInterface.getByName( ifaceName );
		}
		catch ( SocketException exception )
		{
			throw new DiscoException( exception );
		}
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------

}
