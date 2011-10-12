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
 * The upper 16 bits of the Entity Appearance Record are defined specifically for each entity 
 * kind.
 */
public class EntityAppearanceSpecificVariant
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private EntityAppearanceLandPlatform landPlatforms;
	private EntityAppearanceAirPlatform airPlatforms;
	private EntityAppearanceGenericPlatform surfacePlatforms;
	private EntityAppearanceGenericPlatform subsurfacePlatforms;
	private EntityAppearanceGenericPlatform spacePlatforms;
	private EntityAppearanceGuidedMunitions guidedMunitions;
	private EntityAppearanceLifeform lifeforms;
	private EntityAppearanceEnvironmental environmentals;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityAppearanceSpecificVariant( EntityAppearanceLandPlatform landPlatforms,
	                                        EntityAppearanceAirPlatform airPlatforms,
	                                        EntityAppearanceGenericPlatform surfacePlatforms,
	                                        EntityAppearanceGenericPlatform subsurfacePlatforms,
	                                        EntityAppearanceGenericPlatform spacePlatforms,
	                                        EntityAppearanceGuidedMunitions guidedMunitions,
	                                        EntityAppearanceLifeform lifeforms,
	                                        EntityAppearanceEnvironmental environmentals )
	{
		this.landPlatforms = landPlatforms;
		this.airPlatforms = airPlatforms;
		this.surfacePlatforms = surfacePlatforms;
		this.subsurfacePlatforms = subsurfacePlatforms;
		this.spacePlatforms = spacePlatforms;
		this.guidedMunitions = guidedMunitions;
		this.lifeforms = lifeforms;
		this.environmentals = environmentals;
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

		if( other instanceof EntityAppearanceSpecificVariant )
		{
			EntityAppearanceSpecificVariant otherAppearance = (EntityAppearanceSpecificVariant)other;
			if( otherAppearance.landPlatforms == this.landPlatforms &&
			    otherAppearance.airPlatforms == this.airPlatforms &&
			    otherAppearance.surfacePlatforms == this.surfacePlatforms &&
			    otherAppearance.subsurfacePlatforms == this.subsurfacePlatforms &&
			    otherAppearance.spacePlatforms == this.spacePlatforms &&
			    otherAppearance.guidedMunitions == this.guidedMunitions &&
			    otherAppearance.lifeforms == this.lifeforms &&
			    otherAppearance.environmentals == this.environmentals )
			{
				return true;
			}
		}
		
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	public EntityAppearanceLandPlatform getLandPlatforms()
    {
    	return landPlatforms;
    }

	public void setLandPlatforms( EntityAppearanceLandPlatform landPlatforms )
    {
    	this.landPlatforms = landPlatforms;
    }

	public EntityAppearanceAirPlatform getAirPlatforms()
    {
    	return airPlatforms;
    }

	public void setAirPlatforms( EntityAppearanceAirPlatform airPlatforms )
    {
    	this.airPlatforms = airPlatforms;
    }

	public EntityAppearanceGenericPlatform getSurfacePlatforms()
    {
    	return surfacePlatforms;
    }

	public void setSurfacePlatforms( EntityAppearanceGenericPlatform surfacePlatforms )
    {
    	this.surfacePlatforms = surfacePlatforms;
    }

	public EntityAppearanceGenericPlatform getSubsurfacePlatforms()
    {
    	return subsurfacePlatforms;
    }

	public void setSubsurfacePlatforms( EntityAppearanceGenericPlatform subsurfacePlatforms )
    {
    	this.subsurfacePlatforms = subsurfacePlatforms;
    }

	public EntityAppearanceGenericPlatform getSpacePlatforms()
    {
    	return spacePlatforms;
    }

	public void setSpacePlatforms( EntityAppearanceGenericPlatform spacePlatforms )
    {
    	this.spacePlatforms = spacePlatforms;
    }

	public EntityAppearanceGuidedMunitions getGuidedMunitions()
    {
    	return guidedMunitions;
    }

	public void setGuidedMunitions( EntityAppearanceGuidedMunitions guidedMunitions )
    {
    	this.guidedMunitions = guidedMunitions;
    }

	public EntityAppearanceLifeform getLifeforms()
    {
    	return lifeforms;
    }

	public void setLifeforms( EntityAppearanceLifeform lifeforms )
    {
    	this.lifeforms = lifeforms;
    }

	public EntityAppearanceEnvironmental getEnvironmentals()
    {
    	return environmentals;
    }

	public void setEnvironmentals( EntityAppearanceEnvironmental environmentals )
    {
    	this.environmentals = environmentals;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EntityAppearanceSpecificVariant deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityAppearanceSpecificVariant read( DISInputStream dis ) throws IOException
	{
		EntityAppearanceLandPlatform landPlatforms = EntityAppearanceLandPlatform.read(dis);
		EntityAppearanceAirPlatform airPlatforms = EntityAppearanceAirPlatform.read(dis);
		EntityAppearanceGenericPlatform surfacePlatforms = EntityAppearanceGenericPlatform.read(dis);
		EntityAppearanceGenericPlatform subsurfacePlatforms = EntityAppearanceGenericPlatform.read(dis);
		EntityAppearanceGenericPlatform spacePlatforms = EntityAppearanceGenericPlatform.read(dis);
		EntityAppearanceGuidedMunitions guidedMunitions = EntityAppearanceGuidedMunitions.read(dis);
		EntityAppearanceLifeform lifeforms = EntityAppearanceLifeform.read(dis);
		EntityAppearanceEnvironmental environmentals = EntityAppearanceEnvironmental.read(dis);
		
		return new EntityAppearanceSpecificVariant( landPlatforms,
		                                            airPlatforms,
		                                            surfacePlatforms, 
		                                            subsurfacePlatforms,
		                                            spacePlatforms,
		                                            guidedMunitions, 
		                                            lifeforms,
		                                            environmentals );
	}
}
