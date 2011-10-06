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
 * This record describes the Entity Appearance Specific variant for the Environment kind.
 */
public class EntityAppearanceEnvironmental
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private byte density;
	private byte unused;
	private short environmentalSpecific;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityAppearanceEnvironmental( byte density, byte unused, short environmentalSpecific )
	{
		this.density = density;
		this.unused = unused;
		this.environmentalSpecific = environmentalSpecific;
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
			if ( other instanceof EntityAppearanceEnvironmental )
			{
				EntityAppearanceEnvironmental asEntityAppearanceEnvironmental = (EntityAppearanceEnvironmental)other;
				equal = asEntityAppearanceEnvironmental.density == this.density
					&& asEntityAppearanceEnvironmental.unused == this.unused
					&& asEntityAppearanceEnvironmental.environmentalSpecific == this.environmentalSpecific;
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public byte getDensity()
    {
    	return density;
    }

	public void setDensity( byte density )
    {
    	this.density = density;
    }

	public byte getUnused()
    {
    	return unused;
    }

	public void setUnused( byte unused )
    {
    	this.unused = unused;
    }

	public short getEnvironmentalSpecific()
    {
    	return environmentalSpecific;
    }

	public void setEnvironmentalSpecific( short environmentalSpecific )
    {
    	this.environmentalSpecific = environmentalSpecific;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EntityAppearanceEnvironmental deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityAppearanceEnvironmental read( DISInputStream dis ) throws IOException
	{
		int ch1 = dis.read();
		
		if ( ch1 < 0 )
			throw new EOFException();		
		
		byte density = (byte)((ch1 & 0xF0) >> 4);
		byte unused = (byte)((ch1 & 0x0F) >> 0);
		short environmentalSpecific = dis.readUI8();

		return new EntityAppearanceEnvironmental( density, unused, environmentalSpecific );
	}
}
