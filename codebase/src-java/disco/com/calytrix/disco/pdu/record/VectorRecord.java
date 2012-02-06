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
import com.calytrix.disco.util.FloatingPointUtils;

/**
 * An abstract Vector record with 3 dimensions
 */
public class VectorRecord implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private float firstVectorComponent;
	private float secondVectorComponent;
	private float thirdVectorComponent;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public VectorRecord()
	{
		this( 0f, 0f, 0f );
	}
	
	public VectorRecord( float firstVectorComponent,
	                     float secondVectorComponent,
	                     float thirdVectorComponent )
	{
		this.firstVectorComponent = firstVectorComponent;
		this.secondVectorComponent = secondVectorComponent;
		this.thirdVectorComponent = thirdVectorComponent;
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
		
		if( other instanceof VectorRecord )
		{
			VectorRecord otherVector = (VectorRecord)other;
			if( FloatingPointUtils.floatEqual(otherVector.firstVectorComponent, this.firstVectorComponent) &&
			    FloatingPointUtils.floatEqual(otherVector.secondVectorComponent, this.secondVectorComponent) &&
			    FloatingPointUtils.floatEqual(otherVector.thirdVectorComponent, this.thirdVectorComponent) )
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
	public VectorRecord clone()
	{
		return new VectorRecord( firstVectorComponent, secondVectorComponent, thirdVectorComponent );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		firstVectorComponent = dis.readFloat();
		secondVectorComponent = dis.readFloat();
		thirdVectorComponent = dis.readFloat();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeFloat( firstVectorComponent );
		dos.writeFloat( secondVectorComponent );
		dos.writeFloat( thirdVectorComponent );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return DISSizes.FLOAT32_SIZE * 3;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
    public float getFirstVectorComponent()
    {
    	return firstVectorComponent;
    }

    public void setFirstVectorComponent( float firstVectorComponent )
    {
    	this.firstVectorComponent = firstVectorComponent;
    }

    public float getSecondVectorComponent()
    {
    	return secondVectorComponent;
    }

    public void setSecondVectorComponent( float secondVectorComponent )
    {
    	this.secondVectorComponent = secondVectorComponent;
    }

    public float getThirdVectorComponent()
    {
    	return thirdVectorComponent;
    }

    public void setThirdVectorComponent( float thirdVectorComponent )
    {
    	this.thirdVectorComponent = thirdVectorComponent;
    }	

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
