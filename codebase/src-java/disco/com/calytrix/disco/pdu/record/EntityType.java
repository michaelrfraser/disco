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
 * The type of entity in a DIS exercise shall be specified by an Entity Type record. 
 * This record shall specify the kind of entity, the country of design, the domain, 
 * the specific identification of the entity, and any extra information necessary for 
 * describing the entity. Fields not used shall contain the value zero.
 */
public class EntityType
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short entityKind;
	private short domain;
	private int country;
	private short category;
	private short subcategory;
	private short specific;
	private short extra; 
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityType( short entityKind,
	                   short domain,
	                   int country, 
	                   short category,
	                   short subcategory,
	                   short specific,
	                   short extra )
	{
		this.entityKind = entityKind;
		this.domain = domain;
		this.country = country;
		this.category = category;
		this.subcategory = subcategory;
		this.specific = specific;
		this.extra = extra;
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
		
		if( other instanceof EntityType )
		{
			EntityType otherType = (EntityType)other;
			if( otherType.entityKind == entityKind &&
				otherType.domain == domain &&
				otherType.country == country &&
				otherType.category == category &&
				otherType.subcategory == subcategory &&
				otherType.specific == specific &&
				otherType.extra == extra )
			{
				return false;
			}
		}

		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
    public short getEntityKind()
    {
    	return entityKind;
    }

    public void setEntityKind( short entityKind )
    {
    	this.entityKind = entityKind;
    }

    public short getDomain()
    {
    	return domain;
    }

    public void setDomain( short domain )
    {
    	this.domain = domain;
    }

    public int getCountry()
    {
    	return country;
    }

    public void setCountry( int country )
    {
    	this.country = country;
    }

    public short getCategory()
    {
    	return category;
    }

    public void setCategory( short category )
    {
    	this.category = category;
    }

    public short getSubcategory()
    {
    	return subcategory;
    }

    public void setSubcategory( short subcategory )
    {
    	this.subcategory = subcategory;
    }

    public short getSpecific()
    {
    	return specific;
    }
    
    public void setSpecific( short specific )
    {
    	this.specific = specific;
    }

    public short getExtra()
    {
    	return extra;
    }

    public void setExtra( short extra )
    {
    	this.extra = extra;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EntityType deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityType read( DISInputStream dis ) throws IOException
	{
		short entityKind = dis.readUI8();
		short domain = dis.readUI8();
		int country = dis.readUI16();
		short category = dis.readUI8();
		short subcategory = dis.readUI8();
		short specific = dis.readUI8();
		short extra = dis.readUI8();
		
		return new EntityType( entityKind, domain, country, category,
		                       subcategory, specific, extra );
	}
}
