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
 * This field shall indicate the spread spectrum technique or combination of 
 * spread spectrum techniques in use.<br/>
 * <br/>
 * The Spread Spectrum field shall consist of a 16 element boolean array. Each 
 * independent type of spread spectrum technique shall be represented by a 
 * single element of this array. If a particular spread sprectrum technique is 
 * in use, the corresponding array element shall be set to one, otherwise it 
 * shall be set to zero. All unused array elements shall be set to zero.<br/>
 * <br/>	
 * The supported spread spectrum techniques and their assignment to elements of 
 * the 16 element array are defined in Section 9 of EBV-DOC.
 * 
 * <table>
 * 		<tr>
 * 			<th>Bits 3-15</th>
 * 			<th>Bit 2</th>
 * 			<th>Bit 1</th>
 * 			<th>Bit 0</th>
 * 		</tr>
 * 		<tr>
 * 			<td>TBD</td>
 * 			<td>Time Hop</td>
 * 			<td>Pseudo Noise</td>
 * 			<td>Frequency Hop</td>
 * 		</tr>
 * </table>
 * 
 * @see "Section 9 of EBV-DOC"
 */
public class SpreadSpectrum
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int FREQUENCY_HOPPING = 0x01;
	public static final int PSEUDO_NOISE = 0x02;
	public static final int TIME_HOPPING = 0x04;
	
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
		int[] values = { FREQUENCY_HOPPING, PSEUDO_NOISE, TIME_HOPPING };
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
		String description = "";
		
		if ( (value & FREQUENCY_HOPPING) != 0 )
			description += "Frequency Hopping,";
		
		if ( (value & PSEUDO_NOISE) != 0 )
			description += "Pseudo-noise,";
		
		if ( (value & TIME_HOPPING) != 0 )
			description += "Time Hopping,";
		
		if ( description.isEmpty() )
			description = "None";
		
		return description;
	}
}
