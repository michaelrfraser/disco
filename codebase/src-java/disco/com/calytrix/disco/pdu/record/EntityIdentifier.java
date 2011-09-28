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

/**
 * Each Entity in a given exercise executing on a DIS application shall be 
 * assigned an Entity Identifier Record Unique to the exercise.
 */
public class EntityIdentifier
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private SimulationAddress simulationAddress;
	private int entityIdentity;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
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
		boolean equal = false;
		
		if ( other == this )
		{
			equal = true;
		}
		else
		{
			if ( other instanceof EntityIdentifier )
			{
				EntityIdentifier asEntityIdentifier = (EntityIdentifier)other;
				equal = asEntityIdentifier.simulationAddress.equals( this.simulationAddress ) &&
					asEntityIdentifier.entityIdentity == entityIdentity;
			}
		}
		
		return equal;
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
	/**
	 * Reads an instance of this record from the provided DISInputStream
	 * 
	 * @param dis The DISInputStream to read the record from
	 * 
	 * @return The AntennaLocation deserialised from the provided input stream
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream
	 */
	public static EntityIdentifier read( DISInputStream dis ) throws IOException
	{
		SimulationAddress simulationAddress = SimulationAddress.read( dis );
		int entityIdentity = dis.readUI16();
		
		return new EntityIdentifier( simulationAddress, entityIdentity );
	}
}
