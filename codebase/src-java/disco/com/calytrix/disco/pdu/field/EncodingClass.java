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

/**
 * The two most significant bits of the encoding scheme shall enumerate the 
 * following encoding classes.<br/>
 * <br/>
 * The valid values of encoding classes are enumerated in Section 9 of EBV-DOC.
 * 
 * @see "Section 9 of EBV-DOC"
 */
public class EncodingClass
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final byte ENCODED_VOICE = 0;
	public static final byte RAW_BINARY_DATA = 1;
	public static final byte APPLICATION_SPECIFIC_DATA = 2;
	public static final byte DATABASE_INDEX = 3;
	
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
	 * @return A byte[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static byte[] getValues()
	{
		byte[] values = { ENCODED_VOICE,
		                  RAW_BINARY_DATA, 
		                  APPLICATION_SPECIFIC_DATA,
		                  DATABASE_INDEX };
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
	public static String getDescription( byte value )
	{
		String description = "Encoded Voice";
		
		switch( value )
		{
			case ENCODED_VOICE:
				description = "Encoded Voice";
				break;
				
			case RAW_BINARY_DATA:
				description = "Raw Binary Data";
				break;
				
			case APPLICATION_SPECIFIC_DATA:
				description = "Application-Specific Data";
				break;
				
			case DATABASE_INDEX:
				description = "Database index";
				break;
		}
		
		return description;
	}
}
