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
 * Orientation of a simulated entity shall be specified by the Euler Angles Record. This record
 * shall specify three angles which are specified with respect to the entities coordinate system.
 * The three angles shall be represented in radians.
 */
public class EulerAngles
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
		boolean equal = false;
		
		if ( other == this )
		{
			equal = true;
		}
		else
		{
			if ( other instanceof EulerAngles )
			{
				EulerAngles asEulerAngles = (EulerAngles)other;
				equal = FloatingPointUtils.floatEqual( asEulerAngles.psi, this.psi )
					&& FloatingPointUtils.floatEqual( asEulerAngles.theta, this.theta )
					&& FloatingPointUtils.floatEqual( asEulerAngles.phi, this.phi );
			}
		}
		
		return equal;
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
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EulerAngles deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EulerAngles read( DISInputStream dis ) throws IOException
	{
		float psi = dis.readFloat();
		float theta = dis.readFloat();
		float phi = dis.readFloat();
		
		return new EulerAngles( psi, theta, phi );
	}
}
