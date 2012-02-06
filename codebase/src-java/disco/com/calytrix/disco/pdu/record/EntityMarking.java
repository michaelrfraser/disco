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
import com.calytrix.disco.pdu.field.EntityMarkingCharacterSet;

/**
 * This record shall be used to specify the character set used in the marking and the string of
 * characters to be interpreted for display. The character set shall be represented by an 8-bit
 * enumeration. The string of characters shall be represented by an 11 element character string.
 */
public class EntityMarking implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	private static final int MARKING_CHARACTERS = 11;
	
	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short markingCharset;
	private String markingString;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityMarking()
	{
		this( EntityMarkingCharacterSet.ASCII, "" );
	}
	
	public EntityMarking( short markingCharset, String markingString )
	{
		this.markingCharset = markingCharset;
		this.markingString = markingString;
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
		
		if( other instanceof EntityMarking )
		{
			EntityMarking otherMarking = (EntityMarking)other;
			if( otherMarking.markingCharset == this.markingCharset &&
			    otherMarking.markingString.equals(this.markingString) )
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
	public EntityMarking clone()
	{
		String markingStringClone = new String( markingString );
		return new EntityMarking( markingCharset, markingStringClone );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		markingCharset = dis.readUI8();
		String asString = dis.readString( MARKING_CHARACTERS );
		setEntityMarkingString( asString );
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
	    dos.writeUI8( markingCharset );
	    
	    // TODO FIX This will only work for ASCII, we need something to convert between other
	    // formats
	    byte[] asBytes = markingString.getBytes();
	    byte[] finalBytes = Arrays.copyOf( asBytes, MARKING_CHARACTERS );

	    dos.write( finalBytes );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return EntityMarkingCharacterSet.BIT_LENGTH + MARKING_CHARACTERS;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public short getEntityMarkingCharacterSet()
    {
    	return markingCharset;
    }

	public void setEntityMarkingCharacterSet( short entityMarkingCharacterSet )
    {
    	this.markingCharset = entityMarkingCharacterSet;
    }

	public String getEntityMarkingString()
    {
    	return markingString;
    }

	public void setEntityMarkingString( String entityMarkingString )
    {
		if ( entityMarkingString.length() > MARKING_CHARACTERS )
			throw new IllegalArgumentException( "Entity Marking String can only be " + MARKING_CHARACTERS + " characters or less" );
		
    	this.markingString = entityMarkingString;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
