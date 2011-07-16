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

import java.io.File;
import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.calytrix.disco.DiscoException;


/**
 * This class provides simple utility methods for performing Log4j configuration. Any of the 
 * methods that affect all loggers are actually applied to the root logger ("disco") with the
 * intention that their effects will be inherited by all loggers.
 */
public class Log4jUtilities
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
	 * Validates the given String, ensuring that it identifies a proper log4j level. If it doesn't
	 * an exception is thrown. If it does, the appropriate <code>Level</code> object is returned.
	 */
	public static Level validateLevel( String level ) throws DiscoException
	{
		if( level.equalsIgnoreCase("ALL") )
			return Level.ALL;
		else if( level.equalsIgnoreCase("TRACE") )
			return Level.TRACE;
		else if( level.equalsIgnoreCase("DEBUG") )
			return Level.DEBUG;
		else if( level.equalsIgnoreCase("INFO") )
			return Level.INFO;
		else if( level.equalsIgnoreCase("WARN") )
			return Level.WARN;
		else if( level.equalsIgnoreCase("ERROR") )
			return Level.ERROR;
		else if( level.equalsIgnoreCase("FATAL") )
			return Level.FATAL;
		else if( level.equalsIgnoreCase("OFF") )
			return Level.OFF;
		else
			throw new DiscoException( "Log Level [" + level + "] not valid" );
	}
	
	/**
	 * Create and attach a console appender to the application logger. Only call if you've checked
	 * that you haven't already done this!
	 * <p/>
	 * This method will attach a console appender to the "disco" logger.
	 */
	public static void enableConsole()
	{
		// create the appender
		PatternLayout layout = new PatternLayout( "%-5p [%t] %c: %x%m%n" );
		ConsoleAppender appender = new ConsoleAppender( layout, ConsoleAppender.SYSTEM_OUT );
		appender.setThreshold( Level.TRACE ); // output restricted at logger level, not appender

		// attach the appender
		Logger.getLogger("disco").addAppender( appender );
	}
	
	/**
	 * Create and attach a file appender to the application logger. Only call if you've checked
	 * that you haven't already done this!
	 * <p/>
	 * This method will attach a file appender to the "disco" logger.
	 */
	public static void enableFile( String filename ) throws DiscoException
	{
		try
		{
			PatternLayout layout = new PatternLayout( "%-5p [%t] %c: %x%m%n" );

			// If the log file already exists, we want to roll it over so that the contents
			// of a log file don't contain logging for more than one execution. However, if
			// we wait until after we create the appender to check to see if the file exists,
			// it will (as the appeander creates it when it is created). So we'll check now,
			// and rollover later if we have to
			boolean rollover = false;
			if( new File(filename).exists() )
				rollover = true;
			
			RollingFileAppender appender = new RollingFileAppender( layout, filename, false );
			appender.setMaxBackupIndex( 2 );
			appender.setMaxFileSize( "10MB" );
			appender.setThreshold( Level.TRACE ); // output restricted at logger level, not appender
			
			// roll over if we have to
			if( rollover )
				appender.rollOver();
			
			// attach the appender
			Logger.getLogger("disco").addAppender( appender );
		}
		catch( IOException ioex )
		{
			throw new DiscoException( ioex );
		}
	}
}
