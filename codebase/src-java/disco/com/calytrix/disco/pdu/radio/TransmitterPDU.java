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
import com.calytrix.disco.pdu.field.AntennaPatternType;
import com.calytrix.disco.pdu.field.CryptoSystem;
import com.calytrix.disco.pdu.field.InputSource;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.field.TransmitState;
import com.calytrix.disco.pdu.record.AntennaLocation;
import com.calytrix.disco.pdu.record.ModulationType;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.RadioEntityType;
import com.calytrix.disco.util.DISSizes;
import com.calytrix.disco.util.DISUnsignedInt64;

/**
 * This class represents an Transmitter PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.7.2"
 */
public class TransmitterPDU extends AbstractRadioPDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private RadioEntityType radioEntityType;
	private short transmitState;
	private short inputSource;
	private AntennaLocation antennaLocation;
	private int antennaPatternType;
	private DISUnsignedInt64 transmissionFrequency;
	private float transmissionFrequencyBandwidth;
	private float power;
	private ModulationType modulationType;
	private int cryptoSystem;
	private int cryptoKey;
	private byte[] modulationParameter;
	private byte[] antennaPatternParameter;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for TransmitterPDU with provided PDUHeader
	 * 
	 * @param header The PDUHeader that this PDU will wrap 
	 */
	public TransmitterPDU( PDUHeader header )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.TRANSMITTER )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		this.radioEntityType = new RadioEntityType();
		this.transmitState = TransmitState.OFF;
		this.inputSource = InputSource.OTHER;
		this.antennaLocation = new AntennaLocation();
		this.transmissionFrequency = DISUnsignedInt64.ZERO;
		this.transmissionFrequencyBandwidth = 0f;
		this.power = 0f;
		this.cryptoSystem = CryptoSystem.OTHER;
		this.cryptoKey = 0;
		
		setModulation( new ModulationType(), new byte[0] );
		setAntennaPattern( AntennaPatternType.OMNI_DIRECTIONAL, new byte[0] );
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
		
		radioEntityType.read( dis );
		transmitState = dis.readUI8();
		inputSource = dis.readUI8();
		
		// Padding
		dis.skip16();
				
		antennaLocation.read( dis );
		int antennaPatternType = dis.readUI16();
		int antennaPatternLength = dis.readUI16();
		transmissionFrequency.read( dis );
		transmissionFrequencyBandwidth = dis.readFloat();
		power = dis.readFloat();
		modulationType.read( dis );
		cryptoSystem = dis.readUI16();
		cryptoKey = dis.readUI16();
		
		short modulationParametersLength = dis.readUI8();
		
		// Padding
		dis.skip24();
		byte[] modulationParameter = new byte[modulationParametersLength];
		dis.readFully( modulationParameter );
		setModulation( modulationType, modulationParameter );
		
		byte[] antennaParameter = new byte[antennaPatternLength];
		dis.readFully( antennaParameter );
		setAntennaPattern( antennaPatternType, antennaParameter );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeContent( DISOutputStream dos ) throws IOException
	{
		super.writeContent( dos );
		
		radioEntityType.write( dos );
		dos.writeUI8( transmitState );
		dos.writeUI8( inputSource );
		
		dos.writePadding16();
		
		antennaLocation.write( dos );
		dos.writeUI16( antennaPatternType );
		// This will never be beyond the bounds of a UI16 due to input verification in the setter
		dos.writeUI16( antennaPatternParameter.length );
		transmissionFrequency.write( dos );
		dos.writeFloat( transmissionFrequencyBandwidth );
		dos.writeFloat( power );
		modulationType.write( dos );
		dos.writeUI16( cryptoSystem );
		dos.writeUI16( cryptoKey );
				
		// This will never be beyond the bounds of a UI8 due to input verification in the setter
		dos.writeUI8( (short)modulationParameter.length );
		
		dos.writePadding24();
		dos.write( modulationParameter );
		dos.write( antennaPatternParameter );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getContentLength()
	{
		int size = super.getContentLength();
		
		size += radioEntityType.getByteLength();
		size += TransmitState.BYTE_LENGTH;
		size += InputSource.BYTE_LENGTH;
		size += 2;										// Padding
		size += antennaLocation.getByteLength();
		size += AntennaPatternType.BYTE_LENGTH;
		size += DISSizes.UI16_SIZE;				// Antenna Pattern Parameter Length
		size += transmissionFrequency.getByteLength();
		size += DISSizes.FLOAT32_SIZE;						// Bandwidth
		size += DISSizes.FLOAT32_SIZE;						// Power
		size += modulationType.getByteLength();
		size += CryptoSystem.BYTE_LENGTH;
		size += DISSizes.UI16_SIZE;				// Crypto Key
		size += DISSizes.UI8_SIZE;				// Modulation Parameter Length
		size += 3;										// Padding
		size += modulationParameter.length;
		size += antennaPatternParameter.length;
		
		return size;
	}
	
	public RadioEntityType getRadioEntityType()
	{
		return radioEntityType;
	}
	
	public void setRadioEntityType( RadioEntityType radioEntityType )
	{
		this.radioEntityType = radioEntityType;
	}
	
	public short getTransmitState()
	{
		return transmitState;
	}
		
	public void setTransmitState( short transmitState )
	{
		this.transmitState = transmitState;
	}
	
	public short getInputSource()
	{
		return inputSource;
	}
	
	public void setInputSource( short inputSource )
	{
		this.inputSource = inputSource;
	}
	
	public int getAntennaPatternType()
	{
		return antennaPatternType;
	}
	
	public byte[] getAntennaParameter()
	{
		return antennaPatternParameter;
	}
	
	public void setAntennaPattern( int antennaPatternType, byte[] antennaParameter )
	{
		int antennaParameterLength = antennaParameter.length;
		if( (antennaParameterLength % 8) != 0 )
		{
			throw new IllegalArgumentException( "Antenna Parameter BLOB must be aligned to 64bit boundary" );
		}
		else if( antennaParameterLength > DISSizes.UI16_MAX_VALUE )
		{
			throw new IllegalArgumentException( "Antenna Parameter BLOB may not be larger than " + 
				DISSizes.UI16_MAX_VALUE + "bytes" );
		}
		
		this.antennaPatternType = antennaPatternType;
		this.antennaPatternParameter = antennaParameter;
	}
	
	public AntennaLocation getAntennaLocation()
	{
		return antennaLocation;
	}
	
	public void setAntennaLocation( AntennaLocation antennaLocation )
	{
		this.antennaLocation = antennaLocation;
	}
	
	public DISUnsignedInt64 getTransmissionFrequency()
	{
		return transmissionFrequency;
	}
	
	public void setTransmissionFrequency( DISUnsignedInt64 transmissionFrequency )
	{
		this.transmissionFrequency = transmissionFrequency;
	}
	
	public float getTransmissionFrequencyBandwidth()
	{
		return transmissionFrequencyBandwidth;
	}
	
	public void setTransmissionFrequencyBandwitch( float transmissionFrequencyBandwidth )
	{
		this.transmissionFrequencyBandwidth = transmissionFrequencyBandwidth;
	}
	
	public float getPower()
	{
		return power;
	}
	
	public void setPower( float power )
	{
		this.power = power;
	}
	
	public ModulationType getModulationType()
	{
		return modulationType;
	}
	
	public byte[] getModulationParameter()
	{
		return modulationParameter;
	}
	
	public void setModulation( ModulationType modulationType, byte[] modulationParameter )
	{
		int parameterLength = modulationParameter.length;
		if( (parameterLength % 8) != 0 )
		{
			throw new IllegalArgumentException( "Modulation Parameter BLOB must be aligned to "+
			                                 "64bit boundary" );
		}
		else if( parameterLength > DISSizes.UI8_MAX_VALUE )
		{
			throw new IllegalArgumentException( "Modulation Parameter BLOB may not be larger than " + 
				DISSizes.UI8_MAX_VALUE + "bytes" );
		}
		
		this.modulationType = modulationType;
		this.modulationParameter = modulationParameter;
	}
	
	public int getCryptoSystem()
	{
		return cryptoSystem;
	}
	
	public void setCryptoSystem( int cryptoSystem )
	{
		this.cryptoSystem = cryptoSystem;
	}
	
	public int getCryptoKey()
	{
		return cryptoKey;
	}
	
	public void setCryptoKey( int cryptoKey )
	{
		this.cryptoKey = cryptoKey;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
