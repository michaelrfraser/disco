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
 * This field shall specify the interpretation of the modulation parameter 
 * field(s) in the Transmitter PDU.<br/>
 * <br/>
 * This field shall be represented by a 16-bit enumeration
 * 
 * @see "Section 9 in EBV-DOC"
 */
public class System
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI16_SIZE;
	
	public static final int OTHER = 0;
	public static final int GENERIC = 1;
	public static final int HQ = 2;
	public static final int HQII = 3;
	public static final int HQIIA = 4;
	public static final int SINCGARS = 5;
	public static final int CCTT_SINCGARS = 6;
	
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
	 * @return An int[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static int[] getValues()
	{
		int[] values = { OTHER, 
		                 GENERIC, 
		                 HQ, 
		                 HQII, 
		                 HQIIA, 
		                 SINCGARS, 
		                 CCTT_SINCGARS };
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
		
		switch( value )
		{
			case OTHER:
				description = "Other";
				break;
				
			case GENERIC:
				description = "Generic";
				break;
			
			case HQ:
				description = "HQ";
				break;
				
			case HQII:
				description = "HQII";
				break;
				
			case HQIIA:
				description = "HQIIA";
				break;
				
			case SINCGARS:
				description = "SINCGARS";
				break;
				
			case CCTT_SINCGARS:
				description = "CCTT SINCGARS";
				break;
		}
		
		return description;
	}
}
