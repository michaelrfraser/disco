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
import com.calytrix.disco.util.FloatingPointUtils;

/**
 * Location of the origin of the entity's coordinate system shall be specified 
 * by a set of three coordinates: X, Y, and Z. The shape of the earth shall be 
 * specified using WGS 84.<br/>
 * <br/>
 * The origin of the world coordinate system shall be the centroid of the earth, 
 * with the X-axis passing through the Prime Meridian at the Equator, the Y-axis 
 * passing through 90 degrees East longitude at the Equator, and the Z-axis 
 * passing through the North pole<br/>
 * <br/> 
 * These coordinates shall represent meters from the centroid of the earth. A 
 * 64-bit double precision floating point number shall represent the location 
 * for each coordinate.
 */
public class WorldCoordinate implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private double x;
	private double y;
	private double z;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public WorldCoordinate()
	{
		this( 0d, 0d, 0d );
	}
	
	public WorldCoordinate( double x, double y, double z )
	{
		this.x = x;
		this.y = y;
		this.z = z;
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
		
		if( other instanceof WorldCoordinate )
		{
			WorldCoordinate asWorldCoordinate = (WorldCoordinate)other;
			if( FloatingPointUtils.doubleEqual(asWorldCoordinate.x,this.x) &&
				FloatingPointUtils.doubleEqual(asWorldCoordinate.y,this.y) &&
				FloatingPointUtils.doubleEqual(asWorldCoordinate.z,this.z) )
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
	public WorldCoordinate clone()
	{
		return new WorldCoordinate( x, y, z );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read( DISInputStream dis ) throws IOException
	{
		x = dis.readDouble();
		y = dis.readDouble();
		z = dis.readDouble();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write( DISOutputStream dos ) throws IOException
	{
		dos.writeDouble( x );
		dos.writeDouble( y );
		dos.writeDouble( z );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getByteLength()
	{
		return DISSizes.FLOAT64_SIZE * 3;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public double getX()
	{
		return x;
	}
	
	public void setX( double x )
	{
		this.x = x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setY( double y )
	{
		this.y = y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public void setZ( double z )
	{
		this.z = z;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
