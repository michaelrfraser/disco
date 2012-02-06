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

import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * This class represents an Data PDU. A Data PDU is usually issued by the DIS node that handles a
 * SetData PDU request
 * 
 * @see "IEEE Std 1278.1-1995 section 4.5.5.4.10"
 */
public class DataPDU extends AbstractDataPDU
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
	 * Constructor for type DataPDU with specified PDUHeader
	 * 
	 * @param header The <code>PDUHeader</code> to base this PDU on
	 */
	public DataPDU( PDUHeader header )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.DATA )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	
}
