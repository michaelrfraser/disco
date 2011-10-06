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
package com.calytrix.disco.network;

import java.io.IOException;

import com.calytrix.disco.DiscoException;
import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.entity.EntityStatePDU;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.radio.SignalPDU;
import com.calytrix.disco.pdu.radio.TransmitterPDU;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.warfare.DetonationPDU;
import com.calytrix.disco.pdu.warfare.FirePDU;

public class PDUCodec
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
	public PDU readPDU( DISInputStream dis ) throws DiscoException
	{
		try
		{
			// Firstly, read the header from the data stream 
			PDUHeader header = PDUHeader.read( dis );
			
			// Create the PDU from the rest of the datastream
			PDU pdu = readRemainingPDU( header, dis );
			return pdu;
		}
		catch( IOException ioex )
		{
			throw new DiscoException( ioex );
		}
	}

	private PDU readRemainingPDU( PDUHeader header, DISInputStream dis ) throws IOException
	{
		PDU result = null;
		
		short pduType = header.getPDUType();
				
		switch( pduType )
		{
			case PDUType.TRANSMITTER:
				result = TransmitterPDU.read( header, dis );
				break;
				
			case PDUType.SIGNAL:
				result = SignalPDU.read( header, dis );
				break;
			case PDUType.ENTITY_STATE:
				result = EntityStatePDU.read( header, dis );
				break;
			case PDUType.DETONATION:
				result = DetonationPDU.read( header, dis );
				break;
			case PDUType.FIRE:
				result = FirePDU.read( header, dis );
				break;
		}
				
		return result;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
