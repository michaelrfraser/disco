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
	private EntityIdentifier m_entityIdentifier;
	private int m_radioID;
	private RadioEntityType m_radioEntityType;
	private short m_transmitState;
	private short m_inputSource;
	private AntennaLocation m_antennaLocation;
	private int m_antennaPatternType;
	private BigInteger m_transmissionFrequency;
	private float m_transmissionFrequencyBandwidth;
	private float m_power;
	private ModulationType m_modulationType;
	private int m_cryptoSystem;
	private int m_cryptoKey;
	private byte[] m_modulationParameter;
	private byte[] m_antennaPatternParameter;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public TransmitterPDU( PDUHeader header, EntityIdentifier entityIdentifier,
	                       int radioID, RadioEntityType radioEntityType, 
	                       short transmitState, short inputSource, 
	                       AntennaLocation antennaLocation, int antennaPatternType,
	                       BigInteger transmissionFrequency, 
	                       float transmissionFrequencyBandwidth, float power,
	                       ModulationType modulationType, int cryptoSystem, 
	                       int cryptoKey, byte[] modulationParameter, 
	                       byte[] antennaPatternParameter )
	{
		super( header );
		
		if ( header.getPDUType() != PDUType.TRANSMITTER )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		m_entityIdentifier = entityIdentifier;
		m_radioID = radioID;
		m_radioEntityType = radioEntityType;
		m_transmitState = transmitState;
		m_inputSource = inputSource;
		m_antennaLocation = antennaLocation;
		m_transmissionFrequency = transmissionFrequency;
		m_transmissionFrequencyBandwidth = transmissionFrequencyBandwidth;
		m_power = power;
		m_cryptoSystem = cryptoSystem;
		m_cryptoKey = cryptoKey;
		
		setModulation( modulationType, modulationParameter );
		setAntennaPattern( antennaPatternType, antennaPatternParameter );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	private void setPDULength()
	{
		int length = TRANSMITTER_BASE_SIZE;
		if ( m_modulationParameter != null )
			length += m_modulationParameter.length;
		
		if ( m_antennaPatternParameter != null )
			length += m_antennaPatternParameter.length;
		
		PDUHeader header = getHeader();
		header.setLength( length );
	}
	
	public EntityIdentifier getEntityIdentifier()
	{
		return m_entityIdentifier;
	}
	
	public void setEntityIdentifier( EntityIdentifier entityIdentifier )
	{
		m_entityIdentifier = entityIdentifier;
	}
	
	public int getRadioID()
	{
		return m_radioID;
	}
	
	public void setRadioID( int radioID )
	{
		m_radioID = radioID;
	}
	
	public RadioEntityType getRadioEntityType()
	{
		return m_radioEntityType;
	}
	
	public void setRadioEntityType( RadioEntityType radioEntityType )
	{
		m_radioEntityType = radioEntityType;
	}
	
	public short getTransmitState()
	{
		return m_transmitState;
	}
		
	public void setTransmitState( short transmitState )
	{
		m_transmitState = transmitState;
	}
	
	public short getInputSource()
	{
		return m_inputSource;
	}
	
	public void setInputSource( short inputSource )
	{
		m_inputSource = inputSource;
	}
	
	public int getAntennaPatternType()
	{
		return m_antennaPatternType;
	}
	
	public byte[] getAntennaParameter()
	{
		return m_antennaPatternParameter;
	}
	
	public void setAntennaPattern( int antennaPatternType, byte[] antennaParameter )
	{
		if ( antennaParameter.length % 8 != 0 )
			throw new IllegalStateException( "Antenna Parameter BLOB must be aligned to 64bit boundary" );
		
		m_antennaPatternType = antennaPatternType;
		m_antennaPatternParameter = antennaParameter;
		
		setPDULength();
	}
	
	public AntennaLocation getAntennaLocation()
	{
		return m_antennaLocation;
	}
	
	public void setAntennaLocation( AntennaLocation antennaLocation )
	{
		m_antennaLocation = antennaLocation;
	}
	
	public BigInteger getTransmissionFrequency()
	{
		return m_transmissionFrequency;
	}
	
	public void setTransmissionFrequency( BigInteger transmissionFrequency )
	{
		m_transmissionFrequency = transmissionFrequency;
	}
	
	public float getTransmissionFrequencyBandwidth()
	{
		return m_transmissionFrequencyBandwidth;
	}
	
	public void setTransmissionFrequencyBandwitch( float transmissionFrequencyBandwidth )
	{
		m_transmissionFrequencyBandwidth = transmissionFrequencyBandwidth;
	}
	
	public float getPower()
	{
		return m_power;
	}
	
	public void setPower( float power )
	{
		m_power = power;
	}
	
	public ModulationType getModulationType()
	{
		return m_modulationType;
	}
	
	public byte[] getModulationParameter()
	{
		return m_modulationParameter;
	}
	
	public void setModulation( ModulationType modulationType, byte[] modulationParameter )
	{
		if ( modulationParameter.length % 8 != 0 )
			throw new IllegalStateException( "Modulation Parameter BLOB must be aligned to 64bit boundary" );
		
		m_modulationType = modulationType;
		m_modulationParameter = modulationParameter;
		
		setPDULength();
	}
	
	public int getCryptoSystem()
	{
		return m_cryptoSystem;
	}
	
	public void setCryptoSystem( int cryptoSystem )
	{
		m_cryptoSystem = cryptoSystem;
	}
	
	public int getCryptoKey()
	{
		return m_cryptoKey;
	}
	
	public void setCryptoKey( int cryptoKey )
	{
		m_cryptoKey = cryptoKey;
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
		
		return new TransmitterPDU( header, entityIdentifier, radioID, 
		                        radioEntityType, transmitState, inputSource, 
		                        antennaLocation, antennaPatternType, 
		                        transmissionFrequency, 
		                        transmissionFrequencyBandwidth, power, 
		                        modulationType, cryptoSystem, cryptoKey, 
		                        modulationParameter, antennaPatternParameter );
	}
}
