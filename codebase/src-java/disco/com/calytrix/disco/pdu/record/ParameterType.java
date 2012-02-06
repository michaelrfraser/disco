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
import com.calytrix.disco.util.DISSizes;

/**
 * Indicates the type of Parameter used in an Articulation Parameter Record. This Variant is
 * represented as either an Attached part or Articulated part depending upon the Parameter Type
 * Designator.
 */
public class ParameterType implements IPDUComponent, Cloneable
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
	public ParameterType()
	{
		this( 0, new ParameterTypeArticulatedParts() );
	}
	
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
		if( this == other )
			return true;
		
		if( other instanceof ParameterType )
		{
			ParameterType otherParameter = (ParameterType)other;
			if( otherParameter.attachedParts == this.attachedParts &&
				otherParameter.articulatedParts.equals(this.articulatedParts) )
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
	public ParameterType clone()
	{
		ParameterTypeArticulatedParts clonedArticulatedParts = articulatedParts.clone();
		return new ParameterType( attachedParts, clonedArticulatedParts );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		attachedParts = dis.readUI32();
		articulatedParts.read( dis );
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI32( attachedParts );
		articulatedParts.write( dos );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = DISSizes.UI32_SIZE;
		size += articulatedParts.getByteLength();
		
		return size;
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
}
