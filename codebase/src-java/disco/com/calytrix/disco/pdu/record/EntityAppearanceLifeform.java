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
 * This record is another variant of the Entity Appearance Specific Variant which describes the
 * specific details for Life Forms.
 */
public class EntityAppearanceLifeform
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte lifeformState;
	private byte unused;
	private byte frozenStatus;
	private byte unused2;
	private byte activityState;
	private byte weapon1;
	private byte weapon2;
	private byte entitySpecific;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityAppearanceLifeform( byte lifeformState,
	                                 byte unused,
	                                 byte frozenStatus,
	                                 byte unused2,
	                                 byte activityState,
	                                 byte weapon1,
	                                 byte weapon2,
	                                 byte entitySpecific )
	{
		this.lifeformState = lifeformState;
		this.unused = unused;
		this.frozenStatus = frozenStatus;
		this.unused2 = unused2;
		this.activityState = activityState;
		this.weapon1 = weapon1;
		this.weapon2 = weapon2;
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
		if( this == other )
			return true;
		
		if( other instanceof EntityAppearanceLifeform )
		{
			EntityAppearanceLifeform otherAppearance = (EntityAppearanceLifeform)other;
			if( otherAppearance.lifeformState == this.lifeformState &&
				otherAppearance.unused == this.unused &&
				otherAppearance.frozenStatus == this.frozenStatus &&
				otherAppearance.unused2 == this.unused2 &&
				otherAppearance.activityState == this.activityState &&
				otherAppearance.weapon1 == this.weapon1 &&
				otherAppearance.weapon2 == this.weapon2 &&
				otherAppearance.entitySpecific == this.entitySpecific )
			{
				return true;
			}
		}
		
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public byte getLifeformState()
    {
    	return lifeformState;
    }

	public void setLifeformState( byte lifeformState )
    {
    	this.lifeformState = lifeformState;
    }

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

	public byte getUnused2()
    {
    	return unused2;
    }

	public void setUnused2( byte unused2 )
    {
    	this.unused2 = unused2;
    }

	public byte getActivityState()
    {
    	return activityState;
    }

	public void setActivityState( byte activityState )
    {
    	this.activityState = activityState;
    }

	public byte getWeapon1()
    {
    	return weapon1;
    }

	public void setWeapon1( byte weapon1 )
    {
    	this.weapon1 = weapon1;
    }

	public byte getWeapon2()
    {
    	return weapon2;
    }

	public void setWeapon2( byte weapon2 )
    {
    	this.weapon2 = weapon2;
    }

	public byte getEntitySpecific()
    {
    	return entitySpecific;
    }

	public void setEntitySpecific( byte entitySpecific )
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
	 * @return The EntityAppearanceLifeform deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityAppearanceLifeform read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		int ch2 = dis.read();
		
		if ( (ch1 | ch2) < 0 )
			throw new EOFException();		
		
		byte lifeformState = (byte)((ch1 & 0xF0) >> 4);
		byte unused = (byte)((ch1 & 0x08) >> 3);
		byte frozenStatus = (byte)((ch1 & 0x06) >> 2);
		byte unused2 = (byte)((ch1 & 0x02) >> 1);
		byte activityState = (byte)((ch1 & 0x01) >> 0);
		byte weapon1 = (byte)((ch2 & 0xC0) >> 6);
		byte weapon2 = (byte)((ch2 & 0x30) >> 4);
		byte entitySpecific = (byte)((ch2 & 0x0F) >> 0);

		return new EntityAppearanceLifeform( lifeformState,
		                                     unused,
		                                     frozenStatus,
		                                     unused2,
		                                     activityState,
		                                     weapon1,
		                                     weapon2,
		                                     entitySpecific );
	}
}
