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
package com.calytrix.disco;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.calytrix.disco.config.DiscoProperties;

/**
 * This class provides setup and shutdown test methods for the testing suite.
 */
public class TestSetup
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static String TEST_LOG_LEVEL = "OFF";

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public TestSetup()
	{
		
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------	
	////////////////////////////////////////////////////////////
	/////////////////// Suite Setup/Shutdown ///////////////////
	////////////////////////////////////////////////////////////
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite()
	{
		// make sure we set the global flag that signals that this is a unit test
		DiscoProperties.IS_UNIT_TEST = true;
		
		///////////////////////////////////////////////////////////////////////////////
		// set the global log level based off the level provided on the command line //
		///////////////////////////////////////////////////////////////////////////////
		// set the log level if it has been specified in the system properties
		String loglevel = System.getProperty( "test.loglevel", "OFF" );
		if( loglevel.equals("${test.loglevel}") )
				loglevel = "OFF";
		
		// set the global log level based off the given level above
		TestSetup.TEST_LOG_LEVEL = loglevel;
		
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite()
	{
		
	}
	
	////////////////////////////////////////////////////////////
	////////////////////// Helper Methods //////////////////////
	////////////////////////////////////////////////////////////
	public static void testStarting( String className, String methodName )
	{
		// don't set things up unless per-test log files are enabled
		//if( FILE_LOG_LEVEL.equals("${test.fileLogLevel}") || FILE_LOG_LEVEL.equals("no") )
		//	return;

		//String testSuite = System.getProperty( "test.suite", "unknownSuite" );
		//String filename = "logs/"+testSuite+"/"+className+"/"+methodName+".log";
		//Log4jConfigurator.redirectFileOutput( filename, false );
	}
	
}
