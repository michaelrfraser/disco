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
package com.calytrix.disco.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.calytrix.disco.util.DISSizes;

/**
 * This class is responsible for writing types specified in the DIS 
 * specification to the provided OutputStream 
 */
public class DISOutputStream extends DataOutputStream
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
		
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type DISOutputStream with provided OutputStream
	 * 
	 * @param out the OutputStream to write DIS types to
	 */
	public DISOutputStream( OutputStream out )
    {
		super( out );
    }
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Writes the specified 8-bit unsigned integer value to the stream. 
	 * 
	 * @param value The value to write to the stream. The value must be within the range 0 to 255 
	 * inclusive
	 * 
	 * @throws IOException thrown if there was a problem writing the value to the stream
	 * @throws IllegalArgumentException If the value was outside the range of a 8-bit unsigned 
	 * integer
	 */
	public void writeUI8( short value ) throws IOException
	{
		if( value < 0 || value > DISSizes.UI8_MAX_VALUE )
		{
			String message = "Number out of range. Expecting a number between 0 and " + 
				DISSizes.UI8_MAX_VALUE;
			throw new IllegalArgumentException( message );
		}
		
		writeByte( value );
	}
	
	/**
	 * Writes the specified 16-bit unsigned integer value to the stream. 
	 * 
	 * @param value The value to write to the stream. The value must be within the range 0 to 65,535 
	 * inclusive
	 * 
	 * @throws IOException thrown if there was a problem writing the value to the stream
	 * @throws IllegalArgumentException If the value was outside the range of a 16-bit unsigned 
	 * integer
	 */
	public void writeUI16( int value ) throws IOException
	{
		if( value < 0 || value > DISSizes.UI16_MAX_VALUE )
		{
			String message = "Number out of range. Expecting a number between 0 and " + 
				DISSizes.UI16_MAX_VALUE;
			throw new IllegalArgumentException( message );
		}
		
		writeShort( value );
		
	}
	
	/**
	 * Writes the specified 32-bit unsigned integer value to the stream. 
	 * 
	 * @param value The value to write to the stream. The value must be within the range 0 to 
	 * 4,294,967,295 inclusive
	 * 
	 * @throws IOException thrown if there was a problem writing the value to the stream
	 * @throws IllegalArgumentException If the value was outside the range of a 32-bit unsigned 
	 * integer
	 */
	public void writeUI32( long value ) throws IOException
	{
		if( value < 0 || value > DISSizes.UI32_MAX_VALUE )
		{
			String message = "Number out of range. Expecting a number between 0 and " + 
				DISSizes.UI32_MAX_VALUE;
			throw new IllegalArgumentException( message );
		}
		
		write( (int)((value >>> 24) & 0xFF) );
		write( (int)((value >>> 16) & 0xFF) );
		write( (int)((value >>> 8) & 0xFF) );
		write( (int)((value >>> 0) & 0xFF) );
	}
	
	/**
	 * Writes <code>count</code> bytes of padding zeros to the stream.
	 * 
	 * @param count The amount of padding bytes to write
	 * 
	 * @throws IOException thrown if there was a problem writing the padding bytes to the stream
	 */
	public void writePadding( int count ) throws IOException
	{
		for( int i = 0 ; i < count ; ++i )
			write( 0 );
	}
	
	/**
	 * Writes 16 bits of padding zeros to the stream.
	 * 
	 * @throws IOException thrown if there was a problem writing the padding bytes to the stream
	 */
	public void writePadding16() throws IOException
	{
		writePadding( 2 );
	}
	
	/**
	 * Writes 24 bits of padding zeros to the stream.
	 * 
	 * @throws IOException thrown if there was a problem writing the padding bytes to the stream
	 */
	public void writePadding24() throws IOException
	{
		writePadding( 3 );
	}
	
	/**
	 * Writes 32 bits of padding zeros to the stream.
	 * 
	 * @throws IOException thrown if there was a problem writing the padding bytes to the stream
	 */
	public void writePadding32() throws IOException
	{
		writePadding( 4 );
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
