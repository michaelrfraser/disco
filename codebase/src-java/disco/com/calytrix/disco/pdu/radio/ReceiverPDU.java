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
package com.calytrix.disco.pdu.radio;

import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * This class represents a Receiver PDU.
 * <p/>
 * PDUs of this type contain information about...
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.7.4"
 */
public class ReceiverPDU extends AbstractRadioPDU
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
	/**
	 * Constructor for type ReceiverPDU with specified header
	 * 
	 * @param header The PDUHeader to base the new PDU on
	 */
	public ReceiverPDU( PDUHeader header )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.RECEIVER )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
