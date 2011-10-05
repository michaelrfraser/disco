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
 * This is a generic (non-specific) platform record which is used by several of the Entity
 * Appearance Specific Variants.
 */
public class EntityAppearanceGenericPlatform
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte unused;
	private byte frozenStatus;
	private byte powerPlantStatus;
	private byte state;
	private short entitySpecific;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityAppearanceGenericPlatform( byte unused, byte frozenStatus, byte powerPlantStatus,
	                                        byte state, short entitySpecific )
	{
		this.unused = unused;
		this.frozenStatus = frozenStatus;
		this.powerPlantStatus = powerPlantStatus;
		this.state = state;
		this.entitySpecific = entitySpecific;
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
			if ( other instanceof EntityAppearanceGenericPlatform )
			{
				EntityAppearanceGenericPlatform asEntityAppearanceGenericPlatform = (EntityAppearanceGenericPlatform)other;
				equal = asEntityAppearanceGenericPlatform.unused == this.unused
					&& asEntityAppearanceGenericPlatform.frozenStatus == this.frozenStatus
					&& asEntityAppearanceGenericPlatform.powerPlantStatus == this.powerPlantStatus
					&& asEntityAppearanceGenericPlatform.state == this.state
					&& asEntityAppearanceGenericPlatform.entitySpecific == this.entitySpecific;
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public byte getUnused()
    {
    	return unused;
    }

	public void setUnused( byte unused )
    {
    	this.unused = unused;
    }

	public byte getFrozenStatus()
    {
    	return frozenStatus;
    }

	public void setFrozenStatus( byte frozenStatus )
    {
    	this.frozenStatus = frozenStatus;
    }

	public byte getPowerPlantStatus()
    {
    	return powerPlantStatus;
    }

	public void setPowerPlantStatus( byte powerPlantStatus )
    {
    	this.powerPlantStatus = powerPlantStatus;
    }

	public byte getState()
    {
    	return state;
    }

	public void setState( byte state )
    {
    	this.state = state;
    }

	public short getEntitySpecific()
    {
    	return entitySpecific;
    }

	public void setEntitySpecific( short entitySpecific )
    {
    	this.entitySpecific = entitySpecific;
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
	public static EntityAppearanceGenericPlatform read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		
		if ( ch1 < 0 )
			throw new EOFException();		
		
		byte unused = (byte)((ch1 & 0xF8) >> 3);
		byte frozenStatus = (byte)((ch1 & 0x04) >> 2);
		byte powerPlantStatus = (byte)((ch1 & 0x02) >> 1);
		byte state = (byte)((ch1 & 0x01) >> 0);
		short entitySpecific = dis.readUI8();

		return new EntityAppearanceGenericPlatform( unused, frozenStatus, powerPlantStatus,
		                                        state, entitySpecific );
	}
}
