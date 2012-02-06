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
package com.calytrix.disco.pdu.simmanagement;

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * An Abstract PDU that contains fields common to all Management PDUs
 */
public class AbstractManagementPDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityIdentifier originatingEntityID;
	private EntityIdentifier receivingEntityID;
		
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type AbstractManagementPDU with specified PDUHeader
	 * 
	 * @param header The <code>PDUHeader</code> to base this PDU on
	 */
	public AbstractManagementPDU( PDUHeader header )
	{
		super( header );
		
		this.originatingEntityID = new EntityIdentifier();
		this.receivingEntityID = new EntityIdentifier();
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
		originatingEntityID.read( dis );
		receivingEntityID.read( dis );
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeContent( DISOutputStream dos ) throws IOException
	{
		originatingEntityID.write( dos );
		receivingEntityID.write( dos );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getContentLength()
	{
		int size = originatingEntityID.getByteLength();
		size += receivingEntityID.getByteLength();
		
		return size;
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
	public EntityIdentifier getOriginatingEntityID()
	{
		return originatingEntityID;
	}
	
	public void setOriginatingEntityID( EntityIdentifier originatingEntityID )
	{
		this.originatingEntityID = originatingEntityID;
	}
	
	public EntityIdentifier getReceivingEntityID()
	{
		return receivingEntityID;
	}
	
	public void setReceivingEntityID( EntityIdentifier receivingEntityID )
	{
		this.receivingEntityID = receivingEntityID;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
