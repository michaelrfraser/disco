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
package com.calytrix.disco.util;

/**
 * Helper class for commonly used floating point operations
 */
public class FloatingPointUtils
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final double FP_EQUALITY_THRESHOLD = 1e-5;
	
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
	 * Returns whether the two specified double value are equal. The two values
	 * will be considered equal if the absolute difference between the values
	 * is within a predefined threshold.
	 * 
	 * @param d1 The first double to compare
	 * @param d2 The second double to compare
	 * 
	 * @return true if the two values are equal, otherwise false
	 */
	public static boolean doubleEqual( double d1, double d2 )
	{
		double absDiff = Math.abs( d1 - d2 );
		return absDiff < FP_EQUALITY_THRESHOLD;
	}
	
	/**
	 * Returns whether the two specified float value are equal. The two values
	 * will be considered equal if the absolute difference between the values
	 * is within a predefined threshold.
	 * 
	 * @param f1 The first float to compare
	 * @param f2 The second float to compare
	 * 
	 * @return true if the two values are equal, otherwise false
	 */
	public static boolean floatEqual( float f1, float f2 )
	{
		float absDiff = Math.abs( f1 - f2 );
		return absDiff < FP_EQUALITY_THRESHOLD;
	}
}
