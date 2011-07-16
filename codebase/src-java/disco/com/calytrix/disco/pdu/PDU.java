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

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Each PDU has a specific type, as enumeraetd in {@link PDUType}. This method returns the
	 * specific value for this PDU type. 
	 */
	public abstract PDUType getPDUType();

	/**
	 * This method will serialize the values of the provided PDU into a byte[] and return it.
	 * 
	 * @return byte[] containing the serialized form of this PDU
	 */
	public abstract byte[] serialize();

	/**
	 * This method will initialize the local values of this PDU with the information contained
	 * in the provided buffer. After this method has been called, any previous state in the PDU
	 * should have been wiped clear and replaced with the new values from the buffer.
	 * 
	 * @param buffer The raw bytes to extract the various values for the PDU from
	 */
	public abstract void deserialize( byte[] buffer );

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
