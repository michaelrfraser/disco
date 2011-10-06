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
 * The General Appearance record represents those attributes which are generally applicable to all
 * entities.
 */
public class EntityAppearanceGeneral
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte entityPaintScheme;
	private byte entityMobilityKill;
	private byte entityFirePower;
	private byte entityDamage;
	private byte entitySmoke;
	private byte entityTrailingEffect;
	private byte entityHatchState;
	private byte entityLights;
	private byte entityFlamingEffect;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityAppearanceGeneral( byte entityPaintScheme, byte entityMobilityKill,
	                                byte entityFirePower, byte entityDamage,
	                                byte entitySmoke, byte entityTrailingEffect,
	                                byte entityHatchState, byte entityLights,
	                                byte entityFlamingEffects )
	{
		this.entityPaintScheme = entityPaintScheme;
		this.entityMobilityKill = entityMobilityKill;
		this.entityFirePower = entityFirePower;
		this.entityDamage = entityDamage;
		this.entitySmoke = entitySmoke;
		this.entityTrailingEffect = entityTrailingEffect;
		this.entityHatchState = entityHatchState;
		this.entityLights = entityLights;
		this.entityFlamingEffect = entityFlamingEffects;
		
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
			if ( other instanceof EntityAppearanceGeneral )
			{
				EntityAppearanceGeneral asEntityAppearanceGeneral = (EntityAppearanceGeneral)other;
				equal = asEntityAppearanceGeneral.entityPaintScheme == this.entityPaintScheme
					&& asEntityAppearanceGeneral.entityMobilityKill == this.entityMobilityKill
					&& asEntityAppearanceGeneral.entityFirePower == this.entityFirePower
					&& asEntityAppearanceGeneral.entityDamage == this.entityDamage
					&& asEntityAppearanceGeneral.entitySmoke == this.entitySmoke
					&& asEntityAppearanceGeneral.entityTrailingEffect == this.entityTrailingEffect
					&& asEntityAppearanceGeneral.entityHatchState == this.entityHatchState
					&& asEntityAppearanceGeneral.entityLights == this.entityLights
					&& asEntityAppearanceGeneral.entityFlamingEffect == this.entityFlamingEffect;
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
    public short getEntityPaintScheme()
    {
    	return entityPaintScheme;
    }

    public void setEntityPaintScheme( byte entityPaintScheme )
    {
    	this.entityPaintScheme = entityPaintScheme;
    }

    public short getEntityMobilityKill()
    {
    	return entityMobilityKill;
    }

    public void setEntityMobilityKill( byte entityMobilityKill )
    {
    	this.entityMobilityKill = entityMobilityKill;
    }

    public short getEntityFirePower()
    {
    	return entityFirePower;
    }

    public void setEntityFirePower( byte entityFirePower )
    {
    	this.entityFirePower = entityFirePower;
    }

    public short getEntityDamage()
    {
    	return entityDamage;
    }

    public void setEntityDamage( byte entityDamage )
    {
    	this.entityDamage = entityDamage;
    }

    public short getEntitySmoke()
    {
    	return entitySmoke;
    }

    public void setEntitySmoke( byte entitySmoke )
    {
    	this.entitySmoke = entitySmoke;
    }

    public short getEntityTrailingEffect()
    {
    	return entityTrailingEffect;
    }

    public void setEntityTrailingEffect( byte entityTrailingEffect )
    {
    	this.entityTrailingEffect = entityTrailingEffect;
    }

    public short getEntityHatchState()
    {
    	return entityHatchState;
    }

    public void setEntityHatchState( byte entityHatchState )
    {
    	this.entityHatchState = entityHatchState;
    }

    public short getEntityLights()
    {
    	return entityLights;
    }

    public void setEntityLights( byte entityLights )
    {
    	this.entityLights = entityLights;
    }

    public short getEntityFlamingEffect()
    {
    	return entityFlamingEffect;
    }

    public void setEntityFlamingEffect( byte entityFlamingEffect )
    {
    	this.entityFlamingEffect = entityFlamingEffect;
    }
	
	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EntityAppearanceGeneral deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityAppearanceGeneral read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		int ch2 = dis.read();
		
		if ( (ch1 | ch2) < 0 )
			throw new EOFException();		
		
		byte entityPaintScheme = (byte)((ch1 & 0x80) >> 7);
		byte entityMobilityKill = (byte)((ch1 & 0x40) >> 6);
		byte entityFirePower = (byte)((ch1 & 0x20) >> 5);
		byte entityDamage = (byte)((ch1 & 0x18) >> 3);
		byte entitySmoke = (byte)((ch1 & 0x06) >> 1);
		byte entityTrailingEffect = (byte)(((ch1 & 0x01) << 1) | ((ch2 & 0x80) >> 7));
		byte entityHatchState = (byte)((ch2 & 0x70) >> 4);
		byte entityLights = (byte)((ch2 & 0x0E) >> 1);
		byte entityFlamingEffect = (byte)((ch2 & 0x01) >> 0);
		
		return new EntityAppearanceGeneral( entityPaintScheme, entityMobilityKill, entityFirePower,
		                                    entityDamage, entitySmoke, entityTrailingEffect,
		                                    entityHatchState, entityLights, entityFlamingEffect );
	}
}
