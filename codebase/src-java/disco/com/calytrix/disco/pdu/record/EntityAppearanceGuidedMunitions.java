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
 * This record describes the Entity Appearance Specific variant for Guided Munitions.
 */
public class EntityAppearanceGuidedMunitions
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte launchFlash;
	private byte unused;
	private byte frozenStatus;
	private byte unused2;
	private byte state;
	private short entitySpecific;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityAppearanceGuidedMunitions( byte launchFlash, byte unused, byte frozenStatus,
	                                        byte unused2, byte state, short entitySpecific )
	{
		this.launchFlash = launchFlash;
		this.unused = unused;
		this.frozenStatus = frozenStatus;
		this.unused2 = unused2;
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
			if ( other instanceof EntityAppearanceGuidedMunitions )
			{
				EntityAppearanceGuidedMunitions asEntityAppearanceGuidedMunitions = (EntityAppearanceGuidedMunitions)other;
				equal = asEntityAppearanceGuidedMunitions.launchFlash == this.launchFlash
					&& asEntityAppearanceGuidedMunitions.unused == this.unused
					&& asEntityAppearanceGuidedMunitions.frozenStatus == this.frozenStatus
					&& asEntityAppearanceGuidedMunitions.unused2 == this.unused2
					&& asEntityAppearanceGuidedMunitions.state == this.state
					&& asEntityAppearanceGuidedMunitions.entitySpecific == this.entitySpecific;
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public byte getLaunchFlash()
    {
    	return launchFlash;
    }

	public void setLaunchFlash( byte launchFlash )
    {
    	this.launchFlash = launchFlash;
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
	 * @return The EntityAppearanceGuidedMunitions deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityAppearanceGuidedMunitions read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		
		if ( ch1 < 0 )
			throw new EOFException();		
		
		byte launchFlash = (byte)((ch1 & 0x80) >> 7);
		byte unused = (byte)((ch1 & 0x78) >> 3);
		byte frozenStatus = (byte)((ch1 & 0x04) >> 2);
		byte unused2 = (byte)((ch1 & 0x02) >> 1);
		byte state = (byte)((ch1 & 0x01) >> 0);
		short entitySpecific = dis.readUI8();

		return new EntityAppearanceGuidedMunitions( launchFlash, unused, frozenStatus, unused2,
		                                        state, entitySpecific );
	}
}
