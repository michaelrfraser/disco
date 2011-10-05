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
 *  This is one of the possible variants of the Entity Appearance Specific Variant.
 */
public class EntityAppearanceLandPlatform
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------	

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte launcher;
	private byte camouflageType;
	private byte concealed;
	private byte unused;
	private byte frozenStatus;
	private byte powerPlantStatus;
	private byte state;
	private byte tent;
	private byte ramp;
	private byte entitySpecific;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	
	public EntityAppearanceLandPlatform( byte launcher, byte camouflageType, byte concealed,
	                                     byte unused, byte frozenStatus, byte powerPlantStatus,
	                                     byte state, byte tent, byte ramp, byte entitySpecific )
	{
		this.launcher = launcher;
		this.camouflageType = camouflageType;
		this.concealed = concealed;
		this.unused = unused;
		this.frozenStatus = frozenStatus;
		this.powerPlantStatus = powerPlantStatus;
		this.state = state;
		this.tent = tent;
		this.ramp = ramp;
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
			if ( other instanceof EntityAppearanceLandPlatform )
			{
				EntityAppearanceLandPlatform asEntityAppearanceLandPlatform = (EntityAppearanceLandPlatform)other;
				equal = asEntityAppearanceLandPlatform.launcher == this.launcher
					&& asEntityAppearanceLandPlatform.camouflageType == this.camouflageType
					&& asEntityAppearanceLandPlatform.concealed == this.concealed
					&& asEntityAppearanceLandPlatform.unused == this.unused
					&& asEntityAppearanceLandPlatform.frozenStatus == this.frozenStatus
					&& asEntityAppearanceLandPlatform.powerPlantStatus == this.powerPlantStatus
					&& asEntityAppearanceLandPlatform.state == this.state
					&& asEntityAppearanceLandPlatform.tent == this.tent
					&& asEntityAppearanceLandPlatform.ramp == this.ramp
					&& asEntityAppearanceLandPlatform.entitySpecific == this.entitySpecific;
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public byte getLauncher()
    {
    	return launcher;
    }

	public void setLauncher( byte launcher )
    {
    	this.launcher = launcher;
    }

	public byte getCamouflageType()
    {
    	return camouflageType;
    }

	public void setCamouflageType( byte camouflageType )
    {
    	this.camouflageType = camouflageType;
    }

	public byte getConcealed()
    {
    	return concealed;
    }

	public void setConcealed( byte concealed )
    {
    	this.concealed = concealed;
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

	public byte getTent()
    {
    	return tent;
    }

	public void setTent( byte tent )
    {
    	this.tent = tent;
    }

	public byte getRamp()
    {
    	return ramp;
    }

	public void setRamp( byte ramp )
    {
    	this.ramp = ramp;
    }

	public short getEntitySpecific()
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
	 * @return The AntennaLocation deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityAppearanceLandPlatform read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		int ch2 = dis.read();
		
		if ( (ch1 | ch2) < 0 )
			throw new EOFException();		
		
		byte launcher = (byte)((ch1 & 0x80) >> 7);
		byte camouflageType = (byte)((ch1 & 0x60) >> 5);
		byte concealed = (byte)((ch1 & 0x10) >> 4);
		byte unused = (byte)((ch1 & 0x08) >> 3);
		byte frozenStatus = (byte)((ch1 & 0x04) >> 2);
		byte powerPlantStatus = (byte)((ch1 & 0x02) >> 1);
		byte state = (byte)((ch1 & 0x01) >> 0);
		byte tent = (byte)((ch2 & 0x80) >> 7);
		byte ramp = (byte)((ch2 & 0x40) >> 6);
		byte entitySpecific = (byte)((ch2 & 0x3F) >> 0);

		return new EntityAppearanceLandPlatform( launcher, camouflageType, concealed, unused,
		                                         frozenStatus, powerPlantStatus, state, tent, ramp,
		                                         entitySpecific );
	}
}
