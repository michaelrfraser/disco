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
 * An Entity's simulation address shall be specified by a Simulation Address 
 * Record. A Simulation Address record shall consist of the Site ID number and 
 * the Application ID number.
 */
public class SimulationAddress
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private int siteIdentifier;
	private int applicationIdentifier;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public SimulationAddress( int siteIdentifier, int applicationIdentifier )
	{
		this.siteIdentifier = siteIdentifier;
		this.applicationIdentifier = applicationIdentifier;
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

		if ( other instanceof SimulationAddress )
		{
			SimulationAddress asSimulationAddress = (SimulationAddress)other;
			if( asSimulationAddress.applicationIdentifier == this.applicationIdentifier &&
				asSimulationAddress.siteIdentifier == this.siteIdentifier )
			{
				return true;
			}
		}
		
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public int getSiteIdentifier()
    {
    	return siteIdentifier;
    }

	public void setSiteIdentifier( int siteIdentifier )
    {
		this.siteIdentifier = siteIdentifier;
    }

	public int getApplicationIdentifier()
    {
    	return applicationIdentifier;
    }

	public void setApplicationIdentifier( int applicationIdentifier )
    {
		this.applicationIdentifier = applicationIdentifier;
    }
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream
	 * 
	 * @param dis The DISInputStream to read the record from
	 * 
	 * @return The SimulationAddress deserialised from the provided input stream
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream
	 */
	public static SimulationAddress read( DISInputStream dis ) throws IOException
	{
		int siteIdentifier = dis.readUI16();
		int applicationIdentifier = dis.readUI16();
		
		return new SimulationAddress( siteIdentifier, applicationIdentifier );
	}
}
