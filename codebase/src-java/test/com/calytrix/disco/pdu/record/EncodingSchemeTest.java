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
import com.calytrix.disco.pdu.field.EncodingClass;
import com.calytrix.disco.pdu.field.EncodingType;

public class EncodingSchemeTest extends AbstractTest
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int ENCODING_SCHEME_SIZE = 2;
	
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
	
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////// Testing Methods /////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testEncodingSchemeEquals()
    {
    	EncodingScheme schemeC1T1 = new EncodingScheme( EncodingClass.ENCODED_VOICE, EncodingType.PCM_8 );
    	EncodingScheme schemeC1T1v2 = new EncodingScheme( EncodingClass.ENCODED_VOICE, EncodingType.PCM_8 );
    	EncodingScheme schemeC1T1v3 = new EncodingScheme( EncodingClass.ENCODED_VOICE, EncodingType.PCM_8 );
    	
    	// Reflexive?
    	Assert.assertSame( schemeC1T1, schemeC1T1 );
    	Assert.assertEquals( schemeC1T1, schemeC1T1 );
    	    	
    	// Symmetric?
		Assert.assertEquals( schemeC1T1, schemeC1T1v2 );
		Assert.assertNotSame( schemeC1T1, schemeC1T1v2 );
		
		// Transitive?
		Assert.assertNotSame( schemeC1T1, schemeC1T1v3 );
		Assert.assertNotSame( schemeC1T1v2, schemeC1T1v3 );
		Assert.assertEquals( schemeC1T1v2, schemeC1T1v3 );
		Assert.assertEquals( schemeC1T1, schemeC1T1v3 );
		
		EncodingScheme schemeC1T2 = new EncodingScheme( EncodingClass.ENCODED_VOICE, EncodingType.PCM_16 );
		EncodingScheme schemeC2T1 = new EncodingScheme( EncodingClass.RAW_BINARY_DATA, EncodingType.PCM_8 );
		EncodingScheme schemeC2T2 = new EncodingScheme( EncodingClass.RAW_BINARY_DATA, EncodingType.PCM_16 );
		
		// Inequality
		Assert.assertFalse( schemeC1T1.equals(schemeC1T2) );
		Assert.assertFalse( schemeC1T1.equals(schemeC2T1) );
		Assert.assertFalse( schemeC1T1.equals(schemeC2T2) );
		
		// Symmetric Inequality
		Assert.assertFalse( schemeC1T1v2.equals(schemeC1T2) );
		Assert.assertFalse( schemeC1T1v2.equals(schemeC2T1) );
		Assert.assertFalse( schemeC1T1v2.equals(schemeC2T2) );
    }
	
    @Test
	public void testEncodingSchemeCompleteDeserialisationVoice()
	{    	
    	try
    	{
    		// Voice/MuLaw-8
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x00, (byte)0x01 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.ENCODED_VOICE );
    		Assert.assertEquals( scheme.getEncodingType(), EncodingType.MULAW_8 );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising a Voice/MuLaw-8 Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Voice/CVSD
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x00, (byte)0x02 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.ENCODED_VOICE );
    		Assert.assertEquals( scheme.getEncodingType(), EncodingType.CVSD );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising a Voice/CVSD Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Voice/ADPCM
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x00, (byte)0x03 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.ENCODED_VOICE );
    		Assert.assertEquals( scheme.getEncodingType(), EncodingType.ADPCM );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising a Voice/ADPCM Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Voice/PCM-16
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x00, (byte)0x04 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.ENCODED_VOICE );
    		Assert.assertEquals( scheme.getEncodingType(), EncodingType.PCM_16 );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising a Voice/PCM-16 Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Voice/PCM-8
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x00, (byte)0x05 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.ENCODED_VOICE );
    		Assert.assertEquals( scheme.getEncodingType(), EncodingType.PCM_8 );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising a Voice/PCM-8 Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Voice/Vector Quantization
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x00, (byte)0x06 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.ENCODED_VOICE );
    		Assert.assertEquals( scheme.getEncodingType(), EncodingType.VQ );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising a Voice/VQ Encoding Scheme record", e );
    	}
	}
    
    @Test
    public void testEncodingSchemeCompleteDeserialisationOther()
    {
    	try
    	{
    		// Raw Binary Data
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x40, (byte)0x00 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.RAW_BINARY_DATA );
    		Assert.assertEquals( scheme.getEncodingType(), 0 );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising a Raw Binary Data Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Application Specific Data
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0x80, (byte)0x00 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.APPLICATION_SPECIFIC_DATA );
    		Assert.assertEquals( scheme.getEncodingType(), 0 );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising Application Specific Data Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Database Index
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0xC0, (byte)0x00 );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.DATABASE_INDEX );
    		Assert.assertEquals( scheme.getEncodingType(), 0 );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising Databse Index Encoding Scheme record", e );
    	}
    	
    	try
    	{
    		// Ensure that the max value for TDL count does not affect the
    		// encoding class
    		DISInputStream stream = TestUtils.convertToInputStream( (byte)0xBF, (byte)0xFF );
    		EncodingScheme scheme = EncodingScheme.read( stream );
    		
    		Assert.assertNotNull( scheme );
    		Assert.assertEquals( scheme.getEncodingClass(), EncodingClass.APPLICATION_SPECIFIC_DATA );
    		Assert.assertEquals( scheme.getEncodingType(), 0x3FFF );
    	}
    	catch ( Exception e )
    	{
    		// Unexpected Exception
    		Assert.fail( "Unexpected exception while deserialising Encoding Scheme record", e );
    	}
    }
    
    @Test
	public void testEncodingSchemeIncompleteDeserialisation()
	{
    	ByteArrayOutputStream baos = new ByteArrayOutputStream( ENCODING_SCHEME_SIZE );
		DISOutputStream dos = new DISOutputStream( baos );
		
		try
		{
			// Only write out one byte of data
			dos.writeUI8( EncodingClass.APPLICATION_SPECIFIC_DATA );
			
			// Run the raw bytes through the deserialisation method. This should
			// fail as the EncodingScheme record is incomplete
			DISInputStream dis = TestUtils.convertToInputStream( baos );
			EncodingScheme.read( dis );
			
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
