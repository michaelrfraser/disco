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

/**
 * An interface that declares the necessary methods for a service that reads PDUs from an abstract
 * source and notifies IPDUListeners that a PDU has been received.
 */
public interface IPDUReaderService extends IDiscoService
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Registers the provided IPDUListener with this class to receive 
	 * notifications when PDUs are received by this reader service
	 * 
	 * @param listener The IPDUListener to register with this service
	 */
	public void addPDUListener( IPDUListener listener );
	
	/**
	 * Unregisters the provided IPDUListener with this class so that it no 
	 * longer receives notifications when PDUs are received by this reader service
	 * 
	 * @param listener The IPDUListener to unregister from this service
	 */
	public void removePDUListener( IPDUListener listener );
	
	/**
	 * Adds the specified IPDUFilter to this IPDUReaderService's filter set. The logic contained 
	 * within the specified IPDUFilter will be called upon to decide whether PDUs received by this 
	 * service should be processed further or discarded.<br/>
	 * <br/>
	 * <b>Note:</b> IPDUFilters may only be added while the IPDUReaderService is stopped.
	 * 
	 * @param filter The IPDUFilter to add to this IPDUReaderService's filter set
	 * 
	 * @throws IllegalStateException Thrown if addPDUFilter() was called while the IPDUReaderService 
	 * was started
	 */
	public void addPDUFilter( IPDUFilter filter );
	
	/**
	 * Removes the specified IPDUFilter from this IPDUReaderService's filter set.<br/>
	 * <br/>
	 * <b>Note:</b> IPDUFilters may only be removed while the IPDUReaderService is stopped.
	 * 
	 * @param filter The IPDUFilter to remove from this IPDUReaderService's filter set
	 * 
	 * @throws IllegalStateException Thrown if removePDUFilter() was called while the 
	 * IPDUReaderService was started
	 */
	public void removePDUFilter( IPDUFilter filter );
}
