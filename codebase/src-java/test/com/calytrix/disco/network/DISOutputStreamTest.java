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

import java.io.ByteArrayOutputStream;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.calytrix.disco.AbstractTest;
import com.calytrix.disco.TestUtils;
import com.calytrix.disco.util.DISSizes;

public class DISOutputStreamTest extends AbstractTest
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
	public void testWriteUI16Normal()
	{
		int ui16Value = Short.MAX_VALUE + 1;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DISOutputStream dos = new DISOutputStream( baos );
		
		try
		{
			dos.writeUI16( ui16Value );
			
			// Were the raw bytes as we expect? 
			byte[] result = baos.toByteArray();
			Assert.assertTrue( result.length >= 2 );
			Assert.assertEquals( result[0], (byte)0x80 );
			Assert.assertEquals( result[1], (byte)0x00 );
			
			// Can it be interpreted by the DISInputStream?
			DISInputStream dis = TestUtils.convertToInputStream( result );
			int recoveredUI16 = dis.readUI16();
			
			Assert.assertEquals( recoveredUI16, ui16Value );
		}
		catch ( Exception e )
		{
			Assert.fail( "Unexpected exception while serialising an 16-bit unsigned integer", e );
		}
	}
	
	@Test
	public void testWriteUI16MaxValue()
	{
		int ui16Value = DISSizes.UI16_MAX_VALUE;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DISOutputStream dos = new DISOutputStream( baos );
		
		try
		{
			dos.writeUI16( ui16Value );
			
			// Were the raw bytes as we expect? 
			byte[] result = baos.toByteArray();
			Assert.assertTrue( result.length >= 2 );
			Assert.assertEquals( result[0], (byte)0xFF );
			Assert.assertEquals( result[1], (byte)0xFF );
			
			// Can it be interpreted by the DISInputStream?
			DISInputStream dis = TestUtils.convertToInputStream( result );
			int recoveredUI16 = dis.readUI16();
			
			Assert.assertEquals( recoveredUI16, ui16Value );
		}
		catch ( Exception e )
		{
			Assert.fail( "Unexpected exception while serialising an 16-bit unsigned integer", e );
		}
	}
	
	@Test
	public void testWriteUI16Negative()
	{	
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DISOutputStream dos = new DISOutputStream( baos );		
			
			// A value of -1 should fail
			dos.writeUI16( -1 );
			
			// Exception should have been thrown
			Assert.fail( "An IllegalArgumentException should have been thrown for a negative value" );
		}
		catch ( IllegalArgumentException e )
		{
			// Expected
		}
		catch ( Exception e )
		{
			Assert.fail( "Wrong exception received when writing a negative value" );
		}
	}
	
	@Test
	public void testWriteUI16OutOfRange()
	{	
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DISOutputStream dos = new DISOutputStream( baos );		
			
			// A value of beyond the max should fail
			dos.writeUI16( DISSizes.UI16_MAX_VALUE + 1 );
			
			// Exception should have been thrown
			Assert.fail( "An IllegalArgumentException should have been thrown for an out of range value" );
		}
		catch ( IllegalArgumentException e )
		{
			// Expected
		}
		catch ( Exception e )
		{
			Assert.fail( "Wrong exception received when writing an out of range value" );
		}
	}
	
	@Test
	public void testWriteUI32Normal()
	{
		long ui32Value = Integer.MAX_VALUE + 1l;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DISOutputStream dos = new DISOutputStream( baos );
		
		try
		{
			dos.writeUI32( ui32Value );
			
			// Were the raw bytes as we expect? 
			byte[] result = baos.toByteArray();
			Assert.assertTrue( result.length >= 4 );
			Assert.assertEquals( result[0], (byte)0x80 );
			Assert.assertEquals( result[1], (byte)0x00 );
			Assert.assertEquals( result[2], (byte)0x00 );
			Assert.assertEquals( result[3], (byte)0x00 );
			
			// Can it be interpreted by the DISInputStream?
			DISInputStream dis = TestUtils.convertToInputStream( result );
			long recoveredUI32 = dis.readUI32();
			
			Assert.assertEquals( recoveredUI32, ui32Value );
		}
		catch ( Exception e )
		{
			Assert.fail( "Unexpected exception while serialising an 32-bit unsigned integer", e );
		}
	}
	
	@Test
	public void testWriteUI32MaxValue()
	{
		long ui32Value = DISSizes.UI32_MAX_VALUE;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DISOutputStream dos = new DISOutputStream( baos );
		
		try
		{
			dos.writeUI32( ui32Value );
			
			// Were the raw bytes as we expect? 
			byte[] result = baos.toByteArray();
			Assert.assertTrue( result.length >= 4 );
			Assert.assertEquals( result[0], (byte)0xFF );
			Assert.assertEquals( result[1], (byte)0xFF );
			Assert.assertEquals( result[2], (byte)0xFF );
			Assert.assertEquals( result[3], (byte)0xFF );
			
			// Can it be interpreted by the DISInputStream?
			DISInputStream dis = TestUtils.convertToInputStream( result );
			long recoveredUI32 = dis.readUI32();
			
			Assert.assertEquals( recoveredUI32, ui32Value );
		}
		catch ( Exception e )
		{
			Assert.fail( "Unexpected exception while serialising an 32-bit unsigned integer", e );
		}
	}
	
	@Test
	public void testWriteUI32Negative()
	{	
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DISOutputStream dos = new DISOutputStream( baos );		
			
			// A value of -1 should fail
			dos.writeUI32( -1 );
			
			// Exception should have been thrown
			Assert.fail( "An IllegalArgumentException should have been thrown for a negative value" );
		}
		catch ( IllegalArgumentException e )
		{
			// Expected
		}
		catch ( Exception e )
		{
			Assert.fail( "Wrong exception received when writing a negative value" );
		}
	}
	
	@Test
	public void testWriteUI32OutOfRange()
	{	
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DISOutputStream dos = new DISOutputStream( baos );		
			
			// A value of beyond the max should fail
			dos.writeUI32( DISSizes.UI32_MAX_VALUE + 1 );
			
			// Exception should have been thrown
			Assert.fail( "An IllegalArgumentException should have been thrown for an out of range value" );
		}
		catch ( IllegalArgumentException e )
		{
			// Expected
		}
		catch ( Exception e )
		{
			Assert.fail( "Wrong exception received when writing an out of range value" );
		}
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
