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
 * This class represents a SetData PDU. The SetData PDU is issued by a DIS node that wishes for 
 * another DIS node to update the data it holds. Handling of a SetData PDUs is usually responded to
 * by sending a corresponding DataPDU acknowledging that the requested data change has been made
 */
public class SetDataPDU extends AbstractDataPDU
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
	 * Constructor for type SetDataPDU with specified PDUHeader
	 * 
	 * @param header The <code>PDUHeader</code> to base this PDU on
	 */
	public SetDataPDU( PDUHeader header )
	{
		super( header );
		
		if( header.getPDUType() != PDUType.SET_DATA )
	    	throw new IllegalStateException( "Invalid PDUType in Header" );
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
