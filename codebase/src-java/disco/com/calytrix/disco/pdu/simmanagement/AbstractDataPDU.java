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
package com.calytrix.disco.pdu.simmanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.record.FixedDatumRecord;
import com.calytrix.disco.pdu.record.PDUHeader;
import com.calytrix.disco.pdu.record.VariableDatumRecord;
import com.calytrix.disco.util.DISSizes;

/**
 * Abstract PDU that contains fields common to all Data PDUs
 */
public abstract class AbstractDataPDU extends AbstractManagementPDU
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private long requestID;
	private List<FixedDatumRecord> fixedDatumRecords;
	private List<VariableDatumRecord> variableDatumRecords;
		
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	/**
	 * Constructor for type AbstractDataPDU with specified PDUHeader
	 * 
	 * @param header The <code>PDUHeader</code> to base this PDU on
	 */
	public AbstractDataPDU( PDUHeader header )
	{
		super( header );
				
		this.requestID = 0;
		this.fixedDatumRecords = new ArrayList<FixedDatumRecord>();
		this.variableDatumRecords = new ArrayList<VariableDatumRecord>();
	}

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readContent( DISInputStream dis ) throws IOException
	{
		super.readContent( dis );
		
		requestID = dis.readUI32();
		dis.skip32(); // padding
		
		long fixedDatumRecordCount = dis.readUI32();
		long variableDatumRecordCount = dis.readUI32();
		
		if( fixedDatumRecordCount > Integer.MAX_VALUE )
		{
			// LOG Warning regarding truncation
		}
		
		fixedDatumRecords.clear();
		for( int i = 0 ; i < fixedDatumRecordCount ; ++i )
		{
			FixedDatumRecord fixedDatumRecord = new FixedDatumRecord();
			fixedDatumRecord.read( dis );
			fixedDatumRecords.add( fixedDatumRecord );
		}
		
		if( variableDatumRecordCount > Integer.MAX_VALUE )
		{
			// LOG Warning regarding truncation
		}
		
		variableDatumRecords.clear();
		for( int i = 0 ; i < variableDatumRecordCount ; ++i )
		{
			VariableDatumRecord variableDatumRecord = new VariableDatumRecord();
			variableDatumRecord.read( dis );
			variableDatumRecords.add( variableDatumRecord );
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeContent( DISOutputStream dos ) throws IOException
	{
		super.writeContent( dos );
		
		dos.writeUI32( requestID );
		dos.writePadding32(); // padding
		
		dos.writeUI32( fixedDatumRecords.size() );
		dos.writeUI32( variableDatumRecords.size() );
		
		for( FixedDatumRecord fixed : fixedDatumRecords )
			fixed.write( dos );

		for( VariableDatumRecord variable : variableDatumRecords )
			variable.write( dos );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getContentLength()
	{
		int size = super.getContentLength();
		size += DISSizes.UI32_SIZE;		// Request ID
		size += 4;						// Padding
		size += DISSizes.UI32_SIZE * 2; // Fixed Record Count, Variable Record Count
		size += DISSizes.getByteLengthOfCollection( fixedDatumRecords );
		size += DISSizes.getByteLengthOfCollection( variableDatumRecords );
		
		return size;
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
	public long getRequestID()
	{
		return requestID;
	}
	
	public void setRequestID( long requestID )
	{
		this.requestID = requestID;
	}
	
	public List<FixedDatumRecord> getFixedDatumRecords()
	{
		return fixedDatumRecords;
	}
	
	public void setFixedDatumRecords( List<FixedDatumRecord> fixedDatumRecords )
	{
		this.fixedDatumRecords = fixedDatumRecords;
	}
	
	public List<VariableDatumRecord> getVariableDatumRecords()
	{
		return variableDatumRecords;
	}
	
	public void setVariableDatumRecords( List<VariableDatumRecord> variableDatumRecords )
	{
		this.variableDatumRecords = variableDatumRecords;
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
