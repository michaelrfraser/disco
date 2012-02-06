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
 * This field shall identify the crypto equipment utilized if such equipment is 
 * used with the Transmitter PDU.<br/>
 * <br/>
 * This field shall be represented by a 16-bit enumeration
 * 
 * @see "Section 9 in EBV-DOC"
 */
public class CryptoSystem
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI16_SIZE;
	
	public static final int OTHER = 0;
	public static final int KY_28 = 1;
	public static final int KY_58 = 2;
	public static final int NSVE = 3;
	public static final int WSVE = 4;
	public static final int SINCGARS_ICOM = 5;
	
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
		int[] values = { OTHER, KY_28, KY_58, NSVE, WSVE, SINCGARS_ICOM };
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
				
			case KY_28:
				description = "KY-28";
				break;
			
			case KY_58:
				description = "KY-58";
				break;
				
			case NSVE:
				description = "Narrow Spectrum Secure Voice";
				break;
				
			case WSVE:
				description = "Wide Spectrum Secure Voice";
				break;
				
			case SINCGARS_ICOM:
				description = "SINCGARS ICOM";
				break;
		}
		
		return description;
	}
}
