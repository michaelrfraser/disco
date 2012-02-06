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

/**
 * The location of a radio transmitter's antenna shall be represented using an 
 * Antenna Location Record. This record shall specify the location of the 
 * radiating portion of the antenna. The antenna's location is specified in two 
 * different coordinate systems world coordinates and entity coordinates. The 
 * fields of this record are described in the paragraphs that follow.
 * 
 * <ol>
 * 	<li>
 * 		<b>Antenna Location</b> This field shall specify the location of the 
 * 		radiating  portion of the antenna. This field shall be represented by a 
 * 		World Coordinates Record (see 5.3.33).
 * 	</li>
 * 	<li>
 * 		<b>Relative Antenna Location</b> This field shall specify the location 
 * 		of the radiating portion of the antenna. This field shall be represented 
 * 		by an Entity Coordinate Vector Record (see 5.3.32.1)
 * 	</li>
 * </ol>
 * 
 * @see "IEEE Std 1278.1-1995 section 5.3.33"
 * @see "IEEE Std 1278.1-1995 section 5.3.32.1"
 */
public class AntennaLocation implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private WorldCoordinate antennaLocation;
	private EntityCoordinate relativeAntennaLocation;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public AntennaLocation()
	{
		this( new WorldCoordinate(), new EntityCoordinate() );
	}
	
	public AntennaLocation( WorldCoordinate antennaLocation,
	                        EntityCoordinate relativeAntennaLocation )
	{
		this.antennaLocation = antennaLocation;
		this.relativeAntennaLocation = relativeAntennaLocation;
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
		
		if( other instanceof AntennaLocation )
		{
			AntennaLocation asAntennaLocation = (AntennaLocation)other;
			if( asAntennaLocation.antennaLocation.equals(this.antennaLocation) &&
				asAntennaLocation.relativeAntennaLocation.equals(this.relativeAntennaLocation) )
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
	public AntennaLocation clone()
	{
		WorldCoordinate antennaLocationClone = antennaLocation.clone();
		EntityCoordinate relativeAntennaLocationClone = relativeAntennaLocation.clone();
		
		return new AntennaLocation( antennaLocationClone, relativeAntennaLocationClone );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		antennaLocation.read( dis );
		relativeAntennaLocation.read( dis );
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		antennaLocation.write( dos );
		relativeAntennaLocation.write( dos );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = antennaLocation.getByteLength();
		size += relativeAntennaLocation.getByteLength();
		
		return size;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public WorldCoordinate getAntennaLocation()
	{
		return antennaLocation;
	}
	
	public void setAntennaLocation( WorldCoordinate antennaLocation )
	{
		this.antennaLocation = antennaLocation;
	}
	
	public EntityCoordinate getRelativeAntennaLocation()
	{
		return relativeAntennaLocation;
	}
	
	public void setRelativeAntennaLocation( EntityCoordinate relativeAntennaLocation )
	{
		this.relativeAntennaLocation = relativeAntennaLocation;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
