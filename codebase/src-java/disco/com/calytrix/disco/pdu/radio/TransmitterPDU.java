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
import java.math.BigInteger;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.AntennaLocation;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.ModulationType;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.RadioEntityType;

/**
 * This class represents an Transmitter PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.7.2"
 */
public class TransmitterPDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int TRANSMITTER_BASE_SIZE = 104;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityIdentifier entityIdentifier;
	private int radioID;
	private RadioEntityType radioEntityType;
	private short transmitState;
	private short inputSource;
	private AntennaLocation antennaLocation;
	private int antennaPatternType;
	private BigInteger transmissionFrequency;
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
	public TransmitterPDU( PDUHeader header,
	                       EntityIdentifier entityIdentifier,
	                       int radioID,
	                       RadioEntityType radioEntityType, 
	                       short transmitState,
	                       short inputSource, 
	                       AntennaLocation antennaLocation,
	                       int antennaPatternType,
	                       BigInteger transmissionFrequency, 
	                       float transmissionFrequencyBandwidth,
	                       float power,
	                       ModulationType modulationType,
	                       int cryptoSystem, 
	                       int cryptoKey,
	                       byte[] modulationParameter, 
	                       byte[] antennaPatternParameter )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.TRANSMITTER )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		this.entityIdentifier = entityIdentifier;
		this.radioID = radioID;
		this.radioEntityType = radioEntityType;
		this.transmitState = transmitState;
		this.inputSource = inputSource;
		this.antennaLocation = antennaLocation;
		this.transmissionFrequency = transmissionFrequency;
		this.transmissionFrequencyBandwidth = transmissionFrequencyBandwidth;
		this.power = power;
		this.cryptoSystem = cryptoSystem;
		this.cryptoKey = cryptoKey;
		
		setModulation( modulationType, modulationParameter );
		setAntennaPattern( antennaPatternType, antennaPatternParameter );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	private void setPDULength()
	{
		int length = TRANSMITTER_BASE_SIZE;
		if( modulationParameter != null )
			length += modulationParameter.length;
		
		if( antennaPatternParameter != null )
			length += antennaPatternParameter.length;
		
		PDUHeader header = getHeader();
		header.setLength( length );
	}
	
	public EntityIdentifier getEntityIdentifier()
	{
		return entityIdentifier;
	}
	
	public void setEntityIdentifier( EntityIdentifier entityIdentifier )
	{
		this.entityIdentifier = entityIdentifier;
	}
	
	public int getRadioID()
	{
		return radioID;
	}
	
	public void setRadioID( int radioID )
	{
		this.radioID = radioID;
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
		if ( antennaParameter.length % 8 != 0 )
			throw new IllegalStateException( "Antenna Parameter BLOB must be aligned to 64bit boundary" );
		
		this.antennaPatternType = antennaPatternType;
		this.antennaPatternParameter = antennaParameter;
		
		setPDULength();
	}
	
	public AntennaLocation getAntennaLocation()
	{
		return antennaLocation;
	}
	
	public void setAntennaLocation( AntennaLocation antennaLocation )
	{
		this.antennaLocation = antennaLocation;
	}
	
	public BigInteger getTransmissionFrequency()
	{
		return transmissionFrequency;
	}
	
	public void setTransmissionFrequency( BigInteger transmissionFrequency )
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
		if( (modulationParameter.length % 8) != 0 )
		{
			throw new IllegalStateException( "Modulation Parameter BLOB must be aligned to "+
			                                 "64bit boundary" );
		}
		
		this.modulationType = modulationType;
		this.modulationParameter = modulationParameter;
		
		setPDULength();
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
	public static TransmitterPDU read( PDUHeader header, DISInputStream dis ) throws IOException
	{
		EntityIdentifier entityIdentifier = EntityIdentifier.read( dis );
		int radioID = dis.readUI16();
		RadioEntityType radioEntityType = RadioEntityType.read( dis );
		short transmitState = dis.readUI8();
		short inputSource = dis.readUI8();
		
		// Padding
		dis.skip16();
				
		AntennaLocation antennaLocation = AntennaLocation.read( dis );
		int antennaPatternType = dis.readUI16();
		int antennaPatternLength = dis.readUI16();
		BigInteger transmissionFrequency = dis.readUI64();
		float transmissionFrequencyBandwidth = dis.readFloat();
		float power = dis.readFloat();
		ModulationType modulationType = ModulationType.read( dis );
		int cryptoSystem = dis.readUI16();
		int cryptoKey = dis.readUI16();
		short modulationParametersLength = dis.readUI8();
		
		// Padding
		dis.skip24();
		byte[] modulationParameter = new byte[modulationParametersLength];
		dis.readFully( modulationParameter );
		
		byte[] antennaPatternParameter = new byte[antennaPatternLength];
		dis.readFully( antennaPatternParameter );
		
		return new TransmitterPDU( header,
		                           entityIdentifier,
		                           radioID, 
		                           radioEntityType,
		                           transmitState,
		                           inputSource, 
		                           antennaLocation,
		                           antennaPatternType, 
		                           transmissionFrequency, 
		                           transmissionFrequencyBandwidth,
		                           power, 
		                           modulationType,
		                           cryptoSystem,
		                           cryptoKey, 
		                           modulationParameter,
		                           antennaPatternParameter );
	}
}
