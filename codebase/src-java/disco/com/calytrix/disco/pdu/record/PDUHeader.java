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

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;

/**
 * A PDU Header Record shall be the first part of each PDU.
 */
public class PDUHeader
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int PDU_HEADER_LENGTH = 12;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short protocolVersion;
	private short exerciseIdentifier;
	private short pduType;
	private short protocolFamily;
	private long timestamp;
	private int length;
		
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public PDUHeader( short protocolVersion,
	                  short exerciseIdentifier, 
	                  short pduType,
	                  short protocolFamily,
	                  long timestamp,
	                  int length )
	{
		this.protocolVersion = protocolVersion;
		this.exerciseIdentifier = exerciseIdentifier;
		this.pduType = pduType;
		this.protocolFamily = protocolFamily;
		this.timestamp = timestamp;
		this.length = length;
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public short getProtocolVersion()
	{
		return protocolVersion;
	}
	
	public void setProtocolVersion( short protocolVersion )
	{
		this.protocolVersion = protocolVersion;
	}

	public short getExerciseIdentifier()
	{
		return exerciseIdentifier;
	}
	
	public void setExerciseIdentifier( short exerciseIdentifier )
	{
		this.exerciseIdentifier = exerciseIdentifier;
	}
	
	public short getPDUType()
	{
		return pduType;
	}
	
	public void setPDUType( short pduType )
	{
		this.pduType = pduType;
	}
	
	public short getProtocolFamily()
	{
		return protocolFamily;
	}
	
	public void setProtocolFamily( short protocolFamily )
	{
		this.protocolFamily = protocolFamily;
	}
	
	public long getTimestamp()
	{
		return timestamp;
	}
	
	public void setTimestamp( long timestamp )
	{
		this.timestamp = timestamp; 
	}
	
	public int getLength()
	{
		return length;
	}
	
	public void setLength( int newLength )
	{
		if( newLength < PDU_HEADER_LENGTH )
		{
			throw new IllegalStateException( "Invalid PDU length " + newLength +
			                                 " (less than header size)" );
		}
		
		this.length = newLength;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream
	 * 
	 * @param dis The DISInputStream to read the record from
	 * 
	 * @return The PDUHeader deserialised from the provided input stream
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream
	 */
	public static PDUHeader read( DISInputStream dis ) throws IOException
	{
		short protocolVersion = dis.readUI8();
		short exerciseIdentifier = dis.readUI8();
		short pduType = dis.readUI8();
		short protocolFamily = dis.readUI8();
		long timestamp = dis.readUI32();
		int length = dis.readUI16();
		
		dis.readUI16(); // padding bytes
		
		return new PDUHeader( protocolVersion,
		                      exerciseIdentifier,
		                      pduType,
		                      protocolFamily,
		                      timestamp,
		                      length );
	}
}
