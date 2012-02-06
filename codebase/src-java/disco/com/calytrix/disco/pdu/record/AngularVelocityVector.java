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
 * The angular velocity of simulated entities shall be represented by the Angular Velocity Vector
 * Record. This record shall specify the rate at which an entity's orientation is changing. This
 * rate shall be measured in radians per second measured about each of the entity's own coordinate
 * axes. The record shall consist of three fields. The first field shall represent velocity about
 * the x-axis, the second about the y-axis, and the third about the z-axis (see 5.3.32.1). The
 * positive direction of the angular velocity is defined by the right-hand rule.
 */
public class AngularVelocityVector implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private long rateAboutXAxis;
	private long rateAboutYAxis;
	private long rateAboutZAxis;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public AngularVelocityVector()
	{
		this( 0, 0, 0 );
	}
	
	public AngularVelocityVector( long rateAboutXAxis, long rateAboutYAxis, long rateAboutZAxis )
	{
		this.rateAboutXAxis = rateAboutXAxis;
		this.rateAboutYAxis = rateAboutYAxis;
		this.rateAboutZAxis = rateAboutZAxis;
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
		
		if( other == this )
		{
			equal = true;
		}
		else
		{
			if( other instanceof AngularVelocityVector )
			{
				AngularVelocityVector asAngularVelocityVector = (AngularVelocityVector)other;
				equal = asAngularVelocityVector.rateAboutXAxis == this.rateAboutXAxis 
					&& asAngularVelocityVector.rateAboutYAxis == this.rateAboutYAxis
					&& asAngularVelocityVector.rateAboutZAxis == this.rateAboutZAxis;
			}
		}
		
		return equal;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override 
	public AngularVelocityVector clone()
	{
		return new AngularVelocityVector( rateAboutXAxis, rateAboutYAxis, rateAboutZAxis );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		rateAboutXAxis = dis.readUI32();
		rateAboutYAxis = dis.readUI32();
		rateAboutZAxis = dis.readUI32();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI32( rateAboutXAxis );
		dos.writeUI32( rateAboutYAxis );
		dos.writeUI32( rateAboutZAxis );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return DISSizes.UI32_SIZE * 3;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
    public long getRateAboutXAxis()
    {
    	return rateAboutXAxis;
    }

    public void setRateAboutXAxis( long rateAboutXAxis )
    {
    	this.rateAboutXAxis = rateAboutXAxis;
    }

    public long getRateAboutYAxis()
    {
    	return rateAboutYAxis;
    }

    public void setRateAboutYAxis( long rateAboutYAxis )
    {
    	this.rateAboutYAxis = rateAboutYAxis;
    }

    public long getRateAboutZAxis()
    {
    	return rateAboutZAxis;
    }

    public void setRateAboutZAxis( long rateAboutZAxis )
    {
    	this.rateAboutZAxis = rateAboutZAxis;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
