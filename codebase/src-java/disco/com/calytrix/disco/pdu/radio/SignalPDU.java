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
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.field.TDLType;
import com.calytrix.disco.pdu.record.EncodingScheme;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.util.DISSizes;

/**
 * This class represents an Signal PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.7.3"
 */
public class SignalPDU extends AbstractRadioPDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EncodingScheme encodingScheme;
	private int tdlType;
	private long sampleRate;
	private int dataLength;
	private int samples;
	private byte[] data;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for SignalPDU with provided PDUHeader
	 * 
	 * @param header The PDUHeader that this PDU will wrap 
	 */
	public SignalPDU( PDUHeader header )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.SIGNAL )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		this.encodingScheme = new EncodingScheme();
		this.tdlType = TDLType.OTHER;
		this.sampleRate = 0;
		this.samples = 0;
		setData( new byte[0] );
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readContent( DISInputStream dis ) throws IOException
	{
		super.readContent( dis );

		encodingScheme.read( dis );
		tdlType = dis.readUI16();
		sampleRate = dis.readUI32();
		int dataLength = dis.readUI16();
		samples = dis.readUI16();
		
		boolean lengthAligned = dataLength % 8 == 0;
		
		int lengthBytes = dataLength / 8;
		if( !lengthAligned )
			++lengthBytes;
		
		byte[] data = new byte[lengthBytes];
		dis.readFully( data );
		
		setData( dataLength, data );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeContent( DISOutputStream dos ) throws IOException
	{
		super.writeContent( dos );
		
		encodingScheme.write( dos );
		dos.writeUI16( tdlType );
		dos.writeUI32( sampleRate );
		dos.writeUI16( dataLength );
		dos.writeUI16( samples );
		dos.write( data );
	}
	
	@Override
	public int getContentLength()
	{
		int size = super.getContentLength();
		size += encodingScheme.getByteLength();
		size += TDLType.BYTE_LENGTH;
		size += DISSizes.UI32_SIZE;		// Sample Rate
		size += DISSizes.UI16_SIZE;		// Data Length
		size += DISSizes.UI16_SIZE;		// Samples
		size += data.length;
		
		return size;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
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
		// Ensure the length of the data section is aligned as per the spec
		boolean lengthAligned = dataLength % 8 == 0;
		int requiredBytes = dataLength / 8;
		if( !lengthAligned )
			++requiredBytes;
		
		if( data.length != requiredBytes )
			throw new IllegalArgumentException( "Data size mismatch" );
		
		this.dataLength = dataLength;
		this.data = data;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
