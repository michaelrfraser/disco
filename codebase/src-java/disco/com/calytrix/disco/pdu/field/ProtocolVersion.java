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

public class ProtocolVersion
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI8_SIZE;
	
	public static final short OTHER = 0;
	public static final short DIS_PDU_V1 = 1;
	public static final short IEEE_1278 = 2;
	public static final short DIS_PDU_V2_DRAFT_3 = 3;
	public static final short DIS_PDU_V2_DRAFT_4 = 4;
	public static final short IEEE_1278_1 = 5;
	
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
		short[] values = { OTHER, 
		                   DIS_PDU_V1,
		                   IEEE_1278,
		                   DIS_PDU_V2_DRAFT_3,
		                   DIS_PDU_V2_DRAFT_4,
		                   IEEE_1278_1 };
		
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
				
			case DIS_PDU_V1:
				description = "DIS PDU version 1.0";
				break;
				
			case IEEE_1278:
				description = "IEEE 1278";
				break;
				
			case DIS_PDU_V2_DRAFT_3:
				description = "DIS PDU version 2.0 - third draft";
				break;
				
			case DIS_PDU_V2_DRAFT_4:
				description = "DIS PDU version 2.0 - fourth draft";
				break;
				
			case IEEE_1278_1:
				description = "IEEE 1278.1";
				break;
		}
		
		return description;
	}	
}
