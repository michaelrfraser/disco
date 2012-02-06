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
import java.util.Arrays;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.IPDUComponent;
import com.calytrix.disco.pdu.field.DeadReckoningAlgorithm;

/**
 * Used to provide the parameters for dead reckoning the position and orientation of the entity.
 * Dead Reckoning Algorithm in use, Entity Acceleration and Angular velocity shall be included as
 * a part of the dead reckoning parameters. 120 bits are reserved for other parameters that are
 * currently undefined.
 */
public class DeadReckoningParameter implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	private static final int OTHER_PARAMETERS_ARRAY_SIZE = 15;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short deadReckoningAlgorithm;
	private byte[] deadReckoningOtherParameters;
	private VectorRecord entityLinearAcceleration;
	private AngularVelocityVector entityAngularVelocity;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public DeadReckoningParameter()
	{
		this( DeadReckoningAlgorithm.OTHER, 
		      new byte[OTHER_PARAMETERS_ARRAY_SIZE], 
		      new VectorRecord(),
		      new AngularVelocityVector() );
	}
	
	public DeadReckoningParameter( short deadReckoningAlgorithm,
	                               byte[] deadReckoningOtherParameters,
	                               VectorRecord entityLinearAcceleration,
	                               AngularVelocityVector entityAngularVelocity )
	{
		this.deadReckoningAlgorithm = deadReckoningAlgorithm;
		this.entityLinearAcceleration = entityLinearAcceleration;
		this.entityAngularVelocity = entityAngularVelocity;
		
		setDeadReckoningOtherParameters( deadReckoningOtherParameters );
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

		if( other instanceof DeadReckoningParameter )
		{
			DeadReckoningParameter asParam = (DeadReckoningParameter)other;
			if( asParam.deadReckoningAlgorithm == this.deadReckoningAlgorithm && 
				Arrays.equals(asParam.deadReckoningOtherParameters, this.deadReckoningOtherParameters) &&
			    asParam.entityLinearAcceleration.equals(this.entityLinearAcceleration) &&
			    asParam.entityAngularVelocity.equals(this.entityAngularVelocity) )
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
	public DeadReckoningParameter clone()
	{
		VectorRecord linearAccelerationClone = entityLinearAcceleration.clone();
		AngularVelocityVector angularVelocityClone = entityAngularVelocity.clone();
		byte[] otherParametersClone = Arrays.copyOf( deadReckoningOtherParameters, deadReckoningOtherParameters.length );
		
		return new DeadReckoningParameter( deadReckoningAlgorithm, 
		                                   otherParametersClone, 
		                                   linearAccelerationClone, 
		                                   angularVelocityClone );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		deadReckoningAlgorithm = dis.readUI8();
		dis.readFully( deadReckoningOtherParameters );
		entityLinearAcceleration.read( dis );
		entityAngularVelocity.read( dis );
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI8( deadReckoningAlgorithm );
		dos.write( deadReckoningOtherParameters );
		entityLinearAcceleration.write( dos );
		entityAngularVelocity.write( dos );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		int size = DeadReckoningAlgorithm.BYTE_LENGTH;
		size += OTHER_PARAMETERS_ARRAY_SIZE;
		size += entityLinearAcceleration.getByteLength();
		size += entityAngularVelocity.getByteLength();
		
		return size;
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

	public VectorRecord getEntityLinearAcceleration()
    {
    	return entityLinearAcceleration;
    }

	public void setEntityLinearAcceleration( VectorRecord entityLinearAcceleration )
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
    	return deadReckoningOtherParameters;
    }

	public void setDeadReckoningOtherParameters( byte[] deadReckoningOtherParameters )
    {
		if( deadReckoningOtherParameters.length != OTHER_PARAMETERS_ARRAY_SIZE )
			throw new IllegalArgumentException( "Dead Reckoning Other Parameters BLOB must be 120-bits in size" );

    	this.deadReckoningOtherParameters = deadReckoningOtherParameters;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
