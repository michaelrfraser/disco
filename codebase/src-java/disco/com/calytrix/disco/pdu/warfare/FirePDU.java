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
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.BurstDescriptor;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.EventIdentifier;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.Vector;
import com.calytrix.disco.pdu.record.WorldCoordinate;

/**
 * This class represents an Fire PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.3.2"
 */
public class FirePDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int FIRE_BASE_SIZE = 768;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private PDUHeader header;
	private EntityIdentifier firingEntityID;
	private EntityIdentifier targetEntityID;
	private EntityIdentifier munitionID;
	private EventIdentifier eventID;
	private long fireMissionIndex;
	private WorldCoordinate locationInWorld;
	private BurstDescriptor burstDescriptor;
	private Vector velocity;
	private float range;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public FirePDU( PDUHeader header, EntityIdentifier firingEntityID,
	                EntityIdentifier targetEntityID, EntityIdentifier munitionID,
	                EventIdentifier eventID, long fireMissionIndex,
	                WorldCoordinate locationInWorld, BurstDescriptor burstDescriptor,
	                Vector velocity, float range )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.FIRE )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		header.setLength( FIRE_BASE_SIZE );
		
		this.header = header;
		this.firingEntityID = firingEntityID;
		this.targetEntityID = targetEntityID;
		this.munitionID = munitionID;
		this.eventID = eventID;
		this.fireMissionIndex = fireMissionIndex;
		this.locationInWorld = locationInWorld;
		this.burstDescriptor = burstDescriptor;
		this.velocity = velocity;
		this.range = range;
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	public PDUHeader getHeader()
    {
    	return header;
    }

	public void setHeader( PDUHeader header )
    {
    	this.header = header;
    }

	public EntityIdentifier getFiringEntityID()
    {
    	return firingEntityID;
    }

	public void setFiringEntityID( EntityIdentifier firingEntityID )
    {
    	this.firingEntityID = firingEntityID;
    }

	public EntityIdentifier getTargetEntityID()
    {
    	return targetEntityID;
    }

	public void setTargetEntityID( EntityIdentifier targetEntityID )
    {
    	this.targetEntityID = targetEntityID;
    }

	public EntityIdentifier getMunitionID()
    {
    	return munitionID;
    }

	public void setMunitionID( EntityIdentifier munitionID )
    {
    	this.munitionID = munitionID;
    }

	public EventIdentifier getEventID()
    {
    	return eventID;
    }

	public void setEventID( EventIdentifier eventID )
    {
    	this.eventID = eventID;
    }

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

	public Vector getVelocity()
    {
    	return velocity;
    }

	public void setVelocity( Vector velocity )
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
	public static FirePDU read( PDUHeader header, DISInputStream dis ) throws IOException
	{
		EntityIdentifier firingEntityID = EntityIdentifier.read( dis );
		EntityIdentifier targetEntityID = EntityIdentifier.read( dis );
		EntityIdentifier munitionID = EntityIdentifier.read( dis );
		EventIdentifier eventID = EventIdentifier.read( dis );
		long fireMissionIndex = dis.readUI32();
		WorldCoordinate locationInWorld = WorldCoordinate.read( dis );
		BurstDescriptor burstDescriptor = BurstDescriptor.read( dis );
		Vector velocity = Vector.read( dis );
		float range = dis.readFloat();		
	
		return new FirePDU( header, firingEntityID, targetEntityID, munitionID, eventID,
		                    fireMissionIndex, locationInWorld, burstDescriptor, velocity, range );
	}
}
