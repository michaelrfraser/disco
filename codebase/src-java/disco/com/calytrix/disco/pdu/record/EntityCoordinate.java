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
 * Location with respect to a particular entity shall be specified with respect 
 * to three orthogonal axes whose origin shall be the center of the bounding 
 * volume of the entity excluding its articulated and attached parts.<br/>
 * <br/>
 * The x-axis extends in the positive direction out the front of the entity. The 
 * y-axis extends in the positive direction out the right side of the entity as 
 * viewed from above, facing in the direction of the positive x-axis. The z-axis 
 * extends in the positive direction out the bottom of the entity.
 */
public class EntityCoordinate implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private float x;
	private float y;
	private float z;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityCoordinate()
	{
		this( 0f, 0f, 0f );
	}
	
	public EntityCoordinate( float x, float y, float z )
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
		
		if( other instanceof EntityCoordinate )
		{
			EntityCoordinate asEntityCoordinate = (EntityCoordinate)other;
			if( FloatingPointUtils.floatEqual(asEntityCoordinate.x,this.x) &&
				FloatingPointUtils.floatEqual(asEntityCoordinate.y,this.y) &&
				FloatingPointUtils.floatEqual(asEntityCoordinate.z,this.z) )
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
	public EntityCoordinate clone()
	{
		return new EntityCoordinate( x, y, z );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		x = dis.readFloat();
		y = dis.readFloat();
		z = dis.readFloat();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeFloat( x );
		dos.writeFloat( y );
		dos.writeFloat( z );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return DISSizes.FLOAT32_SIZE * 3;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public float getX()
	{
		return x;
	}
	
	public void setX( float x )
	{
		this.x = x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setY( float y )
	{
		this.y = y;
	}
	
	public float getZ()
	{
		return z;
	}
	
	public void setZ( float z )
	{
		this.z = z;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
