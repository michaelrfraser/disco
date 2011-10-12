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
 * This is a 11 byte string of characters used to designate the Entity Marking.
 */
public class EntityMarkingString
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private short firstCharacter;
	private short secondCharacter;
	private short thirdCharacter;
	private short fourthCharacter;
	private short fifthCharacter;
	private short sixthCharacter;
	private short seventhCharacter;
	private short eighthCharacter;
	private short ninthCharacter;
	private short tenthCharacter;
	private short eleventhCharacter;

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public EntityMarkingString( short firstCharacter,
	                            short secondCharacter,
	                            short thirdCharacter,
	                            short fourthCharacter,
	                            short fifthCharacter,
	                            short sixthCharacter,
	                            short seventhCharacter,
	                            short eighthCharacter,
	                            short ninthCharacter,
	                            short tenthCharacter,
	                            short eleventhChracter )
	{
		this.firstCharacter = firstCharacter;
		this.secondCharacter = secondCharacter;
		this.thirdCharacter = thirdCharacter;
		this.fourthCharacter = fourthCharacter;
		this.fifthCharacter = fifthCharacter;
		this.sixthCharacter = sixthCharacter;
		this.seventhCharacter = seventhCharacter;
		this.eighthCharacter = eighthCharacter;
		this.ninthCharacter = ninthCharacter;
		this.tenthCharacter = tenthCharacter;
		this.eleventhCharacter = eleventhChracter;
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
		
		if( other instanceof EntityMarkingString )
		{
			EntityMarkingString otherString = (EntityMarkingString)other;
			if( otherString.firstCharacter == this.firstCharacter &&
				otherString.secondCharacter == this.secondCharacter &&
				otherString.thirdCharacter == this.thirdCharacter &&
				otherString.fourthCharacter == this.fourthCharacter &&
				otherString.fifthCharacter == this.fifthCharacter &&
				otherString.sixthCharacter == this.sixthCharacter &&
				otherString.seventhCharacter == this.seventhCharacter &&
				otherString.eighthCharacter == this.eighthCharacter &&
				otherString.ninthCharacter == this.ninthCharacter &&
				otherString.tenthCharacter == this.tenthCharacter &&
				otherString.eleventhCharacter == this.eleventhCharacter )
			{
				return true;
			}
		}
		
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
    public short getFirstCharacter()
    {
    	return firstCharacter;
    }

    public void setFirstCharacter( short firstCharacter )
    {
    	this.firstCharacter = firstCharacter;
    }

    public short getSecondCharacter()
    {
    	return secondCharacter;
    }

    public void setSecondCharacter( short secondCharacter )
    {
    	this.secondCharacter = secondCharacter;
    }

    public short getThirdCharacter()
    {
    	return thirdCharacter;
    }

    public void setThirdCharacter( short thirdCharacter )
    {
    	this.thirdCharacter = thirdCharacter;
    }

    public short getFourthCharacter()
    {
    	return fourthCharacter;
    }

    public void setFourthCharacter( short fourthCharacter )
    {
    	this.fourthCharacter = fourthCharacter;
    }

    public short getFifthCharacter()
    {
    	return fifthCharacter;
    }

    public void setFifthCharacter( short fifthCharacter )
    {
    	this.fifthCharacter = fifthCharacter;
    }

    public short getSixthCharacter()
    {
    	return sixthCharacter;
    }

    public void setSixthCharacter( short sixthCharacter )
    {
    	this.sixthCharacter = sixthCharacter;
    }

    public short getSeventhCharacter()
    {
    	return seventhCharacter;
    }

    public void setSeventhCharacter( short seventhCharacter )
    {
    	this.seventhCharacter = seventhCharacter;
    }

    public short getEighthCharacter()
    {
    	return eighthCharacter;
    }

    public void setEighthCharacter( short eighthCharacter )
    {
    	this.eighthCharacter = eighthCharacter;
    }

    public short getNinthCharacter()
    {
    	return ninthCharacter;
    }

    public void setNinthCharacter( short ninthCharacter )
    {
    	this.ninthCharacter = ninthCharacter;
    }

    public short getTenthCharacter()
    {
    	return tenthCharacter;
    }

    public void setTenthCharacter( short tenthCharacter )
    {
    	this.tenthCharacter = tenthCharacter;
    }

    public short getEleventhCharacter()
    {
    	return eleventhCharacter;
    }

    public void setEleventhCharacter( short eleventhCharacter )
    {
    	this.eleventhCharacter = eleventhCharacter;
    }

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Reads an instance of this record from the provided DISInputStream.
	 * 
	 * @param dis The DISInputStream to read the record from.
	 * 
	 * @return The EntityMarkingString deserialised from the provided input stream.
	 * 
	 * @throws IOException Thrown if an error occurred reading the record from
	 * the stream.
	 */
	public static EntityMarkingString read( DISInputStream dis ) throws IOException
	{
		short firstCharacter = dis.readUI8();
		short secondCharacter = dis.readUI8();
		short thirdCharacter = dis.readUI8();
		short fourthCharacter = dis.readUI8();
		short fifthCharacter = dis.readUI8();
		short sixthCharacter = dis.readUI8();
		short seventhCharacter = dis.readUI8();
		short eighthCharacter = dis.readUI8();
		short ninthCharacter = dis.readUI8();
		short tenthCharacter = dis.readUI8();
		short eleventhCharacter = dis.readUI8();
		
		return new EntityMarkingString( firstCharacter,
		                                secondCharacter,
		                                thirdCharacter,
		                                fourthCharacter,
		                                fifthCharacter,
		                                sixthCharacter,
		                                seventhCharacter,
		                                eighthCharacter,
		                                ninthCharacter,
		                                tenthCharacter,
		                                eleventhCharacter );
	}
}
