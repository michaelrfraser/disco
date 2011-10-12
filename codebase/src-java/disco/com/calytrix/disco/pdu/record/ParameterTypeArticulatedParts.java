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

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;

/**
 * This record represents one of the variants of the Parameter Type Variant, its values are used
 * only when the Parameter Type is Articulated Part (0) rather than Attached Part (1).
 */
public class ParameterTypeArticulatedParts
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private int lowBits;
	private int highBits;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public ParameterTypeArticulatedParts( int lowBits, int highBits )
	{
		this.lowBits = lowBits;
		this.highBits = highBits;
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
		
		if( other instanceof ParameterTypeArticulatedParts )
		{
			ParameterTypeArticulatedParts otherParts = (ParameterTypeArticulatedParts)other;
			if( otherParts.lowBits == this.lowBits && otherParts.highBits == this.highBits )
				return true;
		}

		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public int getLowBits()
    {
    	return lowBits;
    }

	public void setLowBits( int lowBits )
    {
    	this.lowBits = lowBits;
    }

	public int getHighBits()
    {
    	return highBits;
    }

	public void setHighBits( int highBits )
    {
    	this.highBits = highBits;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The ParameterTypeArticulatedParts deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static ParameterTypeArticulatedParts read( DISInputStream dis ) throws IOException
	{
		int lowBits = dis.readUI16();
		int highBits = dis.readUI16();
		
		return new ParameterTypeArticulatedParts( lowBits, highBits );
	}
}
