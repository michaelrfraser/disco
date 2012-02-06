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
 * This field shall specify the type of representation used for the radiation 
 * pattern from the antenna. The value of this field shall determine the 
 * interpretation of the Antenna Pattern Parameter field.<br/>
 * <br/>
 * This field shall be represented by a 16-bit enumeration
 * 
 * @see "Section 9 in EBV-DOC"
 */
public class AntennaPatternType
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI16_SIZE;
	
	public static final int OMNI_DIRECTIONAL = 0;
	public static final int BEAM = 1;
	public static final int SPHERICAL_HARMONIC = 2;
	
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
		int[] values = { OMNI_DIRECTIONAL, BEAM, SPHERICAL_HARMONIC };
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
			case OMNI_DIRECTIONAL:
				description = "Omni-directional";
				break;
				
			case BEAM:
				description = "Beam";
				break;
				
			case SPHERICAL_HARMONIC:
				description = "Spherical harmonic";
				break;
		}
		
		return description;
	}
}
