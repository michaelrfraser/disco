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
 * This type enumerates all the DIS PDU types supported by disco.
 */
public enum PDUType
{
	//----------------------------------------------------------
	//                    ENUMERATED VALUES
	//----------------------------------------------------------
	// a) entity
	EntityState,
	// b) warfare
	Fire,
	Detonation,
	// c) logistics
	// d) simulation management
	Data,
	SetData,
	// e) emission
	// f) radio
	Signal,
	Transmitter,
	Receiver;
	// g) entity management
	// h) minefield
	// i) synthetic environment
	// j) simulation management with reliability
	// k) live entity
	// l) non-realtime

	//----------------------------------------------------------
	//                    INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
}
