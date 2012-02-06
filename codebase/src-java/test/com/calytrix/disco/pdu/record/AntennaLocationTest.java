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
package com.calytrix.disco.pdu.record;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.calytrix.disco.AbstractTest;
import com.calytrix.disco.TestUtils;
import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.util.FloatingPointUtils;

@Test(sequential=true, groups={"pdu"})
public class AntennaLocationTest extends AbstractTest
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final double APERY       = 1.20205690315959;
	public static final double BRUN        = 1.90216058;
	public static final double CATALAN     = 0.915965594177219015;
	public static final float FEIGENBAUM   = 2.5029078750f;
	public static final float GAUSS        = 2.5029078750f;
	public static final float LAPLACE      = 0.6627434193f;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public AntennaLocationTest()
	{
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	@BeforeClass(alwaysRun=true)
	public void beforeClass()
	{
		super.beforeClass();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() throws Exception
	{
		super.beforeMethod();
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod()
	{
		super.afterMethod();
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass()
	{
		super.afterClass();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////// Testing Methods /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testAntennaLocationEquals()
	{
		WorldCoordinate worldCoordinate1 = new WorldCoordinate( APERY, BRUN, CATALAN );
		WorldCoordinate worldCoordinate2 = new WorldCoordinate( BRUN, CATALAN, APERY );
		
		EntityCoordinate entityCoordinate1 = new EntityCoordinate( FEIGENBAUM, GAUSS, LAPLACE );
		EntityCoordinate entityCoordinate2 = new EntityCoordinate( GAUSS, LAPLACE, FEIGENBAUM );
		
		AntennaLocation locationW1E1 = new AntennaLocation( worldCoordinate1, entityCoordinate1 );
		AntennaLocation locationW1E1v2 = new AntennaLocation( worldCoordinate1, entityCoordinate1 );
		AntennaLocation locationW1E1v3 = new AntennaLocation( worldCoordinate1, entityCoordinate1 );
		
		// Reflexive?
		Assert.assertSame( locationW1E1, locationW1E1 );
		Assert.assertEquals( locationW1E1, locationW1E1 );
				
		// Symmetric?
		Assert.assertNotSame( locationW1E1, locationW1E1v2 );
		Assert.assertEquals( locationW1E1, locationW1E1v2 );
		
		// Transitive?
		Assert.assertNotSame( locationW1E1, locationW1E1v3 );
		Assert.assertNotSame( locationW1E1v2, locationW1E1v3 );
		Assert.assertEquals( locationW1E1v2, locationW1E1v3 );
		Assert.assertEquals( locationW1E1, locationW1E1v3 );
		
		AntennaLocation locationW1E2 = new AntennaLocation( worldCoordinate1, entityCoordinate2 );
		AntennaLocation locationW2E1 = new AntennaLocation( worldCoordinate2, entityCoordinate1 );
		AntennaLocation locationW2E2 = new AntennaLocation( worldCoordinate2, entityCoordinate2 );
		
		// Inequality
		Assert.assertFalse( locationW1E1.equals(locationW1E2) );
		Assert.assertFalse( locationW1E1.equals(locationW2E1) );
		Assert.assertFalse( locationW1E1.equals(locationW2E2) );
		
		// Symmetric Inequality
		Assert.assertFalse( locationW1E1v2.equals(locationW1E2) );
		Assert.assertFalse( locationW1E1v2.equals(locationW2E1) );
		Assert.assertFalse( locationW1E1v2.equals(locationW2E2) );
	}
	
	@Test
	public void testAntennaLocationCompleteDeserialisation()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DISOutputStream dos = new DISOutputStream( baos );
		
		try
		{
			// Write out an Antenna Location Record
			dos.writeDouble( APERY );		// world.x
			dos.writeDouble( BRUN );		// world.y
			dos.writeDouble( CATALAN );		// world.z
			
			dos.writeFloat( FEIGENBAUM );	// relative.x
			dos.writeFloat( GAUSS );		// relative.x
			dos.writeFloat( LAPLACE );		// relative.x
			
			// Run the raw bytes through the deserialisation method to create
			// an AntennaLocation object
			DISInputStream dis = TestUtils.convertToInputStream( baos );
			AntennaLocation location = new AntennaLocation();
			location.read( dis );
						
			// Ensure that the records contained within are valid
			WorldCoordinate worldCoords = location.getAntennaLocation();
			EntityCoordinate relativeCoords = location.getRelativeAntennaLocation();
			Assert.assertNotNull( worldCoords );
			Assert.assertNotNull( relativeCoords );
			
			// Do the World Coordinates match?
			Assert.assertTrue( FloatingPointUtils.doubleEqual(APERY, worldCoords.getX()) );
			Assert.assertTrue( FloatingPointUtils.doubleEqual(BRUN, worldCoords.getY()) );
			Assert.assertTrue( FloatingPointUtils.doubleEqual(CATALAN, worldCoords.getZ()) );
			
			// Do the Relative Coordinates match?
			Assert.assertTrue( FloatingPointUtils.floatEqual(FEIGENBAUM, relativeCoords.getX()) );
			Assert.assertTrue( FloatingPointUtils.floatEqual(GAUSS, relativeCoords.getY()) );
			Assert.assertTrue( FloatingPointUtils.floatEqual(LAPLACE, relativeCoords.getZ()) );
			
		}
		catch ( Exception e )
		{
			// Unexpected Exception
			Assert.fail( "Unexpected exception while deserialising an AntennaLocation record", e );
		}
	}
	
	@Test
	public void testAntennaLocationIncompleteDeserialisation()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DISOutputStream dos = new DISOutputStream( baos );
				
		AntennaLocation location = new AntennaLocation();
		
		try
		{
			// Only write out an Antenna Location Record
			dos.writeDouble( APERY );		// world.x
			dos.writeDouble( BRUN );		// world.y
			dos.writeDouble( CATALAN );		// world.z
			
			// Run the raw bytes through the deserialisation method. This should
			// fail as the AntennaLocation record is incomplete
			DISInputStream dis = TestUtils.convertToInputStream( baos );
			location.read( dis );
			
			Assert.fail( "IOException should have been thrown" );
		}
		catch ( IOException ioex )
		{
			// This is what we were expecting
		}
		catch ( Exception e )
		{
			Assert.fail( "Expected an IOException to be thrown but received " + e.getClass(), e );
		}
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
