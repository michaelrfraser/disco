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
 * Orientation of a simulated entity shall be specified by the Euler Angles Record. This record
 * shall specify three angles which are specified with respect to the entities coordinate system.
 * The three angles shall be represented in radians.
 */
public class EulerAngles implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private float psi;
	private float theta;
	private float phi;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EulerAngles()
	{
		this( 0f, 0f, 0f );
	}
	
	public EulerAngles( float psi, float theta, float phi )
	{
		this.psi = psi;
		this.theta = theta;
		this.phi = phi;
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

		if( other instanceof EulerAngles )
		{
			EulerAngles otherAngle = (EulerAngles)other;
			if( FloatingPointUtils.floatEqual(otherAngle.psi,this.psi) &&
				FloatingPointUtils.floatEqual(otherAngle.theta,this.theta) &&
				FloatingPointUtils.floatEqual(otherAngle.phi,this.phi) )
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
	public EulerAngles clone()
	{
		return new EulerAngles( psi, theta, phi );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		psi = dis.readFloat();
		theta = dis.readFloat();
		phi = dis.readFloat();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeFloat( psi );
		dos.writeFloat( theta );
		dos.writeFloat( phi );
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
	public float getPsi()
    {
    	return psi;
    }

	public void setPsi( float psi )
    {
    	this.psi = psi;
    }

	public float getTheta()
    {
    	return theta;
    }

	public void setTheta( float theta )
    {
    	this.theta = theta;
    }

	public float getPhi()
    {
    	return phi;
    }

	public void setPhi( float phi )
    {
    	this.phi = phi;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
