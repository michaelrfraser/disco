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
package com.calytrix.disco.service;

import com.calytrix.disco.DiscoException;
import com.calytrix.disco.pdu.PDU;

/**
 * An interface that declares the necessary methods for a service that writes PDUs to an abstract
 * destination.
 */
public interface IPDUWriterService extends IDiscoService
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Writes the specified PDU to the destination this IPDUWriterService implementation specifies.
	 * 
	 * @param pdu The PDU to write to the destination this IPDUWriterService implementation 
	 * specifies.
	 * 
	 * @throws DiscoException Thrown if an error occurred when attempting to send this PDU
	 */
	public void write( PDU pdu ) throws DiscoException;
}
