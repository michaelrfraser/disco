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
 * The type of radio in a DIS exercise shall be specified by a Radio Entity Type 
 * Record. This record shall specify the kind of entity, the domain, the country 
 * of design, and specific information about the radio.
 */
public class RadioEntityType
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
	public RadioEntityType( short entityKind, short domain, int country, 
	                        short category, short nomenclatureVersion, 
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
		boolean equal = false;
		
		if ( other == this )
		{
			equal = true;
		}
		else
		{
			if ( other instanceof RadioEntityType )
			{
				RadioEntityType asRadioEntityType = (RadioEntityType)other;
				equal = asRadioEntityType.entityKind == this.entityKind &&
					asRadioEntityType.domain == this.domain &&
					asRadioEntityType.country == this.country &&
					asRadioEntityType.category == this.category &&
					asRadioEntityType.nomenclatureVersion == this.nomenclatureVersion &&
					asRadioEntityType.nomenclature == this.nomenclature;
			}
		}
		
		return equal;
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
	/**
	 * Reads an instance of this record from the provided DISInputStream
	 * 
	 * @param dis The DISInputStream to read the record from
	 * 
	 * @return The RadioEntityType deserialised from the provided input stream
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream
	 */
	public static RadioEntityType read( DISInputStream dis ) throws IOException
	{
		short entityKind = dis.readUI8();
		short domain = dis.readUI8();
		int country = dis.readUI16();
        short category = dis.readUI8();
        short nomenclatureVersion = dis.readUI8();
        int nomenclature = dis.readUI16();
        
        return new RadioEntityType( entityKind, domain, country, category, 
                                    nomenclatureVersion, nomenclature );
	}
}
