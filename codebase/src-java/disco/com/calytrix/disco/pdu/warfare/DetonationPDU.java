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
package com.calytrix.disco.pdu.warfare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.field.DetonationResult;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.ArticulationParameter;
import com.calytrix.disco.pdu.record.BurstDescriptor;
import com.calytrix.disco.pdu.record.EntityCoordinate;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.VectorRecord;
import com.calytrix.disco.pdu.record.WorldCoordinate;
import com.calytrix.disco.util.DISSizes;

/**
 * This class represents an Detonation PDU. A Detonation PDU is raised when an ordinate detonates
 * in the simulated world, and is usually traceable to a Fire PDU.
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.3.3"
 */
public class DetonationPDU extends AbstractWarfarePDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------	
	private VectorRecord velocity;
	private WorldCoordinate locationInWorld;
	private BurstDescriptor burstDescriptor;
	private EntityCoordinate locationInEntityCoordinates;
	private short detonationResult;
	private List<ArticulationParameter> articulationParameters;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type DetonationPDU with specified PDUHeader
	 * 
	 * @param header The <code>PDUHeader</code> to base this PDU on
	 */
	public DetonationPDU( PDUHeader header )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.DETONATION )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		

		this.velocity = new VectorRecord();
		this.locationInWorld = new WorldCoordinate();
		this.burstDescriptor = new BurstDescriptor();
		this.locationInEntityCoordinates = new EntityCoordinate();
		this.detonationResult = DetonationResult.OTHER;
		this.articulationParameters = new ArrayList<ArticulationParameter>();
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
		
		velocity.read( dis );
		locationInWorld.read( dis );
		burstDescriptor.read( dis );
		locationInEntityCoordinates.read( dis );
		detonationResult = dis.readUI8();
		
		short numberOfArticulationParameters = dis.readUI8();
		dis.skip16(); // Skip over padding
		
		articulationParameters.clear();
		for( int i = 0; i < numberOfArticulationParameters; ++i )
		{
			ArticulationParameter parameter = new ArticulationParameter();
			parameter.read( dis );
			articulationParameters.add( parameter );
		}
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void writeContent( DISOutputStream dos ) throws IOException
    {
		super.writeContent( dos );
		
		velocity.write( dos );
		locationInWorld.write( dos );
		burstDescriptor.write( dos );
		locationInEntityCoordinates.write( dos );
		dos.writeUI8( detonationResult );
		
		int parameterCountRaw = articulationParameters.size();
		if( parameterCountRaw > DISSizes.UI8_MAX_VALUE )
		{
			// TODO Warn about truncation
		}
		
		short parameterCount = (short)parameterCountRaw;
		dos.writeUI8( parameterCount );
		dos.writePadding16();
		
		for( ArticulationParameter parameter : articulationParameters )
			parameter.write( dos );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getContentLength()
	{
		int size = super.getContentLength();
		size += velocity.getByteLength();
		size += locationInWorld.getByteLength();
		size += burstDescriptor.getByteLength();
		size += locationInEntityCoordinates.getByteLength();
		size += DetonationResult.BYTE_LENGTH;
		size += DISSizes.UI8_SIZE;		// Parameter Count
		size += 2;						// Padding
		size += DISSizes.getByteLengthOfCollection( articulationParameters );
		
		return size;
		
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
	public VectorRecord getVelocity()
    {
    	return velocity;
    }

	public void setVelocity( VectorRecord velocity )
    {
    	this.velocity = velocity;
    }

	public WorldCoordinate getLocationInWorld()
    {
    	return locationInWorld;
    }

	public void setLocationInWorld( WorldCoordinate locationInWorld )
    {
    	this.locationInWorld = locationInWorld;
    }

	public BurstDescriptor getBurstDescriptor()
    {
    	return burstDescriptor;
    }

	public void setBurstDescriptor( BurstDescriptor burstDescriptor )
    {
    	this.burstDescriptor = burstDescriptor;
    }

	public EntityCoordinate getLocationInEntityCoordinates()
    {
    	return locationInEntityCoordinates;
    }

	public void setLocationInEntityCoordinates( EntityCoordinate locationInEntityCoordinates )
    {
    	this.locationInEntityCoordinates = locationInEntityCoordinates;
    }

	public short getDetonationResult()
    {
    	return detonationResult;
    }

	public void setDetonationResult( short detonationResult )
    {
    	this.detonationResult = detonationResult;
    }

	public List<ArticulationParameter> getArticulationParameters()
    {
    	return articulationParameters;
    }

	public void setArticulationParameter( List<ArticulationParameter> articulationParameters )
    {
    	this.articulationParameters = articulationParameters;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
