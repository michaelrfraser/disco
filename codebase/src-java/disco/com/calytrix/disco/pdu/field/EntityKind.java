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
 *  This field shall identify the Kind of Entity.
 */
public class EntityKind
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI8_SIZE;
	
	public static final short OTHER = 0;
	public static final short PLATFORM = 1;
	public static final short MUNITION = 2;
	public static final short LIFEFORM = 3;
	public static final short ENVIRONMENTAL = 4;
	public static final short CULTURAL_FEATURE = 5;
	public static final short SUPPLY = 6;
	public static final short RADIO = 7;
	public static final short EXPENDABLE = 8;
	public static final short SENSOR_EMITTER = 9;

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
	 * @return An short[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static short[] getValues()
	{
		short[] values = { OTHER,
		                 PLATFORM,
		                 MUNITION,
		                 LIFEFORM,
		                 ENVIRONMENTAL,
		                 CULTURAL_FEATURE, 
		                 SUPPLY,
		                 RADIO,
		                 EXPENDABLE,
		                 SENSOR_EMITTER };
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
				
			case PLATFORM:
				description = "Platform";
				break;
				
			case MUNITION:
				description = "Munition";
				break;
				
			case LIFEFORM:
				description = "Lifeform";
				break;
				
			case ENVIRONMENTAL:
				description = "Environmental";
				break;
				
			case CULTURAL_FEATURE:
				description = "Cultural Feature";
				break;
				
			case SUPPLY:
				description = "Supply";
				break;
				
			case RADIO:
				description = "Radio";
				break;
				
			case EXPENDABLE:
				description = "Expendable";
				break;
				
			case SENSOR_EMITTER:
				description = "Sensor/Emitter";
				break;
		}
		
		return description;
	}
}
