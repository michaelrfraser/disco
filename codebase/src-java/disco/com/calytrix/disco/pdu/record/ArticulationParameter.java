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
import java.math.BigInteger;

import com.calytrix.disco.network.DISInputStream;

/**
 * The specification of articulation parameters for movable parts and attached parts of an entity
 * shall be represented by an Articulation Parameter Record. This record shall specify whether or
 * not a change has occurred, the Part ID of the articulated part to which it is attached, and the
 * type and value of each parameter.
 */
public class ArticulationParameter
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
	private BigInteger articulationParamterValue;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public ArticulationParameter( short parameterTypeDesignator, short parameterChangeIndicator,
	                              int articulationAttachmentID, ParameterType parameterTypeVariant,
	                              BigInteger articulationParamterValue )
	{
		this.parameterTypeDesignator = parameterTypeDesignator;
		this.parameterChangeIndicator = parameterChangeIndicator;
		this.articulationAttachmentID = articulationAttachmentID;
		this.parameterTypeVariant = parameterTypeVariant;
		this.articulationParamterValue = articulationParamterValue;
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
			if ( other instanceof ArticulationParameter )
			{
				ArticulationParameter asArticulationParameter = (ArticulationParameter)other;
				equal = asArticulationParameter.parameterTypeDesignator == this.parameterTypeDesignator 
					&& asArticulationParameter.parameterChangeIndicator == this.parameterChangeIndicator
					&& asArticulationParameter.articulationAttachmentID == this.articulationAttachmentID
					&& asArticulationParameter.parameterTypeVariant.equals( this.parameterTypeVariant )
					&& asArticulationParameter.articulationParamterValue.equals( this.articulationParamterValue );
			}
		}
		
		return equal;
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

	public BigInteger getArticulationParamterValue()
    {
    	return articulationParamterValue;
    }

	public void setArticulationParamterValue( BigInteger articulationParamterValue )
    {
    	this.articulationParamterValue = articulationParamterValue;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The ArticulationParameter deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static ArticulationParameter read( DISInputStream dis ) throws IOException
	{
		
		short parameterTypeDesignator = dis.readUI8();
		short parameterChangeIndicator = dis.readUI8();
		int articulationAttachmentID = dis.readUI16();
		ParameterType parameterTypeVariant = ParameterType.read( dis );
		BigInteger articulationParamterValue = dis.readUI64();
		
		return new ArticulationParameter( parameterTypeDesignator, parameterChangeIndicator, 
		                                  articulationAttachmentID, parameterTypeVariant,
		                                  articulationParamterValue );
	}
}
