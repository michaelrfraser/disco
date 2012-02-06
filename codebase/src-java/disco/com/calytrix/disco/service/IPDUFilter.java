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

import java.net.DatagramPacket;

import com.calytrix.disco.pdu.PDU;
import com.calytrix.disco.pdu.record.PDUHeader;

/**
 * Interface used by the IPDUReaderService to accept/reject PDUs at various stages along the 
 * deserialisation chain
 */
public interface IPDUFilter
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Advises that a packet has been received from the specified source. This method shall return
	 * <code>true</code> if the IPDUReaderService should continue processing the PDU packet, 
	 * otherwise the method should return <code>false</code> to indicate that the PDU packet should 
	 * be discarded.
	 *  
	 * @param source The DatagramPacket that has been received
	 * 
	 * @return A boolean value. True if the IPDUReaderService should continue processing the PDU 
	 * packet, otherwise false.
	 */
	public boolean onPacketReceived( DatagramPacket packet );
	
	/**
	 * Advises that a PDUHeader has been received. This method shall return <code>true</code> if the 
	 * IPDUReaderService should continue processing the rest of the PDU contents, otherwise the 
	 * method should return <code>false</code> to indicate that the PDU packet should be discarded.
	 *  
	 * @param header The PDUHeader that has been received
	 * 
	 * @return A boolean value. True if the IPDUReaderService should continue processing the PDU 
	 * contents, otherwise false.
	 */
	public boolean onPDUHeaderReceived( PDUHeader header );
	
	/**
	 * Advises that a PDU has been received. This method shall return <code>true</code> if the 
	 * IPDUReaderService should dispatch the PDU to its registered IPDUListeners the, otherwise the 
	 * method should return <code>false</code> to indicate that the PDU should be discarded.
	 * 
	 * @param pdu The PDU that has been received
	 * 
	 * @return A boolean value. True if the IPDUReaderService should accept the PDU, otherwise 
	 * false.
	 */
	public boolean onPDUReceived( PDU pdu );
}
