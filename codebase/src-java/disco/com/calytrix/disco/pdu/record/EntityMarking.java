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
 * This record shall be used to specify the character set used in the marking and the string of
 * characters to be interpreted for display. The character set shall be represented by an 8-bit
 * enumeration. The string of characters shall be represented by an 11 element character string.
 */
public class EntityMarking
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short entityMarkingCharacterSet;
	private EntityMarkingString entityMarkingString;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityMarking( short entityMarkingCharacterSet, EntityMarkingString entityMarkingString )
	{
		this.entityMarkingCharacterSet = entityMarkingCharacterSet;
		this.entityMarkingString = entityMarkingString;
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
			if ( other instanceof EntityMarking )
			{
				EntityMarking asEntityMarking = (EntityMarking)other;
				equal = asEntityMarking.entityMarkingCharacterSet == this.entityMarkingCharacterSet
					&& asEntityMarking.entityMarkingString.equals( this.entityMarkingString );
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public short getEntityMarkingCharacterSet()
    {
    	return entityMarkingCharacterSet;
    }

	public void setEntityMarkingCharacterSet( short entityMarkingCharacterSet )
    {
    	this.entityMarkingCharacterSet = entityMarkingCharacterSet;
    }

	public EntityMarkingString getEntityMarkingString()
    {
    	return entityMarkingString;
    }

	public void setEntityMarkingString( EntityMarkingString entityMarkingString )
    {
    	this.entityMarkingString = entityMarkingString;
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
	public static EntityMarking read( DISInputStream dis ) throws IOException
	{
		short entityMarkingCharacterSet = dis.readUI8();
		EntityMarkingString entityMarkingString = EntityMarkingString.read( dis );
		
		return new EntityMarking( entityMarkingCharacterSet, entityMarkingString );
	}
}
