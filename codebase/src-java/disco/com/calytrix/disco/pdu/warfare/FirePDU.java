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

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.BurstDescriptor;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.VectorRecord;
import com.calytrix.disco.pdu.record.WorldCoordinate;
import com.calytrix.disco.util.DISSizes;

/**
 * This class represents an Fire PDU. Fire PDUs represent the firing of ordinates in the Simulated
 * World. The detonation of the ordinates is reported in a corresponding Detonation PDU when the
 * modeled munitions eventually detonate.
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.3.2"
 */
public class FirePDU extends AbstractWarfarePDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private long fireMissionIndex;
	private WorldCoordinate locationInWorld;
	private BurstDescriptor burstDescriptor;
	private VectorRecord velocity;
	private float range;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type FirePDU with specified PDUHeader
	 * 
	 * @param header The <code>PDUHeader</code> to base this PDU on
	 */
	public FirePDU( PDUHeader header )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.FIRE )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
				
		this.fireMissionIndex = 0;
		this.locationInWorld = new WorldCoordinate();
		this.burstDescriptor = new BurstDescriptor();
		this.velocity = new VectorRecord();
		this.range = 0f;
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
		
		fireMissionIndex = dis.readUI32();
		locationInWorld.read( dis );
		burstDescriptor.read( dis );
		velocity.read( dis );
		range = dis.readFloat();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void writeContent( DISOutputStream dos ) throws IOException
    {
		super.writeContent( dos );
		
		dos.writeUI32( fireMissionIndex );
		locationInWorld.write( dos );
		burstDescriptor.write( dos );
		velocity.write( dos );
		dos.writeFloat( range );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getContentLength()
	{
		int size = super.getContentLength();
		size += DISSizes.UI32_SIZE;		// Fire Mission Index
		size += locationInWorld.getByteLength();
		size += burstDescriptor.getByteLength();
		size += velocity.getByteLength();
		size += DISSizes.FLOAT32_SIZE;				// Range
		
		return size;
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
	public long getFireMissionIndex()
    {
    	return fireMissionIndex;
    }

	public void setFireMissionIndex( long fireMissionIndex )
    {
    	this.fireMissionIndex = fireMissionIndex;
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

	public VectorRecord getVelocity()
    {
    	return velocity;
    }

	public void setVelocity( VectorRecord velocity )
    {
    	this.velocity = velocity;
    }

	public float getRange()
    {
    	return range;
    }

	public void setRange( float range )
    {
    	this.range = range;
    }
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
