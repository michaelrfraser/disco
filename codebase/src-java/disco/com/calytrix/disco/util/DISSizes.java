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
package com.calytrix.disco.util;

import java.util.Collection;

import com.calytrix.disco.pdu.IPDUComponent;

/**
 * A Utility class that provides size constants and functions
 */
public class DISSizes
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	
	// SIZES (specified in bytes)
	public static final int UI8_SIZE = 1;
	public static final int UI16_SIZE = 2;
	public static final int UI32_SIZE = 4;
	public static final int UI64_SIZE = 8;
	public static final int FLOAT32_SIZE = 4;
	public static final int FLOAT64_SIZE = 8;
	
	// MAXIMUM VALUES
	public static final short UI8_MAX_VALUE = 0xFF; 
	public static final int UI16_MAX_VALUE = 0xFFFF;
	public static final long UI32_MAX_VALUE = 0xFFFFFFFFl;
	
	/**
	 * The maximum value that can fit into the PDU Header's length field (specified in bytes). 
	 */
	public static final int PDU_MAX_SIZE = UI16_MAX_VALUE;
	
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
	 * Returns the size, in bytes, of the the specified collection of DIS <code>IPDUComponent</code>s
	 * 
	 * @param collection The collection to calculate the size of
	 * 
	 * @return An int representing the size, in bytes, of the specified collection
	 */
	public static int getByteLengthOfCollection( Collection<? extends IPDUComponent> collection )
	{
		int size = 0;
		
		for ( IPDUComponent component : collection )
			size += component.getByteLength();
			
		return size;
	}
}
