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
 * This field shall specify the major classification of the modulation type.<br/>
 * <br/> 
 * This field shall be represented by a 16-bit enumeration.
 * 
 * @see "Section 9 in EBV-DOC"
 */
public class MajorModulationType
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int OTHER = 0;
	public static final int AMPLITUDE = 1;
	public static final int AMPLITUDE_AND_ANGLE = 2;
	public static final int ANGLE = 3;
	public static final int COMBINATION = 4;
	public static final int PULSE = 5;
	public static final int UNMODULATED = 6;
	
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
	 * @return An int[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static int[] getValues()
	{
		int[] values = { OTHER, AMPLITUDE, AMPLITUDE_AND_ANGLE, ANGLE, 
		                 COMBINATION, PULSE, UNMODULATED };
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
			case AMPLITUDE:
				description = "Amplitude";
				break;
			case AMPLITUDE_AND_ANGLE:
				description = "Amplitude and Angle";
				break;
			case ANGLE:
				description = "Angle";
				break;
			case COMBINATION:
				description = "Combination";
				break;
			case PULSE:
				description = "Pulse";
				break;
			case UNMODULATED:
				description = "Unmodulated";
				break;
		}
		
		return description;
	}
}
