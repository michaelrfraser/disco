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
 * The event identification shall be specified by the Event Identifier Record. The record shall
 * consist of a Simulation Address Record and an Event Number. The latter is uniquely assigned
 * within the host by the simulation application that initiates the sequence of events. The Event
 * Identifier Record shall be set to one for each exercise and incremented by one for each fire
 * event or collision event. In the case where all possible values are exhausted, the numbers may
 * be reused, beginning at one.
 */
public class EventIdentifier
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
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EventIdentifier deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EventIdentifier read( DISInputStream dis ) throws IOException
	{
		SimulationAddress simulationAddress = SimulationAddress.read( dis );
		int eventID = dis.readUI16();
		
		return new EventIdentifier( simulationAddress, eventID );
	}

	
}
