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
 * Indicates the type of Parameter used in an Articulation Parameter Record. This Variant is
 * represented as either an Attached part or Articulated part depending upon the Parameter Type
 * Designator.
 */
public class ParameterType
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private long attachedParts;
	private ParameterTypeArticulatedParts articulatedParts;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public ParameterType( long attachedParts, ParameterTypeArticulatedParts articulatedParts )
	{
		this.attachedParts = attachedParts;
		this.articulatedParts = articulatedParts;
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
			if ( other instanceof ParameterType )
			{
				ParameterType asParameterType = (ParameterType)other;
				equal = asParameterType.attachedParts == this.attachedParts
					&& asParameterType.articulatedParts == this.articulatedParts;
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public long getAttachedParts()
    {
    	return attachedParts;
    }

	public void setAttachedParts( long attachedParts )
    {
    	this.attachedParts = attachedParts;
    }

	public ParameterTypeArticulatedParts getArticulatedParts()
    {
    	return articulatedParts;
    }

	public void setArticulatedParts( ParameterTypeArticulatedParts articulatedParts )
    {
    	this.articulatedParts = articulatedParts;
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
	public static ParameterType read( DISInputStream dis ) throws IOException
	{
		long attachedParts = dis.readUI32();
		ParameterTypeArticulatedParts articulatedParts = ParameterTypeArticulatedParts.read( dis );
		
		return new ParameterType( attachedParts, articulatedParts );
	}
}
