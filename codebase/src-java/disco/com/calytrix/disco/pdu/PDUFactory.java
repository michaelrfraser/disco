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

import com.calytrix.disco.DiscoException;
import com.calytrix.disco.config.DiscoProperties;
import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.field.ProtocolFamily;
import com.calytrix.disco.pdu.radio.SignalPDU;
import com.calytrix.disco.pdu.radio.TransmitterPDU;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.simmanagement.CommentPDU;
import com.calytrix.disco.pdu.simmanagement.SetDataPDU;

/**
 * This class is responsible for creating PDU objects, both from scratch, or from a DISInputStream. 
 */
public class PDUFactory
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private DiscoProperties properties;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public PDUFactory( DiscoProperties properties )
	{
		this.properties = properties;
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Creates a PDUHeader instance populating the Exercise Identifier, PDU Type
	 * and Protocol Family fields with the provided values.<br/>
	 * <br/>
	 * <b>Note:</b> The Protocol Version field value will be automatically
	 * populated from the corresponding property value from this factory's DiscoProperties object
	 *  
	 * @param exerciseIdentifier The Exercise Identifier to assign to the PDU Header
	 * @param pduType The PDU Type to assign to this PDU Header 
	 * @param protocolFamily The Protocol Family that this PDU Header will belong to
	 * 
	 * @return a PDUHeader constructed from the provided information, with the current system time
	 * used as the time stamp
	 */
	public PDUHeader createPDUHeader( short exerciseIdentifier, short pduType, short protocolFamily, long timestamp )
	{
		short protocolVersion = properties.getDISProtocolVersion();
		
		return new PDUHeader( protocolVersion, exerciseIdentifier, pduType, protocolFamily, timestamp );
	}
	
	/**
	 * Creates a PDUHeader instance from the data contained in the provided DISInputStream
	 * 
	 * @param dis The DISInputStream that contains the PDUHeader in raw form
	 * 
	 * @return a PDUHeader constructed from the provided DISInputStream
	 * 
	 * @throws IOException Thrown if there is not enough data in the DISInputStream to construct
	 * a PDUHeader, or if an error occurred while deserialising the PDUHeader from the raw data
	 */
	public PDUHeader createPDUHeader( DISInputStream dis ) throws IOException
	{
		// Read the header from the data stream 
		PDUHeader header = new PDUHeader();
		header.read( dis );
		
		return header;
	}
	
	/**
	 * Creates an empty PDU with the PDUHeader populated with the provided Exercise Identifier, 
	 * PDU Type and Protocol Family. The returned PDU implementation will be based on the provided
	 * PDU Type.<br/>
	 * <br/>
	 * <b>Note:</b> The PDUHeadher's Protocol Version field value will be automatically
	 * populated from the corresponding property value from this factory's DiscoProperties object
	 *  
	 * @param exerciseIdentifier The Exercise Identifier to assign to the PDU's Header
	 * @param pduType The PDU Type to assign to this PDU's Header 
	 * @param protocolFamily The Protocol Family that this PDU will belong to
	 * 
	 * @return a PDU constructed from the provided information, with the current system time
	 * used as the time stamp
	 */
	public PDU createPDU( short exerciseIdentifier, short pduType, short protocolFamily, long timestamp )
	{
		// STEP ONE: Create the header
		PDUHeader header = createPDUHeader( exerciseIdentifier, pduType, protocolFamily, timestamp );
		
		// STEP TWO: Create an empty PDU from the header
		PDU asPDU = createPDU( header );
		
		return asPDU;
	}
	
	/**
	 * Creates an empty PDU, populated with the provided PDUHeader. The returned PDU implementation 
	 * will be based on the PDU Type field of the provided PDUHeader.
	 * 
	 * @param header The PDUHeader to create the new PDU from
	 * 
	 * @return a PDU constructed from the provided PDUHeader
	 */
	public PDU createPDU( PDUHeader header )
	{
		PDU asPDU = null;
		
		switch ( header.getPDUType() )
		{
			case PDUType.TRANSMITTER:
				asPDU = new TransmitterPDU( header );
				break;
			
			case PDUType.SIGNAL:
				asPDU = new SignalPDU( header );
				break;
			
			case PDUType.COMMENT:
				asPDU = new CommentPDU( header );
				break;
				
			case PDUType.SET_DATA:
				asPDU = new SetDataPDU( header );
				break;
		}
		
		return asPDU;
	}
	
	/**
	 * Creates a PDU, populated from data contained in the provided DISInputStream. The returned 
	 * PDU implementation will be based on the PDU Type field of the deserialised PDU Header.
	 * 
	 * @param dis The DISInputStream to read the PDU's contents from
	 * 
	 * @return A PDU instance, populated with the values from the specified DISInputStream
	 * 
	 * @throws DiscoException Thrown if an error occurred reading the PDU from the stream
	 */
	public PDU createPDU( DISInputStream dis ) throws DiscoException
	{
		try
		{
			// STEP ONE: Create the header
			PDUHeader header = createPDUHeader( dis );
			
			// STEP TWO: Create an empty PDU from the header
			PDU asPDU = createPDU( header );
			
			// STEP THREE: Read the PDU contents from the stream
			asPDU.readContent( dis );
			
			return asPDU;
		}
		catch ( IOException ioex )
		{
			throw new DiscoException( ioex );
		}
	}
	
	public TransmitterPDU createTransmitterPDU( short exerciseIdentifier, long timestamp )
	{
		PDU pdu = createPDU( exerciseIdentifier, PDUType.TRANSMITTER, ProtocolFamily.RADIO_COMMUNICATION, timestamp );
		return (TransmitterPDU)pdu;
	}
	
	public SignalPDU createSignalPDU( short exerciseIdentifier, long timestamp )
	{
		PDU pdu = createPDU( exerciseIdentifier, PDUType.SIGNAL, ProtocolFamily.RADIO_COMMUNICATION, timestamp );
		return (SignalPDU)pdu;
	}
	
	public CommentPDU createCommentPDU( short exerciseIdentifier, long timestamp )
	{
		PDU pdu = createPDU( exerciseIdentifier, PDUType.COMMENT, ProtocolFamily.SIMULATION_MANAGEMENT, timestamp );
		return (CommentPDU)pdu;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
