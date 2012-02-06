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
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.IPDUComponent;
import com.calytrix.disco.pdu.field.ParameterTypeDesignator;
import com.calytrix.disco.util.DISSizes;
import com.calytrix.disco.util.DISUnsignedInt64;

/**
 * The specification of articulation parameters for movable parts and attached parts of an entity
 * shall be represented by an Articulation Parameter Record. This record shall specify whether or
 * not a change has occurred, the Part ID of the articulated part to which it is attached, and the
 * type and value of each parameter.
 */
public class ArticulationParameter implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short parameterTypeDesignator;
	private short parameterChangeIndicator;
	private int articulationAttachmentID;
	private ParameterType parameterTypeVariant;
	private DISUnsignedInt64 articulationParameterValue;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public ArticulationParameter()
	{
		this( ParameterTypeDesignator.ARTICULATED_PART, 
		      (short)0, 
		      0, 
		      new ParameterType(),
		      new DISUnsignedInt64() );
	}
	
	public ArticulationParameter( short parameterTypeDesignator,
	                              short parameterChangeIndicator,
	                              int articulationAttachmentID,
	                              ParameterType parameterTypeVariant,
	                              DISUnsignedInt64 articulationParameterValue )
	{
		this.parameterTypeDesignator = parameterTypeDesignator;
		this.parameterChangeIndicator = parameterChangeIndicator;
		this.articulationAttachmentID = articulationAttachmentID;
		this.parameterTypeVariant = parameterTypeVariant;
		this.articulationParameterValue = articulationParameterValue;
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

		if( other instanceof ArticulationParameter )
		{
			ArticulationParameter otherParam = (ArticulationParameter)other;
			if( otherParam.parameterTypeDesignator == this.parameterTypeDesignator && 
			    otherParam.parameterChangeIndicator == this.parameterChangeIndicator &&
			    otherParam.articulationAttachmentID == this.articulationAttachmentID &&
			    otherParam.parameterTypeVariant.equals(this.parameterTypeVariant) &&
			    otherParam.articulationParameterValue.equals(this.articulationParameterValue) )
			{
				return true;
			}
		}
		
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArticulationParameter clone()
	{
		ParameterType parameterTypeClone = parameterTypeVariant.clone();
		DISUnsignedInt64 parameterValueClone = articulationParameterValue.clone();
		
		return new ArticulationParameter( parameterTypeDesignator, 
		                                  parameterChangeIndicator, 
		                                  articulationAttachmentID, 
		                                  parameterTypeClone, 
		                                  parameterValueClone );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		parameterTypeDesignator = dis.readUI8();
		parameterChangeIndicator = dis.readUI8();
		articulationAttachmentID = dis.readUI16();
		parameterTypeVariant.read( dis );
		articulationParameterValue.read( dis );
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI8( parameterTypeDesignator );
		dos.writeUI8( parameterChangeIndicator );
		dos.writeUI16( articulationAttachmentID );
		parameterTypeVariant.write( dos );
		articulationParameterValue.write( dos );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = DISSizes.UI8_SIZE * 2;
		size += DISSizes.UI16_SIZE;
		size += parameterTypeVariant.getByteLength();
		size += articulationParameterValue.getByteLength();
		
		return size;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public short getParameterTypeDesignator()
    {
    	return parameterTypeDesignator;
    }

	public void setParameterTypeDesignator( short parameterTypeDesignator )
    {
    	this.parameterTypeDesignator = parameterTypeDesignator;
    }

	public short getParameterChangeIndicator()
    {
    	return parameterChangeIndicator;
    }

	public void setParameterChangeIndicator( short parameterChangeIndicator )
    {
    	this.parameterChangeIndicator = parameterChangeIndicator;
    }

	public int getArticulationAttachmentID()
    {
    	return articulationAttachmentID;
    }

	public void setArticulationAttachmentID( int articulationAttachmentID )
    {
    	this.articulationAttachmentID = articulationAttachmentID;
    }

	public ParameterType getParameterTypeVariant()
    {
    	return parameterTypeVariant;
    }

	public void setParameterTypeVariant( ParameterType parameterTypeVariant )
    {
    	this.parameterTypeVariant = parameterTypeVariant;
    }

	public DISUnsignedInt64 getArticulationParameterValue()
    {
    	return articulationParameterValue;
    }

	public void setArticulationParameterValue( DISUnsignedInt64 articulationParameterValue )
    {
    	this.articulationParameterValue = articulationParameterValue;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
