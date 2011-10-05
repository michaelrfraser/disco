/*
 *   Copyright 2011 Calytrix Technologies
 *
 *   This file is part of cuttlefish.
 *
 *   NOTICE:  All information contained herein is, and remains
 *            the property of Calytrix Technologies Pty Ltd.
 *            The intellectual and technical concepts contained
 *            herein are proprietary to Calytrix Technologies Pty Ltd.
 *            Dissemination of this information or reproduction of
 *            this material is strictly forbidden unless prior written
 *            permission is obtained from Calytrix Technologies Pty Ltd.
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 */
package com.calytrix.disco.pdu.record;

import java.io.EOFException;
import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;

/**
 * A collection of boolean fields which describe the capabilities of the Entity.
 * 
 */
public class EntityCapabilities
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	private static final int UNUSED_ARRAY_SIZE = 4;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte ammunitionSupply;
	private byte fuelSupply;
	private byte recovery;
	private byte repair;
	private byte[] unused;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityCapabilities( byte ammunitionSupply, byte fuelSupply, byte recovery,
	                           byte repair, byte[] unused )
	{
		this.ammunitionSupply = ammunitionSupply;
		this.fuelSupply = fuelSupply;
		this.recovery = recovery;
		this.repair = repair;
		setUnused( unused );
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
		boolean equal = false;
		
		if ( other == this )
		{
			equal = true;
		}
		else
		{
			if ( other instanceof EntityCapabilities )
			{
				EntityCapabilities asEntityCapabilities = (EntityCapabilities)other;
				equal = asEntityCapabilities.ammunitionSupply == this.ammunitionSupply
					&& asEntityCapabilities.fuelSupply == this.fuelSupply
					&& asEntityCapabilities.recovery == this.recovery
					&& asEntityCapabilities.repair == this.repair
					&& asEntityCapabilities.unused == this.unused;
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public byte getAmmunitionSupply()
    {
    	return ammunitionSupply;
    }

	public void setAmmunitionSupply( byte ammunitionSupply )
    {
    	this.ammunitionSupply = ammunitionSupply;
    }

	public byte getFuelSupply()
    {
    	return fuelSupply;
    }

	public void setFuelSupply( byte fuelSupply )
    {
    	this.fuelSupply = fuelSupply;
    }

	public byte getRecovery()
    {
    	return recovery;
    }

	public void setRecovery( byte recovery )
    {
    	this.recovery = recovery;
    }

	public byte getRepair()
    {
    	return repair;
    }

	public void setRepair( byte repair )
    {
    	this.repair = repair;
    }
	
	public byte[] getUnused()
    {
    	return unused;
    }

	public void setUnused( byte[] unused )
    {
		if ( unused.length != UNUSED_ARRAY_SIZE )
			throw new IllegalStateException( "Unused BLOB must be aligned to 28bit boundary" );
    	this.unused = unused;
    }
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The AntennaLocation deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityCapabilities read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		
		if( ch1 < 0 )
			throw new EOFException();		
		
		byte ammunitionSupply = (byte)((ch1 & 0x80) >> 7);
		byte fuelSupply = (byte)((ch1 & 0x40) >> 6);
		byte recovery = (byte)((ch1 & 0x20) >> 5);
		byte repair = (byte)((ch1 & 0x10) >> 4);
		
		byte[] unused = new byte[UNUSED_ARRAY_SIZE];
		unused[0] = (byte)((ch1 & 0x0F) >> 0);
		byte[] temp = new byte[UNUSED_ARRAY_SIZE - 1];
		dis.readFully( temp );
		for( int i = 0; i < UNUSED_ARRAY_SIZE - 1; i++ )
		{
			unused[i+1] = temp[i];
		}
		
		return new EntityCapabilities( ammunitionSupply, fuelSupply, recovery, repair, unused );
	}
}
