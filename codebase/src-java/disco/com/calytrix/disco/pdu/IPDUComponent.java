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

/**
 * An interface that represents a serialisable component of a PDU
 */
public interface IPDUComponent
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Reads new field values for the PDU Component from the provided DISInputStream replacing any 
	 * existing field values before the method was called.
	 * 
	 * If the method fails due to an IOException being raised, the object will be left in an
	 * undefined state.
	 * 
	 * @param dis The DISInputStream to read the new field values from
	 * 
	 * @throws IOException Thrown if there was an error reading from the provided DISInputStream
	 */
	public abstract void read( DISInputStream dis ) throws IOException;
	
	/**
	 * Writes the PDU Component's current field values to the provided DISOutputStream.
	 * 
	 * If the method fails due to an IOException being raised, the provided DISOutputStream will be 
	 * left in an undefined state.
	 * 
	 * @param dis The DISOutputStream to write field values to
	 * 
	 * @throws IOException Thrown if there was an error writing to the provided DISOutputStream
	 */
	public abstract void write( DISOutputStream dos ) throws IOException;
	
	/**
	 * Returns the length of this IPDUComponent in bytes
	 * 
	 * @return an int value representing the length of this IPDUComponent in bytes
	 */
	public abstract int getByteLength();
	
}
