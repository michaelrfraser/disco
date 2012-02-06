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

public class FixedDatumRecord implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private long id;
	private long value;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public FixedDatumRecord()
	{
		this( 0, 0 );
	}
	
	public FixedDatumRecord( long id, long value )
	{
		this.id = id;
		this.value = value;
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
			FixedDatumRecord asFixedDatumRecord = (FixedDatumRecord)other;
			if( asFixedDatumRecord.id == this.id &&
				asFixedDatumRecord.value == this.value )
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
	public FixedDatumRecord clone()
	{
		return new FixedDatumRecord( id, value );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		id = dis.readUI32();
		value = dis.readUI32();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI32( id );
		dos.writeUI32( value );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return DISSizes.UI32_SIZE * 2;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public long getID()
	{
		return id;
	}
	
	public void setID( long id )
	{
		this.id = id;
	}
	
	public long getValue()
	{
		return value;
	}
	
	public void setValue( long value )
	{
		this.value = value;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
