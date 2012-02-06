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
package com.calytrix.disco.pdu.record;

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.IPDUComponent;
import com.calytrix.disco.util.DISSizes;

/**
 * Each Entity in a given exercise executing on a DIS application shall be 
 * assigned an Entity Identifier Record Unique to the exercise.
 */
public class EntityIdentifier implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int ALL_ENTITIES = 0xFFFF;
		
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private SimulationAddress simulationAddress;
	private int entityIdentity;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityIdentifier()
	{
		this( new SimulationAddress(), 0 );
	}
	
	public EntityIdentifier( SimulationAddress simulationAddress, int entityIdentity )
	{
		this.simulationAddress = simulationAddress;
		this.entityIdentity = entityIdentity;
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals( Object other )
	{
		if( other == this )
			return true;
		
		if( other instanceof EntityIdentifier )
		{
			EntityIdentifier asEntityIdentifier = (EntityIdentifier)other;
			if( asEntityIdentifier.simulationAddress.equals(this.simulationAddress) &&
				asEntityIdentifier.entityIdentity == entityIdentity )
			{
				return true;
			}
		}

		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityIdentifier clone()
	{
		SimulationAddress simulationAddressClone = simulationAddress.clone();
		
		return new EntityIdentifier( simulationAddressClone, entityIdentity );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		simulationAddress.read( dis );
		entityIdentity = dis.readUI16();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		simulationAddress.write( dos );
		dos.writeUI16( entityIdentity );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = simulationAddress.getByteLength();
		size += DISSizes.UI16_SIZE;
		return size;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public SimulationAddress getSimulationAddress()
	{
		return simulationAddress;
	}
	
	public void setSimulationAddress( SimulationAddress simulationAddress )
	{
		this.simulationAddress = simulationAddress;
	}
	
	public int getEntityIdentity()
	{
		return entityIdentity;
	}
	
	public void setEntityIdentity( int entityIdentity )
	{
		this.entityIdentity = entityIdentity;
	}

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
