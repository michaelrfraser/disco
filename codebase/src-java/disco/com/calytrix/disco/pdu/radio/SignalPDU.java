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
	private EntityIdentifier m_entityIdentifier;
	private int m_radioID;
	private EncodingScheme m_encodingScheme;
	private int m_tdlType;
	private long m_sampleRate;
	private int m_dataLength;
	private int m_samples;
	private byte[] m_data;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public SignalPDU( PDUHeader header, EntityIdentifier identifier, int radioID, 
	                  EncodingScheme encodingScheme, int tdlType, long sampleRate, 
	                  int dataLength, int samples, byte[] data )
	{
		super( header );
		
		if ( header.getPDUType() != PDUType.SIGNAL )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
	    m_entityIdentifier = identifier;
	    m_radioID = radioID;
	    m_encodingScheme = encodingScheme;
	    m_tdlType = tdlType;
	    m_sampleRate = sampleRate;
	    m_samples = samples;
	    
	    setData( dataLength, data );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	public EntityIdentifier getEntityIdentifier()
	{
		return m_entityIdentifier;
	}
	
	public void setEntityIdentifier( EntityIdentifier identifier )
	{
		m_entityIdentifier = identifier;
	}
	
	public int getRadioID()
	{
		return m_radioID;
	}
	
	public void setRadioID( int radioID )
	{
		m_radioID = radioID;
	}
	
	public EncodingScheme getEncodingScheme()
	{
		return m_encodingScheme;
	}
	
	public void setEncodingScheme( EncodingScheme encodingScheme )
	{
		m_encodingScheme = encodingScheme;
	}
	
	public int getTDLType()
	{
		return m_tdlType;
	}
	
	public void setTDLType( int tdlType )
	{
		m_tdlType = tdlType;
	}
	
	public long getSampleRate()
	{
		return m_sampleRate;
	}
	
	public void setSampleRate( long sampleRate )
	{
		m_sampleRate = sampleRate;
	}
	
	public int getDataLength()
	{
		return m_dataLength;
	}
	
	public int getSamples()
	{
		return m_samples;
	}
	
	public void setSamples( int samples )
	{
		m_samples = samples;
	}
	
	public byte[] getData()
	{
		return m_data;
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
		
		m_dataLength = dataLength;
		m_data = data;
		
		PDUHeader header = getHeader();
		header.setLength( SIGNAL_BASE_SIZE + m_data.length );
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	public static SignalPDU read( PDUHeader header, DISInputStream dis ) throws IOException
	{
		EntityIdentifier identifier = EntityIdentifier.read( dis );
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
		
		return new SignalPDU( header, identifier, radioID, encodingScheme, 
		                      tdlType, sampleRate, dataLength, samples, data );
	}
}
