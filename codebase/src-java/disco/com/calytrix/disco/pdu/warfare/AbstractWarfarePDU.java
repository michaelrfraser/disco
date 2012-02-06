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
package com.calytrix.disco.pdu.warfare;

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.record.EntityIdentifier;
import com.calytrix.disco.pdu.record.EventIdentifier;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * An abstract class that contains fields common to all Warfare PDUs 
 */
public abstract class AbstractWarfarePDU extends PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityIdentifier firingEntityID;
	private EntityIdentifier targetEntityID;
	private EntityIdentifier munitionID;
	private EventIdentifier eventID;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type AbstractWarfarePDU with specified PDUHeader
	 * 
	 * @param header The <code>PDUHeader</code> to base this PDU on
	 */
	public AbstractWarfarePDU( PDUHeader header )
	{
		super( header );
		
		this.firingEntityID = new EntityIdentifier();
		this.targetEntityID = new EntityIdentifier();
		this.munitionID = new EntityIdentifier();
		this.eventID = new EventIdentifier();
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
		firingEntityID.read( dis );
		targetEntityID.read( dis );
		munitionID.read( dis );
		eventID.read( dis );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void writeContent( DISOutputStream dos ) throws IOException
    {
		firingEntityID.write( dos );
		targetEntityID.write( dos );
		munitionID.write( dos );
		eventID.write( dos );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getContentLength()
	{
		int size = firingEntityID.getByteLength();
		size += targetEntityID.getByteLength();
		size += munitionID.getByteLength();
		size += eventID.getByteLength();
		
		return size;
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
	public EntityIdentifier getFiringEntityID()
    {
    	return firingEntityID;
    }

	public void setFiringEntityID( EntityIdentifier firingEntityID )
    {
    	this.firingEntityID = firingEntityID;
    }

	public EntityIdentifier getTargetEntityID()
    {
    	return targetEntityID;
    }

	public void setTargetEntityID( EntityIdentifier targetEntityID )
    {
    	this.targetEntityID = targetEntityID;
    }

	public EntityIdentifier getMunitionID()
    {
    	return munitionID;
    }

	public void setMunitionID( EntityIdentifier munitionID )
    {
    	this.munitionID = munitionID;
    }

	public EventIdentifier getEventID()
    {
    	return eventID;
    }

	public void setEventID( EventIdentifier eventID )
    {
    	this.eventID = eventID;
    }
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
