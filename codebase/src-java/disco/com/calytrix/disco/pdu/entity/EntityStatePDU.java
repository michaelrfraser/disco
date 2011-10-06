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
package com.calytrix.disco.pdu.entity;

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.ArticulationParameter;
import com.calytrix.disco.pdu.record.DeadReckoningParameter;
import com.calytrix.disco.pdu.record.EntityAppearance;
import com.calytrix.disco.pdu.record.EntityCapabilities;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.EntityMarking;
import com.calytrix.disco.pdu.record.EntityType;
import com.calytrix.disco.pdu.record.EulerAngles;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.Vector;
import com.calytrix.disco.pdu.record.WorldCoordinate;

/**
 * This class represents an EntityState PDU.
 * <p/>
 * PDUs of this type contain information about the state of an entity within a distributed
 * simulation.
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.2.1"
 */
public class EntityStatePDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int ENTITY_STATE_BASE_SIZE = 1152;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private PDUHeader pduHeader;
	private EntityIdentifier entityID;
	private short forceID;
	private EntityType entityType;
	private EntityType alternativeEntityType;
	private Vector entityLinearVelocity;
	private WorldCoordinate entityLocation;
	private EulerAngles entityOrientation;
	private EntityAppearance entityAppearance;
	private DeadReckoningParameter deadReckoningParameters;
	private EntityMarking entityMarking;
	private EntityCapabilities entityCapabilities;
	private ArticulationParameter[] articulationParameter;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityStatePDU( PDUHeader pduHeader, EntityIdentifier entityID, short forceID,
	                       EntityType entityType, EntityType alternativeEntityType,
	                       Vector entityLinearVelocity, WorldCoordinate entityLocation,
	                       EulerAngles entityOrientation, EntityAppearance entityAppearance,
	                       DeadReckoningParameter deadReckoningParameter,
	                       EntityMarking entityMarking, EntityCapabilities entityCapabilities,
	                       ArticulationParameter[] articulationParameter )
	{
		super( pduHeader );
		
		if ( pduHeader.getPDUType() != PDUType.ENTITY_STATE )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		this.pduHeader = pduHeader;
		this.entityID = entityID;
		this.forceID = forceID;
		this.entityType = entityType;
		this.alternativeEntityType = alternativeEntityType;
		this.entityLinearVelocity = entityLinearVelocity;
		this.entityLocation = entityLocation;
		this.entityOrientation = entityOrientation;
		this.entityAppearance = entityAppearance;
		this.deadReckoningParameters = deadReckoningParameter;
		this.entityMarking = entityMarking;
		this.entityCapabilities = entityCapabilities;
		setArticulationParameter( articulationParameter );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	public PDUHeader getPduHeader()
    {
    	return pduHeader;
    }

	public void setPduHeader( PDUHeader pduHeader )
    {
    	this.pduHeader = pduHeader;
    }

	public EntityIdentifier getEntityID()
    {
    	return entityID;
    }

	public void setEntityID( EntityIdentifier entityID )
    {
    	this.entityID = entityID;
    }

	public short getForceID()
    {
    	return forceID;
    }

	public void setForceID( short forceID )
    {
    	this.forceID = forceID;
    }

	public EntityType getEntityType()
    {
    	return entityType;
    }

	public void setEntityType( EntityType entityType )
    {
    	this.entityType = entityType;
    }

	public EntityType getAlternativeEntityType()
    {
    	return alternativeEntityType;
    }

	public void setAlternativeEntityType( EntityType alternativeEntityType )
    {
    	this.alternativeEntityType = alternativeEntityType;
    }

	public Vector getEntityLinearVelocity()
    {
    	return entityLinearVelocity;
    }

	public void setEntityLinearVelocity( Vector entityLinearVelocity )
    {
    	this.entityLinearVelocity = entityLinearVelocity;
    }

	public WorldCoordinate getEntityLocation()
    {
    	return entityLocation;
    }

	public void setEntityLocation( WorldCoordinate entityLocation )
    {
    	this.entityLocation = entityLocation;
    }

	public EulerAngles getEntityOrientation()
    {
    	return entityOrientation;
    }

	public void setEntityOrientation( EulerAngles entityOrientation )
    {
    	this.entityOrientation = entityOrientation;
    }

	public EntityAppearance getEntityAppearance()
    {
    	return entityAppearance;
    }

	public void setEntityAppearance( EntityAppearance entityAppearance )
    {
    	this.entityAppearance = entityAppearance;
    }

	public DeadReckoningParameter getDeadReckoningParameters()
    {
    	return deadReckoningParameters;
    }

	public void setDeadReckoningParameters( DeadReckoningParameter deadReckoningParameters )
    {
    	this.deadReckoningParameters = deadReckoningParameters;
    }

	public EntityMarking getEntityMarking()
    {
    	return entityMarking;
    }

	public void setEntityMarking( EntityMarking entityMarking )
    {
    	this.entityMarking = entityMarking;
    }

	public EntityCapabilities getEntityCapabilities()
    {
    	return entityCapabilities;
    }

	public void setEntityCapabilities( EntityCapabilities entityCapabilities )
    {
    	this.entityCapabilities = entityCapabilities;
    }
	
	public ArticulationParameter[] getArticulationParameter()
    {
    	return articulationParameter;
    }

	public void setArticulationParameter( ArticulationParameter[] articulationParameter )
    {		
		this.articulationParameter = articulationParameter;
		
		PDUHeader header = getHeader();
		header.setLength( ENTITY_STATE_BASE_SIZE +
		                  (articulationParameter.length * ArticulationParameter.ARTICULATION_PARAMETER_SIZE) );
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	public static EntityStatePDU read( PDUHeader header, DISInputStream dis ) throws IOException
	{
		EntityIdentifier entityID = EntityIdentifier.read( dis );
		short forceID = dis.readUI8();
		short numberOfArticulationParameters = dis.readUI8();
		EntityType entityType = EntityType.read( dis );
		EntityType alternativeEntityType = EntityType.read( dis );
		Vector entityLinearVelocity = Vector.read( dis );
		WorldCoordinate entityLocation = WorldCoordinate.read( dis );
		EulerAngles entityOrientation = EulerAngles.read( dis );
		EntityAppearance entityAppearance = EntityAppearance.read( dis );
		DeadReckoningParameter deadReckoningParameters = DeadReckoningParameter.read( dis );
		EntityMarking entityMarking = EntityMarking.read( dis );
		EntityCapabilities entityCapabilities = EntityCapabilities.read( dis );
		ArticulationParameter[] articulationParameters = new ArticulationParameter[numberOfArticulationParameters];
		for( int i = 0; i < numberOfArticulationParameters; i++ )
		{
			articulationParameters[i] = ArticulationParameter.read( dis );
		}
		
		return new EntityStatePDU( header, entityID, forceID, entityType, alternativeEntityType,
		                           entityLinearVelocity, entityLocation, entityOrientation,
		                           entityAppearance, deadReckoningParameters, entityMarking,
		                           entityCapabilities, articulationParameters );
	}
}
