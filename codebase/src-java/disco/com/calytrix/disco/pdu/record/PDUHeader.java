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

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.field.PDUType;
import com.calytrix.disco.pdu.field.ProtocolFamily;
import com.calytrix.disco.pdu.field.ProtocolVersion;
import com.calytrix.disco.util.DISSizes;

/**
 * A PDU Header Record shall be the first part of each PDU.
 */
public class PDUHeader implements Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int PDU_HEADER_LENGTH_BYTES = 12;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short protocolVersion;
	private short exerciseIdentifier;
	private short pduType;
	private short protocolFamily;
	private long timestamp;
		
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public PDUHeader()
	{
		this( ProtocolVersion.OTHER, 
		      (short)0, 
		      PDUType.OTHER, 
		      ProtocolFamily.OTHER, 
		      0 );
	}
	
	public PDUHeader( short protocolVersion,
	                  short exerciseIdentifier, 
	                  short pduType,
	                  short protocolFamily,
	                  long timestamp )
	{
		this.protocolVersion = protocolVersion;
		this.exerciseIdentifier = exerciseIdentifier;
		this.pduType = pduType;
		this.protocolFamily = protocolFamily;
		this.timestamp = timestamp;
	}
		
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals( Object other )
	{
		if( other == this )
			return true;
		
		if( other instanceof PDUHeader )
		{
			PDUHeader asPDUHeader = (PDUHeader)other;
			if( asPDUHeader.protocolVersion == this.protocolVersion &&
				asPDUHeader.exerciseIdentifier == this.exerciseIdentifier &&
				asPDUHeader.pduType == this.pduType &&
				asPDUHeader.protocolFamily == this.protocolFamily &&
				asPDUHeader.timestamp == this.timestamp )
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PDUHeader clone()
	{
		return new PDUHeader( protocolVersion, exerciseIdentifier, pduType, 
		                      protocolFamily, timestamp );
	}
	
    public void read( DISInputStream dis ) throws IOException
    {
		protocolVersion = dis.readUI8();
		exerciseIdentifier = dis.readUI8();
		pduType = dis.readUI8();
		protocolFamily = dis.readUI8();
		timestamp = dis.readUI32();
		
		dis.readUI16(); // Length
		dis.readUI16(); // padding bytes
    }

    public void write( DISOutputStream dos, int contentLength ) throws IOException
    {
    	int totalLength = getByteLength() + contentLength;
    	
    	dos.writeUI8( protocolVersion );
    	dos.writeUI8( exerciseIdentifier );
    	dos.writeUI8( pduType );
    	dos.writeUI8( protocolFamily );
    	dos.writeUI32( timestamp );
    	dos.writeUI16( totalLength );
    	dos.writePadding16();
    }
    
    public int getByteLength()
    {
    	int size = ProtocolVersion.BYTE_LENGTH;
    	size += DISSizes.UI8_SIZE;		// Exercise ID
    	size += PDUType.BYTE_LENGTH;
    	size += ProtocolFamily.BYTE_LENGTH;
    	size += DISSizes.UI32_SIZE;		// Time Stamp
    	size += DISSizes.UI16_SIZE;		// PDU Length
    	size += 2;						// Padding
    	
    	return size;
    }
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public short getProtocolVersion()
	{
		return protocolVersion;
	}
	
	public void setProtocolVersion( short protocolVersion )
	{
		this.protocolVersion = protocolVersion;
	}

	public short getExerciseIdentifier()
	{
		return exerciseIdentifier;
	}
	
	public void setExerciseIdentifier( short exerciseIdentifier )
	{
		this.exerciseIdentifier = exerciseIdentifier;
	}
	
	public short getPDUType()
	{
		return pduType;
	}
	
	public void setPDUType( short pduType )
	{
		this.pduType = pduType;
	}
	
	public short getProtocolFamily()
	{
		return protocolFamily;
	}
	
	public void setProtocolFamily( short protocolFamily )
	{
		this.protocolFamily = protocolFamily;
	}
	
	public long getTimestamp()
	{
		return timestamp;
	}
	
	public void setTimestamp( long timestamp )
	{
		this.timestamp = timestamp; 
	}
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
