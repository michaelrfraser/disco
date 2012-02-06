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
package com.calytrix.disco.util;

import java.io.IOException;
import java.util.Arrays;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.IPDUComponent;

/**
 * The DISUnsignedInt64 represents an 64-bit unsigned integer, used throughout the DIS spec
 * 
 * TODO: Implement the various xxxValue() methods, and provide equals(), lessThan(), greaterThan()
 */
public class DISUnsignedInt64 extends Number implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
    private static final long serialVersionUID = -7912714492712731131L;
    private static final byte[] MAX_VALUE_BYTES = { (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 
                                                    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF };
    
    public static final DISUnsignedInt64 ZERO = new DISUnsignedInt64();
    public static final DISUnsignedInt64 MAX_VALUE = new DISUnsignedInt64( MAX_VALUE_BYTES );
    
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte[] value;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public DISUnsignedInt64()
	{
		this.value = new byte[DISSizes.UI64_SIZE];
	}
	
	public DISUnsignedInt64( byte[] value )
	{
		this();
		
		if ( value.length > DISSizes.UI64_SIZE )
			throw new IllegalArgumentException( "Value must not be more than 8 bytes long" );
		
		int offset = DISSizes.UI64_SIZE - value.length;
		for ( int i = 0 ; i < value.length ; ++i )
			this.value[offset + i] = value[i];
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
		if ( this == other )
			return true;
		
		if ( other instanceof DISUnsignedInt64 )
		{
			DISUnsignedInt64 asUI64 = (DISUnsignedInt64)other;
			return Arrays.equals( this.value, asUI64.value );
		}
		
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DISUnsignedInt64 clone()
	{
		// The constructor takes a copy for itself, so this is safe for cloning
		return new DISUnsignedInt64( value );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int intValue()
	{
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long longValue()
	{
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float floatValue()
	{
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double doubleValue()
	{
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
	    dis.readFully( value );   
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.write( value );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getByteLength()
	{
		return DISSizes.UI64_SIZE;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
