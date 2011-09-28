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
public class AntennaLocation
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
	public AntennaLocation( WorldCoordinate antennaLocation, EntityCoordinate relativeAntennaLocation )
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
		boolean equal = false;
		
		if ( other == this )
		{
			equal = true;
		}
		else
		{
			if ( other instanceof AntennaLocation )
			{
				AntennaLocation asAntennaLocation = (AntennaLocation)other;
				equal = asAntennaLocation.antennaLocation.equals( this.antennaLocation ) &&
					asAntennaLocation.relativeAntennaLocation.equals( this.relativeAntennaLocation );
			}
		}
		
		return equal;
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
	public static AntennaLocation read( DISInputStream dis ) throws IOException
	{
		WorldCoordinate antennaLocation = WorldCoordinate.read( dis );
		EntityCoordinate relativeAntennaLocation = EntityCoordinate.read( dis );
		
		return new AntennaLocation( antennaLocation, relativeAntennaLocation );
	}
}
