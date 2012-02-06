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

import com.calytrix.disco.DiscoException;

/**
 * A service interface that declares methods for service management. The actual utility that the
 * service provides shall be defined by classes that implement this interface. Implementing this
 * interface ensures that all services may be started/stopped in a consistent and familiar way.
 */
public interface IDiscoService
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * Specifies whether this <code>IDiscoService</code> is currently started
	 * 
	 * @return A boolean value: <code>true</code> if the <code>IDiscoService</code> is currently
	 * started, otherwise <code>false</code>.
	 */
	public boolean isStarted();
	
	/**
	 * Starts this <code>IDiscoService</code>, so that it begins to provide the service it 
	 * represents. While in a started state, calls to <code>isStarted()</code> should return 
	 * <code>true</code>. 
	 * 
	 * @throws DiscoException Thrown if an error occurred while attempting to start this service. 
	 */
	public void start() throws DiscoException;
	
	/**
	 * Stops this <code>IDiscoService</code>, so that it no longer provides the service it 
	 * represents.  
	 * 
	 * @throws DiscoException Thrown if an error occurred while attempting to stop this service. 
	 */
	public void stop() throws DiscoException;
}
