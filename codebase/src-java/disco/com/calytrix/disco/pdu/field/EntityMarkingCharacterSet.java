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
 * This enumerated field represents the Character set used in representing the Entity Marking
 * Record.
 */
public class EntityMarkingCharacterSet
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BIT_LENGTH = DISSizes.UI8_SIZE;
	
	public static final short UNUSED = 0;
	public static final short ASCII = 1;
	public static final short ARMY_MARKING = 2;
	public static final short DIGIT_CHEVRON = 3;

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
	public static short[] getValues()
	{
		short[] values = { UNUSED, ASCII, ARMY_MARKING, DIGIT_CHEVRON };
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
			case DIGIT_CHEVRON:
				description = "Digit Chevron";
				break;
				
			case ARMY_MARKING:
				description = "Army Marking (CCTT)";
				break;

			case ASCII:
				description = "ASCII";
				break;

			case UNUSED:
				description = "Unused";
				break;
		}

		return description;
	}
}
