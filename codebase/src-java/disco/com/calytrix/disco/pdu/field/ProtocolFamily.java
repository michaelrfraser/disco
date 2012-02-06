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

public class ProtocolFamily
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI8_SIZE;
	
	public static final short OTHER = 0;
	public static final short ENTITY_INFORMATION_INTERACTION = 1;
	public static final short WARFARE = 2;
	public static final short LOGISTICS = 3;
	public static final short RADIO_COMMUNICATION = 4;
	public static final short SIMULATION_MANAGEMENT = 5;
	public static final short DISTRIBUTED_EMISSION_REGENERATION = 6;
	
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
		short[] values = { OTHER,
		                   ENTITY_INFORMATION_INTERACTION,
		                   WARFARE,
		                   LOGISTICS,
		                   RADIO_COMMUNICATION,
		                   SIMULATION_MANAGEMENT,
		                   DISTRIBUTED_EMISSION_REGENERATION };
		
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
		
		switch ( value )
		{
			case OTHER:
				description = "Other";
				break;
				
			case ENTITY_INFORMATION_INTERACTION:
				description = "Entity Information/Interaction";
				break;
				
			case WARFARE:
				description = "Warfare";
				break;
				
			case LOGISTICS:
				description = "Logistics";
				break;
				
			case RADIO_COMMUNICATION:
				description = "Radio Communication";
				break;
				
			case SIMULATION_MANAGEMENT:
				description = "Simulation Management";
				break;
				
			case DISTRIBUTED_EMISSION_REGENERATION:
				description = "Distributed Emission Regeneration";
				break;
		}
		
		return description;
	}
}
