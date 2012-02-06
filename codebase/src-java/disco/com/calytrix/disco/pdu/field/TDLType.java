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
 * This field shall specify the TDL Type as a 16-bit enumeration field when the 
 * encoding class is the raw binary, audio, application - specific, or database 
 * index representation of a TDL Message. When the data field is not 
 * representing a TDL Message, this field shall be set to zero (see Section 9 
 * in EBV-DOC for enumeration of the TDL Type field).
 * 
 * @see "Section 9 in EBV-DOC"
 */
public class TDLType
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI16_SIZE;
	
	public static final int OTHER = 0;
	public static final int ABBREVIATED_COMMAND_AND_CONTROL = 15;
	
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
	 * Returns the ordered set of values that this enumerated field can assume
	 * 
	 * @return An short[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static int[] getValues()
	{
		int[] values = { OTHER, ABBREVIATED_COMMAND_AND_CONTROL };
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
	public static String getDescription( int value )
	{
		String description = "Undefined";
		
		switch ( value )
		{
			case OTHER:
				description = "Other";
				break;
				
			case ABBREVIATED_COMMAND_AND_CONTROL:
				description = "Abbreviated Command and Control";
				break;
		}
		
		return description;
	}
}
