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

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * This class is responsible for reading types specified in the DIS 
 * specification from the provided InputStream 
 */
public class DISInputStream extends DataInputStream
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
	 * Constructor for type DISInputStream with provided InputStream
	 * 
	 * @param in the InputStream to read DIS types from
	 */
	public DISInputStream( InputStream inputStream )
    {
	    super( inputStream );
    }	
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Reads the next byte from the stream as an 8-bit Unsigned Integer and 
	 * returns the value as a short.
	 * 
	 * @return A short representing the 8-bit Unsigned Integer value
	 * 
	 * @throws IOException thrown if the value could not be read from the stream
	 */
	public short readUI8() throws IOException
	{
		return (short)readUnsignedByte();
	}

	/**
	 * Reads the next two bytes from the stream as an 16-bit Unsigned Integer 
	 * and returns the value as an int.
	 * 
	 * @return An int representing the 16-bit Unsigned Integer value
	 * 
	 * @throws IOException thrown if the value could not be read from the stream
	 */
	public int readUI16() throws IOException
	{
		return readUnsignedShort();
	}
	
	/**
	 * Reads the next four bytes from the stream as a 32-bit Unsigned Integer 
	 * and returns the value as a long.
	 * 
	 * @return A long representing the 32-bit Unsigned Integer value
	 * 
	 * @throws IOException thrown if the value could not be read from the stream
	 */
	public long readUI32() throws IOException
	{
		// Get the next 4 bytes from the stream
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		
		// Check that we haven't gone beyond the stream bounds
		if ( (ch1 | ch2 | ch3 | ch4) < 0 )
			throw new EOFException();
		
		// Assemble the value and return
		return ((long)(ch1 << 24 | ch2 << 16 | ch3 << 8 | ch4)) & 0xFFFFFFFFL;
	}
	
	/**
	 * Reads the next eight bytes from the stream as a 64-bit Unsigned Integer 
	 * and returns the value as a BigInteger.
	 * 
	 * @return A BigInteger representing the 64-bit Unsigned Integer value
	 * 
	 * @throws IOException thrown if the value could not be read from the stream
	 */
	public BigInteger readUI64() throws IOException
	{
		// Read the next 8 bytes into a buffer
		byte[] buffer = new byte[8];
		readFully( buffer );
		
		// Assemble the value and return
		return new BigInteger( buffer );
	}
	
	/**
	 * Skips the next two bytes in the stream. This method is useful for 
	 * skipping over 16 bit padding fields.
	 *   
	 * @throws IOException thrown if the stream does not contain enough bytes 
	 * beyond the read marker to skip over
	 */
	public void skip16() throws IOException
	{
		int skipAmount = skipBytes( 2 );
		if( skipAmount < 2 )
			throw new EOFException();
	}
	
	/**
	 * Skips the next three bytes in the stream. This method is useful for 
	 * skipping over 24 bit padding fields.
	 *   
	 * @throws IOException thrown if the stream does not contain enough bytes 
	 * beyond the read marker to skip over
	 */
	public void skip24() throws IOException
	{
		int skipAmount = skipBytes( 3 );
		if( skipAmount < 3 )
			throw new EOFException();
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
