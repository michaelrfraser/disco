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

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.ArticulationParameter;
import com.calytrix.disco.pdu.record.BurstDescriptor;
import com.calytrix.disco.pdu.record.EntityCoordinate;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.EventIdentifier;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.VectorRecord;
import com.calytrix.disco.pdu.record.WorldCoordinate;

/**
 * This class represents an Detonation PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.3.3"
 */
public class DetonationPDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	private static final int DETONATION_BASE_SIZE = 832;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------	
	private PDUHeader header;
	private EntityIdentifier firingEntityID;
	private EntityIdentifier targetEntityID;
	private EntityIdentifier munitionID;
	private EventIdentifier eventID;
	private VectorRecord velocity;
	private WorldCoordinate locationInWorld;
	private BurstDescriptor burstDescriptor;
	private EntityCoordinate locationInEntityCoordinates;
	private short detonationResult;
	private ArrayList<ArticulationParameter> articulationParameter;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public DetonationPDU( PDUHeader header,
	                      EntityIdentifier firingEntityID,
	                      EntityIdentifier targetEntityID,
	                      EntityIdentifier munitionID,
	                      EventIdentifier eventID,
	                      VectorRecord velocity,
	                      WorldCoordinate locationInWorld,
	                      BurstDescriptor burstDescriptor,
	                      EntityCoordinate locationInEntityCoordinates,
	                      short detonationResult,
	                      ArrayList<ArticulationParameter> articulationParameter )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.DETONATION )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		this.header = header;
		this.firingEntityID = firingEntityID;
		this.targetEntityID = targetEntityID;
		this.munitionID = munitionID;
		this.eventID = eventID;
		this.velocity = velocity;
		this.locationInWorld = locationInWorld;
		this.burstDescriptor = burstDescriptor;
		this.locationInEntityCoordinates = locationInEntityCoordinates;
		this.detonationResult = detonationResult;
		setArticulationParameter( articulationParameter );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	
	
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
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

	public ArrayList<ArticulationParameter> getArticulationParameter()
    {
    	return articulationParameter;
    }

	public void setArticulationParameter( ArrayList<ArticulationParameter> articulationParameter )
    {
    	this.articulationParameter = articulationParameter;
    	PDUHeader header = getHeader();
		header.setLength( DETONATION_BASE_SIZE +
		                  (articulationParameter.size() * ArticulationParameter.ARTICULATION_PARAMETER_SIZE) );
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	public static DetonationPDU read( PDUHeader header, DISInputStream dis ) throws IOException
	{
		EntityIdentifier firingEntityID = EntityIdentifier.read( dis );
		EntityIdentifier targetEntityID = EntityIdentifier.read( dis );
		EntityIdentifier munitionID = EntityIdentifier.read( dis );
		EventIdentifier eventID = EventIdentifier.read( dis );
		VectorRecord velocity = VectorRecord.read( dis );
		WorldCoordinate locationInWorld = WorldCoordinate.read( dis );
		BurstDescriptor burstDescriptor = BurstDescriptor.read( dis );
		EntityCoordinate locationInEntityCoordinates = EntityCoordinate.read( dis );
		short detonationResult = dis.readUI8();
		short numberOfArticulationParameters = dis.readUI8();
		dis.skip16(); // Skip over padding
		ArrayList<ArticulationParameter> articulationParameters = new ArrayList<ArticulationParameter>(numberOfArticulationParameters);
		for( int i = 0; i < numberOfArticulationParameters; i++ )
		{
			articulationParameters.add( ArticulationParameter.read( dis ) );
		}
		
		return new DetonationPDU( header,
		                          firingEntityID,
		                          targetEntityID,
		                          munitionID,
		                          eventID,
		                          velocity,
		                          locationInWorld,
		                          burstDescriptor,
		                          locationInEntityCoordinates, 
		                          detonationResult,
		                          articulationParameters );
	}
}
