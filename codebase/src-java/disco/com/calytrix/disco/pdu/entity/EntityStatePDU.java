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
import java.util.ArrayList;
import java.util.List;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.field.ForceID;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.ArticulationParameter;
import com.calytrix.disco.pdu.record.DeadReckoningParameter;
import com.calytrix.disco.pdu.record.EntityCapabilities;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.EntityMarking;
import com.calytrix.disco.pdu.record.EntityType;
import com.calytrix.disco.pdu.record.EulerAngles;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.VectorRecord;
import com.calytrix.disco.pdu.record.WorldCoordinate;
import com.calytrix.disco.util.DISSizes;

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

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityIdentifier entityID;
	private short forceID;
	private EntityType entityType;
	private EntityType alternativeEntityType;
	private VectorRecord entityLinearVelocity;
	private WorldCoordinate entityLocation;
	private EulerAngles entityOrientation;
	private int entityAppearance;
	private DeadReckoningParameter deadReckoningParameters;
	private EntityMarking entityMarking;
	private EntityCapabilities entityCapabilities;
	private List<ArticulationParameter> articulationParameters;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for EntityStatePDU with provided PDUHeader
	 * 
	 * @param pduHeader The PDUHeader that this PDU will wrap 
	 */
	public EntityStatePDU( PDUHeader pduHeader )
	{
		super( pduHeader );
		
		if( pduHeader.getPDUType() != PDUType.ENTITY_STATE )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
		
		this.entityID = new EntityIdentifier();
		this.forceID = ForceID.OTHER;
		this.entityType = new EntityType();
		this.alternativeEntityType = new EntityType();
		this.entityLinearVelocity = new VectorRecord();
		this.entityLocation = new WorldCoordinate();
		this.entityOrientation = new EulerAngles();
		this.entityAppearance = 0;
		this.deadReckoningParameters = new DeadReckoningParameter();
		this.entityMarking = new EntityMarking();
		this.entityCapabilities = new EntityCapabilities();
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
		articulationParameters.clear();
		
		entityID.read( dis );
		forceID = dis.readUI8();
		short numberOfArticulationParameters = dis.readUI8();
		entityType.read( dis );
		alternativeEntityType.read( dis );
		entityLinearVelocity.read( dis );
		entityLocation.read( dis );
		entityOrientation.read( dis );
		entityAppearance = dis.readInt();
		deadReckoningParameters.read( dis );
		entityMarking.read( dis );
		entityCapabilities.read( dis );
		
		articulationParameters.clear();
		for( int i = 0; i < numberOfArticulationParameters; ++i )
		{
			ArticulationParameter articulationParameter = new ArticulationParameter();
			articulationParameter.read( dis );
			articulationParameters.add( articulationParameter );
		}
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public void writeContent( DISOutputStream dos ) throws IOException
	{
		entityID.write( dos );
		dos.writeUI8( forceID );
		
		int articulationParamCount = articulationParameters.size();
		if ( articulationParamCount > DISSizes.UI8_MAX_VALUE )
		{
			// TODO Warn about truncation
		}
		
		short paramCountAsShort = (short)articulationParamCount;
		dos.writeUI8( paramCountAsShort );
		entityType.write( dos );
		alternativeEntityType.write( dos );
		entityLinearVelocity.write( dos );
		entityLocation.write( dos );
		entityOrientation.write( dos );
		dos.writeInt( entityAppearance );
		deadReckoningParameters.write( dos );
		entityMarking.write( dos );
		entityCapabilities.write( dos );
		
		for ( ArticulationParameter parameter : articulationParameters )
			parameter.write( dos );
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public int getContentLength()
	{
		int size = entityID.getByteLength();
		size += ForceID.BYTE_LENGTH;
		size += DISSizes.UI8_SIZE;				// ParamCount
		size += entityType.getByteLength();
		size += alternativeEntityType.getByteLength();
		size += entityLinearVelocity.getByteLength();
		size += entityLocation.getByteLength();
		size += entityOrientation.getByteLength();
		size += DISSizes.UI32_SIZE;				// Appearance
		size += deadReckoningParameters.getByteLength();
		size += entityMarking.getByteLength();
		size += entityCapabilities.getByteLength();
		size += DISSizes.getByteLengthOfCollection( articulationParameters );
		
		return size;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
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

	public VectorRecord getEntityLinearVelocity()
    {
    	return entityLinearVelocity;
    }

	public void setEntityLinearVelocity( VectorRecord entityLinearVelocity )
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

	public int getEntityAppearance()
    {
    	return entityAppearance;
    }

	public void setEntityAppearance( int entityAppearance )
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
	
	public List<ArticulationParameter> getArticulationParameter()
    {
    	return articulationParameters;
    }

	public void setArticulationParameter( List<ArticulationParameter> articulationParameter )
    {
		if( articulationParameter.size() > DISSizes.UI8_MAX_VALUE )
			throw new IllegalArgumentException( "A maximum of " + DISSizes.UI8_MAX_VALUE + 
			                                    " articulation parameters are supported by the DIS specification" );
			
		this.articulationParameters = articulationParameter;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
