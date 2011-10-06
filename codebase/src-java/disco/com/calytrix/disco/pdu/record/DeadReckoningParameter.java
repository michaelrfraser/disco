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
 * Used to provide the parameters for dead reckoning the position and orientation of the entity.
 * Dead Reckoning Algorithm in use, Entity Acceleration and Angular velocity shall be included as
 * a part of the dead reckoning parameters. 120 bits are reserved for other parameters that are
 * currently undefined.
 */
public class DeadReckoningParameter
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	private static final int OTHER_PARAMETERS_ARRAY_SIZE = 15;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short deadReckoningAlgorithm;
	private byte[] deadReckoningOtherParamaters;
	private Vector entityLinearAcceleration;
	private AngularVelocityVector entityAngularVelocity;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public DeadReckoningParameter( short deadReckoningAlgorithm,
	                               byte[] deadReckoningOtherParamaters,
	                               Vector entityLinearAcceleration,
	                               AngularVelocityVector entityAngularVelocity)
	{
		this.deadReckoningAlgorithm = deadReckoningAlgorithm;
		setDeadReckoningOtherParamaters(deadReckoningOtherParamaters);
		this.entityLinearAcceleration = entityLinearAcceleration;
		this.entityAngularVelocity = entityAngularVelocity;
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
			if ( other instanceof DeadReckoningParameter )
			{
				DeadReckoningParameter asDeadReckoningParameter = (DeadReckoningParameter)other;
				equal = asDeadReckoningParameter.deadReckoningAlgorithm == this.deadReckoningAlgorithm 
					&& asDeadReckoningParameter.deadReckoningOtherParamaters == this.deadReckoningOtherParamaters
					&& asDeadReckoningParameter.entityLinearAcceleration.equals( this.entityLinearAcceleration )
					&& asDeadReckoningParameter.entityAngularVelocity.equals( this.entityAngularVelocity );
			}
		}
		
		return equal;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public short getDeadReckoningAlgorithm()
    {
    	return deadReckoningAlgorithm;
    }

	public void setDeadReckoningAlgorithm( short deadReckoningAlgorithm )
    {
    	this.deadReckoningAlgorithm = deadReckoningAlgorithm;
    }

	public Vector getEntityLinearAcceleration()
    {
    	return entityLinearAcceleration;
    }

	public void setEntityLinearAcceleration( Vector entityLinearAcceleration )
    {
    	this.entityLinearAcceleration = entityLinearAcceleration;
    }

	public AngularVelocityVector getEntityAngularVelocity()
    {
    	return entityAngularVelocity;
    }

	public void setEntityAngularVelocity( AngularVelocityVector entityAngularVelocity )
    {
    	this.entityAngularVelocity = entityAngularVelocity;
    }
	
	public byte[] getDeadReckoningOtherParamaters()
    {
    	return deadReckoningOtherParamaters;
    }

	public void setDeadReckoningOtherParamaters( byte[] deadReckoningOtherParamaters )
    {
		if ( deadReckoningOtherParamaters.length != OTHER_PARAMETERS_ARRAY_SIZE )
			throw new IllegalStateException( "Dead Reckoning Other Parameters BLOB must be aligned to 120bit boundary" );
		
    	this.deadReckoningOtherParamaters = deadReckoningOtherParamaters;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The DeadReckoningParameter deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static DeadReckoningParameter read( DISInputStream dis ) throws IOException
	{
		short deadReckoningAlgorithm = dis.readUI8();
		byte[] deadReckoningOtherParamaters = new byte [OTHER_PARAMETERS_ARRAY_SIZE];
		dis.readFully( deadReckoningOtherParamaters );
		Vector entityLinearAcceleration = Vector.read( dis );
		AngularVelocityVector entityAngularVelocity = AngularVelocityVector.read( dis );
		
		return new DeadReckoningParameter( deadReckoningAlgorithm, deadReckoningOtherParamaters, entityLinearAcceleration, entityAngularVelocity );
	}
}
