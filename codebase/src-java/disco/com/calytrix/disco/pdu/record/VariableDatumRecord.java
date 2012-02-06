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
import java.util.Arrays;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.IPDUComponent;
import com.calytrix.disco.util.DISSizes;

public class VariableDatumRecord implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private long id;
	private byte[] value;
		
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public VariableDatumRecord()
	{
		this( 0, new byte[0] );
	}
	
	public VariableDatumRecord( long id, byte[] value )
	{
		this.id = id;
		setValue( value );
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	@Override
	public boolean equals( Object other )
	{
		if( this == other )
			return true;
		
		if( other instanceof VariableDatumRecord )
		{
			VariableDatumRecord asVariableDatumRecord = (VariableDatumRecord)other;
			if( asVariableDatumRecord.id == this.id &&
				Arrays.equals(asVariableDatumRecord.value, this.value) )
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
	public VariableDatumRecord clone()
	{
		byte[] valueClone = Arrays.copyOf( value, value.length );
		
		return new VariableDatumRecord( id, valueClone );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		id = dis.readUI32();
		long valueLength = dis.readUI32() / 8l;
		
		if( valueLength > Integer.MAX_VALUE )
		{
			// Warn about truncation
			valueLength = Integer.MAX_VALUE;
		}
		
		byte[] value = new byte[(int)valueLength];
		dis.readFully( value );
		
		setValue( value );
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI32( id );
		dos.writeUI32( value.length * 8 );
		dos.write( value );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = DISSizes.UI32_SIZE * 2;
		size += value.length;
		
		return size;
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
	
	public byte[] getValue()
	{
		return value;
	}
	
	public void setValue( byte[] value )
	{
		if( (value.length % 8) != 0 )
			throw new IllegalArgumentException( "Value BLOB must be aligned to 64bit boundary" );
		
		this.value = value;
	}

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
