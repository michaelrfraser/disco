/*
 *   Copyright 2011 Calytrix Technologies
 *
 *   This file is part of Calytrix Disco.
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
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.IPDUComponent;
import com.calytrix.disco.util.DISSizes;

/**
 * A collection of boolean fields which describe the capabilities of the Entity.
 */
public class EntityCapabilities implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private boolean ammunitionSupply;
	private boolean fuelSupply;
	private boolean recovery;
	private boolean repair;
	private boolean adsb;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityCapabilities()
	{
		this( false, false, false, false, false );
	}
	
	public EntityCapabilities( boolean ammunitionSupply,
	                           boolean fuelSupply,
	                           boolean recovery,
	                           boolean repair,
	                           boolean adsb)
	{
		this.ammunitionSupply = ammunitionSupply;
		this.fuelSupply = fuelSupply;
		this.recovery = recovery;
		this.repair = repair;
		this.adsb = adsb;
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
		if( this == other )
			return true;
		
		if( other instanceof EntityCapabilities )
		{
			EntityCapabilities otherCapabilities = (EntityCapabilities)other;
			if( otherCapabilities.ammunitionSupply == this.ammunitionSupply &&
			    otherCapabilities.fuelSupply == this.fuelSupply &&
			    otherCapabilities.recovery == this.recovery &&
			    otherCapabilities.repair == this.repair &&
			    otherCapabilities.adsb == this.adsb )
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public EntityCapabilities clone()
	{
		return new EntityCapabilities( ammunitionSupply, fuelSupply, recovery, repair, adsb );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		int ch1 = dis.readInt();
		
		if( ch1 < 0 )
			throw new EOFException();		
		
		ammunitionSupply = (ch1 & 0x01) != 0;
		fuelSupply = (ch1 & 0x02) != 0;
		recovery = (ch1 & 0x04) != 0;
		repair = (ch1 & 0x08) != 0;
		adsb = (ch1 & 0x10) != 0;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		int ch1 = 0;
		
		if( ammunitionSupply )
			ch1 |= 0x01;
		
		if( fuelSupply )
			ch1 |= 0x02;
		
		if( recovery )
			ch1 |= 0x04;
		
		if( repair )
			ch1 |= 0x08;
		
		if ( adsb )
			ch1 |= 0x10;
		
		dos.writeInt( ch1 );
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return DISSizes.UI32_SIZE;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public boolean getAmmunitionSupply()
    {
    	return ammunitionSupply;
    }

	public void setAmmunitionSupply( boolean ammunitionSupply )
    {
    	this.ammunitionSupply = ammunitionSupply;
    }

	public boolean getFuelSupply()
    {
    	return fuelSupply;
    }

	public void setFuelSupply( boolean fuelSupply )
    {
    	this.fuelSupply = fuelSupply;
    }

	public boolean getRecovery()
    {
    	return recovery;
    }

	public void setRecovery( boolean recovery )
    {
    	this.recovery = recovery;
    }

	public boolean getRepair()
    {
    	return repair;
    }

	public void setRepair( boolean repair )
    {
    	this.repair = repair;
    }
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
