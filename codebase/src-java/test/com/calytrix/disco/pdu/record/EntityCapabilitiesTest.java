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

public class EntityCapabilitiesTest extends AbstractTest
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int ALL_ON = 0x1F;
	public static final int ALL_OFF = 0x00;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

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
	
	private EntityCapabilities toEntityCapabilities( int bitField )
	{
		EntityCapabilities capabilities = new EntityCapabilities();
		
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DISOutputStream dos = new DISOutputStream( baos );
			dos.write( bitField );
			
			// Run the raw bytes through the deserialisation method to create
			// an EntityCapabilities object
			DISInputStream dis = TestUtils.convertToInputStream( baos );
			capabilities.read( dis );
		}
		catch( Exception e )
		{
			Assert.fail( "Unexpected exception while deserialising an EntityCapabilities record", e );
		}
		
		return capabilities;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////// Testing Methods /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testEntityCapabilitiesEquals()
	{
		EntityCapabilities capabilities1 = new EntityCapabilities( true, false, true, false, true );
		EntityCapabilities capabilities1v2 = new EntityCapabilities( true, false, true, false, true );
		EntityCapabilities capabilities1v3 = new EntityCapabilities( true, false, true, false, true );
		
		// Reflexive?
		Assert.assertSame( capabilities1, capabilities1 );
		Assert.assertEquals( capabilities1, capabilities1 );
				
		// Symmetric?
		Assert.assertNotSame( capabilities1, capabilities1v2 );
		Assert.assertEquals( capabilities1, capabilities1v2 );
		
		// Transitive?
		Assert.assertNotSame( capabilities1, capabilities1v3 );
		Assert.assertNotSame( capabilities1v2, capabilities1v3 );
		Assert.assertEquals( capabilities1v2, capabilities1v3 );
		Assert.assertEquals( capabilities1, capabilities1v3 );
		
		EntityCapabilities capabilities2 = new EntityCapabilities( true, true, false, false, false );
		EntityCapabilities capabilities3 = new EntityCapabilities( false, true, false, true, false );
		EntityCapabilities capabilities4 = new EntityCapabilities( false, false, true, true, true );
		
		// Inequality
		Assert.assertFalse( capabilities1.equals(capabilities2) );
		Assert.assertFalse( capabilities1.equals(capabilities3) );
		Assert.assertFalse( capabilities1.equals(capabilities4) );
		
		// Symmetric Inequality
		Assert.assertFalse( capabilities1v2.equals(capabilities2) );
		Assert.assertFalse( capabilities1v2.equals(capabilities3) );
		Assert.assertFalse( capabilities1v2.equals(capabilities4) );
	}
	
	@Test
	public void testEntityCapabilitiesDeserialisation()
	{
		// On/Clean (Turn on only the bits that the DIS bit prescribes)
		EntityCapabilities allOnClean = toEntityCapabilities( ALL_ON );
		Assert.assertTrue( allOnClean.getAmmunitionSupply() );
		Assert.assertTrue( allOnClean.getFuelSupply() );
		Assert.assertTrue( allOnClean.getRecovery() );
		Assert.assertTrue( allOnClean.getRepair() );
		
		// On/Dirty (Turn on the DIS prescribed bits and include rubbish in
		// the other bits)
		// 0x05 == 00000101
//		EntityCapabilities allOnDirty = toEntityCapabilities( ALL_ON | 0x05 );
//		Assert.assertTrue( allOnDirty.getAmmunitionSupply() );
//		Assert.assertTrue( allOnDirty.getFuelSupply() );
//		Assert.assertTrue( allOnDirty.getRecovery() );
//		Assert.assertTrue( allOnDirty.getRepair() );
		
		// Off/Clean (Turn off only the bits that the DIS bit prescribes)
		EntityCapabilities allOffClean = toEntityCapabilities( ALL_OFF );
		Assert.assertFalse( allOffClean.getAmmunitionSupply() );
		Assert.assertFalse( allOffClean.getFuelSupply() );
		Assert.assertFalse( allOffClean.getRecovery() );
		Assert.assertFalse( allOffClean.getRepair() );
		
		// Off/Dirty (Turn off the DIS prescribed bits and include rubbish in
		// the other bits)
		// 0x05 == 00000101
//		EntityCapabilities allOffDirty = toEntityCapabilities( ALL_OFF | 0x05 );
//		Assert.assertFalse( allOffDirty.getAmmunitionSupply() );
//		Assert.assertFalse( allOffDirty.getFuelSupply() );
//		Assert.assertFalse( allOffDirty.getRecovery() );
//		Assert.assertFalse( allOffDirty.getRepair() );
	}
	
	@Test
	public void testEntityCapabilitiesSerialisation()
	{
		try
    	{
    		// All On
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		DISOutputStream dos = new DISOutputStream( baos );
    		
    		EntityCapabilities allOn = new EntityCapabilities( true, true, true, true, true );
    		allOn.write( dos );
    		
    		byte[] asBytes = baos.toByteArray();
    		Assert.assertEquals( asBytes[0], (byte)ALL_ON );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while serialising a EntityCapabilities record", e );
    	}
		
		try
    	{
    		// All Off
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		DISOutputStream dos = new DISOutputStream( baos );
    		
    		EntityCapabilities allOff = new EntityCapabilities( false, false, false, false, false );
    		allOff.write( dos );
    		
    		byte[] asBytes = baos.toByteArray();
    		Assert.assertEquals( asBytes[0], (byte)ALL_OFF );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while serialising a EntityCapabilities record", e );
    	}
	}

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
