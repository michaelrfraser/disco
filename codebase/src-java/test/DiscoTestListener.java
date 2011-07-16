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
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This class implements a TestNG test listener that will print out information about the passage
 * of a testing run as it happens. By default, no information is logged by TestNG unless the
 * verbosity level is turned up. If you do turn it up by 1 level (from 1 to 2 - out of 10), way
 * too much information is printed out. I just want to see whether tests are passing or failing.
 * This will take care of that for me.
 * <p/>
 * Sample report:
 * <p/>
 * <pre>
 * P testValidCreate
 * P testInvalidCreate
 * F testSomethingFailed
 * S testThatWasSkipped
 * Z testThatFailedByPassedByPercentage
 * ...
 * </pre>
 */
public class DiscoTestListener implements ITestListener
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
	
	public void onTestStart( ITestResult result )
	{
		//String className = result.getTestClass().getRealClass().getSimpleName();
		//String methodName = result.getMethod().getMethod().getName();
	}

	public void onTestSuccess( ITestResult result )
	{
		System.out.println( "     " + result.getName() );
	}

	public void onTestFailure( ITestResult result )
	{
		if( result.getName().equals("beforeClass") ||
			result.getName().equals("afterClass") ||
			result.getName().equals("beforeMethod") ||
			result.getName().equals("afterMethod") )
		{
			System.out.println( "FAIL " + result.getName() +
			                    " (" +result.getTestClass().getRealClass().getSimpleName()+ "): " +
			                    result.getThrowable() );
		}
		else
		{
			System.out.println( "FAIL " + result.getName() );
		}
	}

	public void onTestSkipped( ITestResult result )
	{
		System.out.println( "SKIP " + result.getName() +
		                    " (" +result.getTestClass().getRealClass().getSimpleName()+ ")" );

	}

	public void onTestFailedButWithinSuccessPercentage( ITestResult result )
	{
		System.out.println( "     " + result.getName() );
	}

	public void onStart( ITestContext context )
	{
	}

	public void onFinish( ITestContext context )
	{
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
