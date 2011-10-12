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

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;

/**
 * Specifies the dynamic changes to the entities appearance attributes.
 */
public class EntityAppearance
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityAppearanceGeneral generalAppearance;
	private EntityAppearanceSpecificVariant specificAppearanceVariant;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityAppearance( EntityAppearanceGeneral generalAppearance,
	                         EntityAppearanceSpecificVariant specificAppearanceVariant )
	{
		this.generalAppearance = generalAppearance;
		this.specificAppearanceVariant = specificAppearanceVariant;
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
		
		if( other instanceof EntityAppearance )
		{
			EntityAppearance otherAppearance = (EntityAppearance)other;
			if( otherAppearance.generalAppearance.equals(this.generalAppearance) &&
				otherAppearance.specificAppearanceVariant.equals(this.specificAppearanceVariant) )
			{
				return true;
			}
		}
		
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public EntityAppearanceGeneral getGeneralAppearance()
	{
		return generalAppearance;
	}

	public void setGeneralAppearance( EntityAppearanceGeneral generalAppearance )
	{
		this.generalAppearance = generalAppearance;
	}
	
	public EntityAppearanceSpecificVariant getSpecificAppearanceVariant()
    {
    	return specificAppearanceVariant;
    }

	public void setSpecificAppearanceVariant( EntityAppearanceSpecificVariant specificAppearanceVariant )
    {
    	this.specificAppearanceVariant = specificAppearanceVariant;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EntityAppearance deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityAppearance read( DISInputStream dis ) throws IOException
	{
		EntityAppearanceGeneral generalAppearance = EntityAppearanceGeneral.read( dis );
		EntityAppearanceSpecificVariant specificAppearanceVariant =
		    EntityAppearanceSpecificVariant.read( dis );

		return new EntityAppearance( generalAppearance, specificAppearanceVariant );
	}
}
