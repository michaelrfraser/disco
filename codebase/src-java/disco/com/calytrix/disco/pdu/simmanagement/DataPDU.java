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

import com.calytrix.disco.pdu.PDUType;

/**
 * This class represents an Data PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.5.4.10"
 */
public class DataPDU
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
	public DataPDU()
	{
		super();
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	public final PDUType getPDUType()
	{
		return PDUType.Data;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////// Serialization Methods /////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This method will serialize the values of the provided PDU into a byte[] and return it.
	 * 
	 * @return byte[] containing the serialized form of this PDU
	 */
	public byte[] serialize()
	{
		return new byte[0];
	}

	/**
	 * This method will initialize the local values of this PDU with the information contained
	 * in the provided buffer. After this method has been called, any previous state in the PDU
	 * should have been wiped clear and replaced with the new values from the buffer.
	 * 
	 * @param buffer The raw bytes to extract the various values for the PDU from
	 */
	public void deserialize( byte[] buffer )
	{
		// no-op
	}

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
