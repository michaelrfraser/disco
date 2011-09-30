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
 * The fourteen least significant bits of the encoding scheme shall represent 
 * encoding type when the encoding class is encoded audio.<br/>
 * <br/>
 * The valid values of encoding type are enumerated in Section 9 of EBV-DOC.
 * 
 * @see "Section 9 of EBV-DOC"
 */
public class EncodingType
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final short MULAW_8 = 1;
	public static final short CVSD = 2;
	public static final short ADPCM = 3;
	public static final short PCM_16 = 4;
	public static final short PCM_8 = 5;
	public static final short VQ = 6;
		
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
		short[] values = { MULAW_8, CVSD, ADPCM, PCM_16, PCM_8, VQ };
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
		String description = "8-bit mu-law";
		
		switch( value )
		{
			case CVSD:
				description = "CVSD";
				break;
				
			case ADPCM:
				description = "ADPCM";
				break;
				
			case PCM_16:
				description = "16-bit linear PCM";
				break;
				
			case PCM_8:
				description = "8-bit linear PCM";
				break;
				
			case VQ:
				description = "Vector Quantization";
				break;
		}
		
		return description;
	}
}
