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
package com.calytrix.disco.pdu.record;

import java.io.EOFException;
import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;

/**
 * This field shall specify the encoding used in the Data field of this PDU. 
 * The Encoding Scheme shall be composed of a 2-bit field specifying the 
 * encoding class and a 14-bit field specifying either the encoding type, or the 
 * number of TDL messages contained in this Signal PDU
 */
public class EncodingScheme
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
		
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte encodingClass;
	private short encodingType;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EncodingScheme( byte encodingClass, short encodingType )
	{
		this.encodingClass = encodingClass;
		this.encodingType = encodingType;
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals( Object other )
	{
		boolean equal = false;
		
		if ( other == this )
		{
			equal = true;
		}
		else
		{
			if ( other instanceof EncodingScheme )
			{
				EncodingScheme asEncodingScheme = (EncodingScheme)other;
				equal = asEncodingScheme.encodingClass == this.encodingClass &&
					asEncodingScheme.encodingType == this.encodingType;
			}
		}
		
		return equal;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public byte getEncodingClass()
	{
		return encodingClass;
	}
	
	public void setEncodingClass( byte encodingClass )
	{
		this.encodingClass = encodingClass;
	}
	
	public short getEncodingType()
	{
		return encodingType;
	}
	
	public void setEncodingType( short encodingType )
	{
		this.encodingType = encodingType;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream
	 * 
	 * @param dis The DISInputStream to read the record from
	 * 
	 * @return The EncodingScheme deserialised from the provided input stream
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream
	 */
	public static EncodingScheme read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		int ch2 = dis.read();
		
		if ( (ch1 | ch2) < 0 )
			throw new EOFException();
		
		byte encodingClass = (byte)((ch1 & 0xC0) >> 6);
		short encodingType = (short)(((ch1 & 0x3F) << 8) + ch2);
		
		return new EncodingScheme( encodingClass, encodingType );
	}
}
