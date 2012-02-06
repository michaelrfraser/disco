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
 * The event identification shall be specified by the Event Identifier Record. The record shall
 * consist of a Simulation Address Record and an Event Number. The latter is uniquely assigned
 * within the host by the simulation application that initiates the sequence of events. The Event
 * Identifier Record shall be set to one for each exercise and incremented by one for each fire
 * event or collision event. In the case where all possible values are exhausted, the numbers may
 * be reused, beginning at one.
 */
public class EventIdentifier implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private SimulationAddress simulationAddress;
	private int eventID;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EventIdentifier()
	{
		this( new SimulationAddress(), 0 );
	}
	
	public EventIdentifier( SimulationAddress simulationAddress, int eventID )
	{
		this.simulationAddress = simulationAddress;
		this.eventID = eventID;
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
		if( this == other )
			return true;

		if( other instanceof EventIdentifier )
		{
			EventIdentifier otherEvent = (EventIdentifier)other;
			if( otherEvent.simulationAddress.equals(this.simulationAddress) &&
			    otherEvent.eventID == this.eventID )
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
	public EventIdentifier clone()
	{
		SimulationAddress addressClone = simulationAddress.clone();
		return new EventIdentifier( addressClone, eventID );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		simulationAddress.read( dis );
		eventID = dis.readUI16();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		simulationAddress.write( dos );
		dos.writeUI16( eventID );
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

	public int getEventID()
    {
    	return eventID;
    }

	public void setEventID( int eventID )
    {
    	this.eventID = eventID;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
