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
import com.calytrix.disco.pdu.field.Fuse;
import com.calytrix.disco.pdu.field.Warhead;
import com.calytrix.disco.util.DISSizes;

/**
 * The firing of a round or a burst of ammunition shall be represented by a Burst Descriptor
 * Record. This record shall specify the type of munition fired, the type of warhead, the type of
 * fuse, the number of rounds fired, and the rate at which the rounds are fired in rounds per
 * minute.
 */
public class BurstDescriptor implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityType munition;
	private int warhead;
	private int fuse;
	private int quantity;
	private int rate;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public BurstDescriptor()
	{
		this( new EntityType(),
		      Warhead.OTHER,
		      Fuse.OTHER,
		      0,
		      0 );
		      
	}
	
	public BurstDescriptor( EntityType munition, int warhead, int fuse, int quantity, int rate )
	{
		this.munition = munition;
		this.warhead = warhead;
		this.fuse = fuse;
		this.quantity = quantity;
		this.rate = rate;
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
		
		if ( other instanceof BurstDescriptor )
		{
			BurstDescriptor asBurstDescriptor = (BurstDescriptor)other;
			if( asBurstDescriptor.munition.equals(this.munition) && 
			    asBurstDescriptor.warhead == this.warhead &&
			    asBurstDescriptor.fuse == this.fuse &&
			    asBurstDescriptor.quantity == this.quantity &&
			    asBurstDescriptor.rate == this.rate )
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
	public BurstDescriptor clone()
	{
		EntityType munitionClone = munition.clone();
		return new BurstDescriptor( munitionClone, warhead, fuse, quantity, rate );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		munition.read( dis );
		warhead = dis.readUI16();
		fuse = dis.readUI16();
		quantity = dis.readUI16();
		rate = dis.readUI16();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		munition.write( dos );
		dos.writeUI16( warhead );
		dos.writeUI16( fuse );
		dos.writeUI16( quantity );
		dos.writeUI16( rate);
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = munition.getByteLength();
		size += DISSizes.UI16_SIZE * 4;
		
		return size;
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
	public EntityType getMunition()
    {
    	return munition;
    }

	public void setMunition( EntityType munition )
    {
    	this.munition = munition;
    }

	public int getWarhead()
    {
    	return warhead;
    }

	public void setWarhead( int warhead )
    {
    	this.warhead = warhead;
    }

	public int getFuse()
    {
    	return fuse;
    }

	public void setFuse( int fuse )
    {
    	this.fuse = fuse;
    }

	public int getQuantity()
    {
    	return quantity;
    }

	public void setQuantity( int quantity )
    {
    	this.quantity = quantity;
    }

	public int getRate()
    {
    	return rate;
    }

	public void setRate( int rate )
    {
    	this.rate = rate;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
