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

import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * This class represents an Fire PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.3.2"
 */
public class FirePDU extends PDU
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
	public FirePDU( PDUHeader header )
	{
		super( header );
		if( header.getPDUType() != PDUType.FIRE )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
