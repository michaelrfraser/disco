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
import com.calytrix.disco.pdu.field.Country;
import com.calytrix.disco.pdu.field.Domain;
import com.calytrix.disco.pdu.field.EntityKind;
import com.calytrix.disco.util.DISSizes;

/**
 * The type of radio in a DIS exercise shall be specified by a Radio Entity Type 
 * Record. This record shall specify the kind of entity, the domain, the country 
 * of design, and specific information about the radio.
 */
public class RadioEntityType implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short entityKind;
	private short domain;
	private int country;
	private short category;
	private short nomenclatureVersion;
	private int nomenclature;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public RadioEntityType()
	{
		this( (short)0, (short)0, 0, (short)0, (short)0, 0 );
	}
	
	public RadioEntityType( short entityKind,
	                        short domain,
	                        int country, 
	                        short category,
	                        short nomenclatureVersion, 
	                        int nomenclature )
	{
		this.entityKind = entityKind;
		this.domain = domain;
		this.country = country;
		this.category = category;
		this.nomenclatureVersion = nomenclatureVersion;
		this.nomenclature = nomenclature;
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
		
		if( other instanceof RadioEntityType )
		{
			RadioEntityType asRadioEntityType = (RadioEntityType)other;
			if( asRadioEntityType.entityKind == this.entityKind &&
				asRadioEntityType.domain == this.domain &&
				asRadioEntityType.country == this.country &&
				asRadioEntityType.category == this.category &&
				asRadioEntityType.nomenclatureVersion == this.nomenclatureVersion &&
				asRadioEntityType.nomenclature == this.nomenclature )
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
	public RadioEntityType clone()
	{
		return new RadioEntityType( entityKind, 
		                            domain, 
		                            country, 
		                            category, 
		                            nomenclatureVersion, 
		                            nomenclature );
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		entityKind = dis.readUI8();
		domain = dis.readUI8();
		country = dis.readUI16();
        category = dis.readUI8();
        nomenclatureVersion = dis.readUI8();
        nomenclature = dis.readUI16();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI8( entityKind );
		dos.writeUI8( domain );
		dos.writeUI16( country );
		dos.writeUI8( category );
		dos.writeUI8( nomenclatureVersion );
		dos.writeUI16( nomenclature );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = EntityKind.BYTE_LENGTH;
		size += Domain.BYTE_LENGTH;
		size += Country.BYTE_LENGTH;
		size += DISSizes.UI8_SIZE;		// Category
		size += DISSizes.UI8_SIZE;		// Nomenclature Version
		size += DISSizes.UI16_SIZE;		// Nomenclature
		
		return size;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public short getEntityKind()
	{
		return entityKind;
	}
	
	public void setEntityKind( short entityKind )
	{
		this.entityKind = entityKind;
	}
	
	public short getDomain()
	{
		return domain;
	}
	
	public void setDomain( short domain )
	{
		this.domain = domain;
	}
	
	public int getCountry()
	{
		return country;
	}
	
	public void setCountry( int country )
	{
		this.country = country;
	}
	
	public short getCategory()
	{
		return category;
	}
	
	public void setCategory( short category )
	{
		this.category = category;
	}
	
	public short getNomenclatureVersion()
	{
		return nomenclatureVersion;
	}
	
	public void setNomenclatureVersion( short nomenclatureVersion )
	{
		this.nomenclatureVersion = nomenclatureVersion;
	}
	
	public int getNomenclature()
	{
		return nomenclature;
	}
	
	public void setNomenclature( int nomenclature )
	{
		this.nomenclature = nomenclature;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
