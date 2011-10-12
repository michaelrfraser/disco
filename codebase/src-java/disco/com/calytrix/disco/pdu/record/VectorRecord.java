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
import com.calytrix.disco.util.FloatingPointUtils;

/**
 * 
 * 
 */
public class VectorRecord
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
			if( FloatingPointUtils.floatEqual(otherVector.firstVectorComponent,this.firstVectorComponent) &&
			    FloatingPointUtils.floatEqual(otherVector.secondVectorComponent,this.secondVectorComponent) &&
			    FloatingPointUtils.floatEqual(otherVector.thirdVectorComponent,this.thirdVectorComponent) )
			{
				return true;
			}
		}
		
		return false;
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
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The Vector deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
    public static VectorRecord read( DISInputStream dis ) throws IOException
	{
		float firstVectorComponent = dis.readFloat();
		float secondVectorComponent = dis.readFloat();
		float thirdVectorComponent = dis.readFloat();
		
		return new VectorRecord( firstVectorComponent, secondVectorComponent, thirdVectorComponent );
	}
}
