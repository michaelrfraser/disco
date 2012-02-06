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
 * An Entity's simulation address shall be specified by a Simulation Address 
 * Record. A Simulation Address record shall consist of the Site ID number and 
 * the Application ID number.
 */
public class SimulationAddress implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int ALL_SITES = 0xFFFF;
	public static final int ALL_APPLIC = 0xFFFF;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private int siteIdentifier;
	private int applicationIdentifier;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public SimulationAddress()
	{
		this( 0, 0 );
	}
	
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

		if( other instanceof SimulationAddress )
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SimulationAddress clone()
	{
		return new SimulationAddress( siteIdentifier, applicationIdentifier );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		siteIdentifier = dis.readUI16();
		applicationIdentifier = dis.readUI16();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI16( siteIdentifier );
		dos.writeUI16( applicationIdentifier );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return DISSizes.UI16_SIZE * 2;
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
}
