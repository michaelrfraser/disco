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
public class EntityCoordinate
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
	/**
	 * Reads an instance of this record from the provided DISInputStream
	 * 
	 * @param dis The DISInputStream to read the record from
	 * 
	 * @return The EntityCoordinate deserialised from the provided input stream
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream
	 */
	public static EntityCoordinate read( DISInputStream dis ) throws IOException
	{
		float x = dis.readFloat();
		float y = dis.readFloat();
		float z = dis.readFloat();
		
		return new EntityCoordinate( x, y, z );
	}
}
