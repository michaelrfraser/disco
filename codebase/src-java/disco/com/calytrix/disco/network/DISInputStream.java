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
	 * @param inputStream the InputStream to read DIS types from
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
		if( (ch1 | ch2 | ch3 | ch4) < 0 )
			throw new EOFException();
		
		// Assemble the value and return
		return ((long)(ch1 << 24 | ch2 << 16 | ch3 << 8 | ch4)) & 0xFFFFFFFFL;
	}
	
	/**
	 * Reads the specified number of bytes from the stream as a String
	 * 
	 * @param len the number of bytes to read from the stream
	 * 
	 * @return A String representing the bytes read in String form 
	 * 
	 * @throws IOException thrown if the value could not be read from the stream
	 */
	public String readString( int len ) throws IOException
	{
		byte[] buffer = new byte[len];
		readFully( buffer );
		
		return new String( buffer );
	}
	
	/**
	 * Skips the next specified number og bytes in the stream.
	 *   
	 * @throws IOException thrown if the stream does not contain enough bytes 
	 * beyond the read marker to skip over
	 */
	private void checkedSkipBytes( int bytes ) throws IOException
	{
		int skipAmount = skipBytes( bytes );
		if( skipAmount < bytes )
			throw new EOFException();
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
		checkedSkipBytes( 2 );
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
		checkedSkipBytes( 3 );
	}
	
	/**
	 * Skips the next four bytes in the stream. This method is useful for 
	 * skipping over 32 bit padding fields.
	 *   
	 * @throws IOException thrown if the stream does not contain enough bytes 
	 * beyond the read marker to skip over
	 */
	public void skip32() throws IOException
	{
		checkedSkipBytes( 4 );
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
