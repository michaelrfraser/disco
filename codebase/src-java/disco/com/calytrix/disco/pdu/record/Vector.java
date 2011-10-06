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
import com.calytrix.disco.util.FloatingPointUtils;

/**
 * 
 * 
 */
public class Vector
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
	public Vector( float firstVectorComponent, float secondVectorComponent,
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
		boolean equal = false;
		
		if ( other == this )
		{
			equal = true;
		}
		else
		{
			if ( other instanceof Vector )
			{
				Vector asVector = (Vector)other;
				equal = FloatingPointUtils.floatEqual( asVector.firstVectorComponent, this.firstVectorComponent )
					&& FloatingPointUtils.floatEqual( asVector.secondVectorComponent, this.secondVectorComponent )
					&& FloatingPointUtils.floatEqual( asVector.thirdVectorComponent, this.thirdVectorComponent );
			}
		}
		
		return equal;
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
    public static Vector read( DISInputStream dis ) throws IOException
	{
		float firstVectorComponent = dis.readFloat();
		float SecondVectorComponent = dis.readFloat();
		float ThirdVectorComponent = dis.readFloat();
		
		return new Vector( firstVectorComponent, SecondVectorComponent, ThirdVectorComponent );
	}
}
