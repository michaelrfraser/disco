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
package com.calytrix.disco.pdu.field;

import com.calytrix.disco.util.DISSizes;

/**
 * This field shall specify the domain in which the equipment operates (for example, subsurface,
 * surface, land, etc.) except for munition entities. For Munition entities this field shall
 * specify the domain of the target (for example the munition might be surface-to-air, so the
 * domain would be anti-air).<br/>
 * <br/>
 * NOTE: This enumeration will not be stored in the enumeration table, but rather is a part of the
 * Entity Type Database.
 * 
 */
public class Domain
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI8_SIZE;
	
	public static final short OTHER = 0;
	public static final short LAND = 1;
	public static final short AIR = 2;
	public static final short SURFACE = 3;
	public static final short SUBSURFACE = 4;
	public static final short SPACE = 5;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
	/**
	 * Returns the ordered set of values that this enumerated field can assume
	 * 
	 * @return An int[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static short[] getValues()
	{
		short[] values = { OTHER, LAND, AIR, SURFACE, SUBSURFACE, SPACE };
		return values;
	}
	
	/**
	 * Returns a textual description of the provided field value
	 * 
	 * @param value The field value to return a description of
	 * 
	 * @return A String representing the description of the specified field
	 * value
	 */
	public static String getDescription( short value )
	{
		String description = "Undefined";
		
		switch( value )
		{
			case OTHER:
				description = "Other";
				break;
				
			case LAND:
				description = "Land";
				break;
				
			case AIR:
				description = "Air";
				break;
				
			case SURFACE:
				description = "Surface";
				break;
				
			case SUBSURFACE:
				description = "Subsurface";
				break;
				
			case SPACE:
				description = "Space";
				break;
		}
		
		return description;
	}
}
