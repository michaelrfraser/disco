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
package com.calytrix.disco.pdu.radio;

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.util.DISSizes;

/**
 * An abstract PDU that contains fields common to all PDUs in the Radio Communications Family
 */
public abstract class AbstractRadioPDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityIdentifier entityIdentifier;
	private int radioID;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for AbstractRadioPDU with provided PDUHeader
	 * 
	 * @param header The PDUHeader that this PDU will wrap 
	 */
	public AbstractRadioPDU( PDUHeader header )
	{
		super( header );
		
		this.entityIdentifier = new EntityIdentifier();
		this.radioID = 0;
	}
			
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public void readContent( DISInputStream dis ) throws IOException
	{
		entityIdentifier.read( dis );
		radioID = dis.readUI16();
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public void writeContent( DISOutputStream dos ) throws IOException
	{
		entityIdentifier.write( dos );
		dos.writeUI16( radioID );
	}
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public int getContentLength()
	{
		int size = entityIdentifier.getByteLength();
		size += DISSizes.UI16_SIZE;				// Radio ID
		
		return size;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public EntityIdentifier getEntityIdentifier()
	{
		return entityIdentifier;
	}
	
	public void setEntityIdentifier( EntityIdentifier identifier )
	{
		entityIdentifier = identifier;
	}
	
	public int getRadioID()
	{
		return radioID;
	}
	
	public void setRadioID( int radioID )
	{
		this.radioID = radioID;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
