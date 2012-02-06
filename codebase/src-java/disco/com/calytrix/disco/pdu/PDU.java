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

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * This class is the parent of all types that represent PDU data.
 * <br/>
 * A PDU class represents a specific DIS defined PDU message. When a PDU is sensed on the network,
 * the raw data is used to generate an instance of the appropriate {@link PDU} child class. The
 * individual child will contain pdu-specific properties and methods, as well as the logic needed
 * to serialize and deserialize the PDU to and from a raw byte format.
 * <br/>
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
	
	/**
	 * Writes the header section of this PDU to the specified DISOutputStream
	 * 
	 * @param dos The DISOutputStream to write this PDU's header section to
	 * 
	 * @throws IOException Thrown if an error occurred writing this PDU's header section to the
	 * specified DISOutputStream
	 */
	public void writeHeader( DISOutputStream dos ) throws IOException
	{
		int contentLength = getContentLength();
		header.write( dos, contentLength );
	}
	
	/**
	 * Reads the PDU content section from the specified DISInputStream, setting the values of this 
	 * instance's member variables to those read in. 
	 *  
	 * @param dis The DISInputStream to read the PDU content section from
	 * 
	 * @throws IOException Thrown if an error occurred while reading the PDU content section from
	 * the specified DISInputStream
	 */
	public abstract void readContent( DISInputStream dis ) throws IOException;
	
	/**
	 * Writes this PDU's content section to the specified DISOutputStream
	 * 
	 * @param dos The DISOutputStream to write the PDU content section to
	 * 
	 * @throws IOException Thrown if an error occurred while writing the PDU content section to
	 * the specified DISOutputStream
	 */
	public abstract void writeContent( DISOutputStream dos ) throws IOException;
	
	/**
	 * Returns the length of this PDU's content section in bytes
	 * 
	 * @return An int value representing the length of this PDU's content section in bytes
	 */
	public abstract int getContentLength();
	
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
