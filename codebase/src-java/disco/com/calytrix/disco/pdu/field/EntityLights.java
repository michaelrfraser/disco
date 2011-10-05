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
package com.calytrix.disco.pdu.field;

/**
 * Describes the status of lights found on various platforms.
 */
public class EntityLights
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int NONE = 0;
	public static final int RUNNING_LIGHTS_ARE_ON = 1;
	public static final int NAVIGATION_LIGHTS_ARE_ON = 2;
	public static final int FROMATION_LIGHTS_ARE_ON = 3;
	//public static final int UNUSED = 4;
	//public static final int UNUSED = 5;
	//public static final int UNUSED = 6;
	//public static final int UNUSED = 7;

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
		int[] values =
		    { NONE, RUNNING_LIGHTS_ARE_ON, NAVIGATION_LIGHTS_ARE_ON, FROMATION_LIGHTS_ARE_ON };
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
		String description = "Fromation lights are on";
		switch( value )
		{
			case NAVIGATION_LIGHTS_ARE_ON:
				description = "Navigation lights are on";
				break;

			case RUNNING_LIGHTS_ARE_ON:
				description = "Running lights are on";
				break;

			case NONE:
				description = "None";
				break;
		}

		return description;
	}

}
