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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.calytrix.disco.network.DISInputStream;

public class TestUtils
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
	 * Puts the current thread to sleep for the specificed number of milliseconds. If there is an
	 * exception, it prints the stack trace to screen.
	 */
	public static final void sleep( long millis )
	{
		try
		{
			Thread.sleep( millis );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static DISInputStream convertToInputStream( ByteArrayOutputStream baos )
	{
		byte[] buffer = baos.toByteArray();
		return convertToInputStream( buffer );
	}
	
	public static DISInputStream convertToInputStream( byte... buffer )
	{
		ByteArrayInputStream bais = new ByteArrayInputStream( buffer );
		DISInputStream dis = new DISInputStream( bais );
		
		return dis;
	}
}
