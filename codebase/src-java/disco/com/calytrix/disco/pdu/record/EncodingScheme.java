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
 * number of TDL messages contained in this Signal PDU.<br/>
 * <br/>
 * The fourteen least significant bits of the encoding scheme shall represent 
 * encoding type when the encoding class is encoded audio. The valid values of 
 * encoding type are enumerated in Section 9 of EBV-DOC.<br/>
 * <br/>
 * The fourteen least significant bits of the encoding scheme shall be zero when 
 * the encoding class is not encoded audio and the TDL Type (see 5.4.8.2(e)) is 
 * zero.<br/>
 * <br/>
 * Otherwise, the fourteen least significant bits of the encoding scheme shall 
 * represent the number of tactical data link messages contained in the data 
 * section of the Signal PDU.
 * 
 * @see "IEEE Std 1278.1-1995 section 5.4.8.2(e)"
 * @see "Section 9 of EBV-DOC"
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
		if( other == this )
			return true;

		if( other instanceof EncodingScheme )
		{
			EncodingScheme asEncodingScheme = (EncodingScheme)other;
			if( asEncodingScheme.encodingClass == this.encodingClass &&
				asEncodingScheme.encodingType == this.encodingType )
			{
				return true;
			}
		}
		
		return false;
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
