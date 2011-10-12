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

/**
 *  This field shall identify the Kind of Entity.
 */
public class EntityKind
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	private static final int OTHER = 0;
	private static final int PLATFORM = 1;
	private static final int MUNITION = 2;
	private static final int LIFEFORM = 3;
	private static final int ENVIRONMENTAL = 4;
	private static final int CULTURAL_FEATURE = 5;
	private static final int SUPPLY = 6;
	private static final int RADIO = 7;
	private static final int EXPENDABLE = 8;
	private static final int SENSOR_EMITTER = 9;

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
	public static int[] getValues()
	{
		int[] values = { OTHER,
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
	public static String getDescription( int value )
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
