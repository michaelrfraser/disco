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
 * The type of radio in a DIS exercise shall be specified by a Radio Entity Type 
 * Record. This record shall specify the kind of entity, the domain, the country 
 * of design, and specific information about the radio.
 */
public class InputSource
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final short OTHER = 0;
	public static final short PILOT = 1;
	public static final short COPILOT = 2;
	public static final short FIRST_OFFICER = 3;
	public static final short DRIVER = 4;
	public static final short LOADER = 5;
	public static final short GUNNER = 6;
	public static final short COMMANDER = 7;
	public static final short DIGITAL_DATA_DEVICE = 8;
	public static final short INTERCOM = 9;
	
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
	 * @return A short[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static short[] getValues()
	{
		short[] values = { OTHER, PILOT, COPILOT, FIRST_OFFICER, DRIVER, LOADER, 
		                   GUNNER, COMMANDER, DIGITAL_DATA_DEVICE, INTERCOM };
		
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
		String description = "Other";
		
		switch ( value )
		{
			case PILOT:
				description = "Pilot";
				break;
			case COPILOT:
				description = "Copilot";
				break;
			case FIRST_OFFICER:
				description = "First Officer";
				break;
			case DRIVER:
				description = "Driver";
				break;
			case LOADER:
				description = "Loader";
				break;
			case GUNNER:
				description = "Gunner";
				break;
			case COMMANDER:
				description = "Commander";
				break;
			case DIGITAL_DATA_DEVICE:
				description = "Digital Data Device";
				break;
			case INTERCOM:
				description = "Intercom";
				break;
		}
		
		return description;
	}
}
