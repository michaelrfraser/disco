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
package com.calytrix.disco.pdu.field;

import com.calytrix.disco.util.DISSizes;

/**
 * This field shall specify whether a radio is off, powered but not 
 * transmitting, or powered and transmitting.<br/>
 * <br/>
 * This field shall be represented by an 8-bit enumeration. 
 * 
 * @see "Section 9 in EBV-DOC"
 */
public class TransmitState
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI8_SIZE;
	
	public static final short OFF = 0;
	public static final short ON_BUT_NOT_TRANSMITTING = 1;
	public static final short ON_AND_TRANSMITTING = 2;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Returns the ordered set of values that this enumerated field can assume
	 * 
	 * @return A short[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static short[] getValues()
	{
		short[] values = { OFF, ON_BUT_NOT_TRANSMITTING, ON_AND_TRANSMITTING };
		return values;
	}
	
	/**
	 * Returns a textual description of the provided field value
	 * 
	 * @param value The field value to return a description of
	 * 
	 * @return A String representing the description of the specified field
	 * value
	 */
	public static String getDescription( short value )
	{
		String description = "Undefined";
		
		switch( value )
		{
			case OFF:
				description = "Off";
				break;
			case ON_BUT_NOT_TRANSMITTING:
				description = "On but not transmitting";
				break;
			case ON_AND_TRANSMITTING:
				description = "On and transmitting";
				break;
		}
		
		return description;
	}
}

