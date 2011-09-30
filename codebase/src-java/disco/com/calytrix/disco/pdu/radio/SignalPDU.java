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
package com.calytrix.disco.pdu.radio;

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.EncodingScheme;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * This class represents an Signal PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.7.3"
 */
public class SignalPDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int SIGNAL_BASE_SIZE = 32;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityIdentifier entityIdentifier;
	private int radioID;
	private EncodingScheme encodingScheme;
	private int tdlType;
	private long sampleRate;
	private int dataLength;
	private int samples;
	private byte[] data;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public SignalPDU( PDUHeader header,
	                  EntityIdentifier entityIdentifier,
	                  int radioID, 
	                  EncodingScheme encodingScheme,
	                  int tdlType,
	                  long sampleRate, 
	                  int dataLength,
	                  int samples,
	                  byte[] data )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.SIGNAL )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
	    this.entityIdentifier = entityIdentifier;
	    this.radioID = radioID;
	    this.encodingScheme = encodingScheme;
	    this.tdlType = tdlType;
	    this.sampleRate = sampleRate;
	    this.samples = samples;
	    
	    setData( dataLength, data );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	public EntityIdentifier getEntityIdentifier()
	{
		return entityIdentifier;
	}
	
	public void setEntityIdentifier( EntityIdentifier identifier )
	{
		entityIdentifier = identifier;
	}
	
	public int getRadioID()
	{
		return radioID;
	}
	
	public void setRadioID( int radioID )
	{
		this.radioID = radioID;
	}
	
	public EncodingScheme getEncodingScheme()
	{
		return encodingScheme;
	}
	
	public void setEncodingScheme( EncodingScheme encodingScheme )
	{
		this.encodingScheme = encodingScheme;
	}
	
	public int getTDLType()
	{
		return tdlType;
	}
	
	public void setTDLType( int tdlType )
	{
		this.tdlType = tdlType;
	}
	
	public long getSampleRate()
	{
		return sampleRate;
	}
	
	public void setSampleRate( long sampleRate )
	{
		this.sampleRate = sampleRate;
	}
	
	public int getDataLength()
	{
		return dataLength;
	}
	
	public int getSamples()
	{
		return samples;
	}
	
	public void setSamples( int samples )
	{
		this.samples = samples;
	}
	
	public byte[] getData()
	{
		return data;
	}
	
	public void setData( byte[] data )
	{
		int dataLength = data.length * 8;
		setData( dataLength, data );
	}
		
	public void setData( int dataLength, byte[] data )
	{
		boolean lengthAligned = dataLength % 8 == 0;
		int requiredBytes = dataLength / 8;
		if ( !lengthAligned )
			++requiredBytes;
		
		if ( data.length != requiredBytes )
			throw new IllegalStateException( "Data size mismatch" );
		
		this.dataLength = dataLength;
		this.data = data;
		
		PDUHeader header = getHeader();
		header.setLength( SIGNAL_BASE_SIZE + data.length );
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	public static SignalPDU read( PDUHeader header, DISInputStream dis ) throws IOException
	{
		EntityIdentifier entityIdentifier = EntityIdentifier.read( dis );
		int radioID = dis.readUI16();
		EncodingScheme encodingScheme = EncodingScheme.read( dis );
		int tdlType = dis.readUI16();
		long sampleRate = dis.readUI32();
		int dataLength = dis.readUI16();
		int samples = dis.readUI16();
		
		boolean lengthAligned = dataLength % 8 == 0;
		
		int lengthBytes = dataLength / 8;
		if ( !lengthAligned )
			++lengthBytes;
		
		byte[] data = new byte[lengthBytes];
		dis.readFully( data );
		
		return new SignalPDU( header,
		                      entityIdentifier,
		                      radioID,
		                      encodingScheme, 
		                      tdlType,
		                      sampleRate, 
		                      dataLength,
		                      samples,
		                      data );
	}
}
