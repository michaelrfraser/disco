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
package com.calytrix.disco.pdu;

import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * This class is the parent of all types that represent PDU data.
 * <p/>
 * A PDU class represents a specific DIS defined PDU message. When a PDU is sensed on the network,
 * the raw data is used to generate an instance of the appropriate {@link PDU} child class. The
 * individual child will contain pdu-specific properties and methods, as well as the logic needed
 * to serialize and deserialize the PDU to and from a raw byte format.
 * <p/>
 * This class defines the common interface for all PDU types.
 */
public abstract class PDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private PDUHeader header;
	private long received;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public PDU( PDUHeader header )
	{
		this.header = header;
		this.received = System.currentTimeMillis();
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Each PDU has a specific type, as enumerated in 
	 * {@link com.calytrix.disco.pdu.field.PDUType}. This method returns the
	 * specific value for this PDU type. 
	 */
	public final short getPDUType()
	{
		if( header == null )
			throw new IllegalStateException( "The PDU instance does not contain a header" );
		
		short type = header.getPDUType();
		return type;
	}

	public PDUHeader getHeader()
	{
		return header;
	}
	
	public void setHeader( PDUHeader header )
	{
		this.header = header;
	}
	
	public long getReceived()
	{
		return received;
	}
	
	public void setReceived( long received )
	{
		this.received = received;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
