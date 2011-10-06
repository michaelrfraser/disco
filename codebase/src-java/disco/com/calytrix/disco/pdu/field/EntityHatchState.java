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
 * Describes the state of the hatch.
 */
public class EntityHatchState
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int NOT_APPLICABLE = 0;
	public static final int PRIMARY_HATCH_IS_CLOSED = 1;
	public static final int PRIMARY_HATCH_IS_POPPED = 2;
	public static final int PRIMARY_HATCH_IS_POPPED_AND_A_PERSON_IS_VISIBLE_UNDER_HATCH = 3;
	public static final int PRIMARY_HATCH_IS_OPEN = 4;
	public static final int PRIMARY_HATCH_IS_OPEN_AND_PERSON_IS_VISIBLE = 5;
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
		    { NOT_APPLICABLE, PRIMARY_HATCH_IS_CLOSED, PRIMARY_HATCH_IS_POPPED,
		     PRIMARY_HATCH_IS_POPPED_AND_A_PERSON_IS_VISIBLE_UNDER_HATCH, PRIMARY_HATCH_IS_OPEN,
		     PRIMARY_HATCH_IS_OPEN_AND_PERSON_IS_VISIBLE };
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
		String description = "";
		switch( value )
		{
			case PRIMARY_HATCH_IS_OPEN_AND_PERSON_IS_VISIBLE:
				description = "Primary hatch is open and person is visible";
				break;
				
			case PRIMARY_HATCH_IS_OPEN:
				description = "Primary hatch is open";
				break;

			case PRIMARY_HATCH_IS_POPPED_AND_A_PERSON_IS_VISIBLE_UNDER_HATCH:
				description = "Primary hatch is popped and a person is visible under hatch";
				break;

			case PRIMARY_HATCH_IS_POPPED:
				description = "Primary hatch is popped";
				break;

			case PRIMARY_HATCH_IS_CLOSED:
				description = "Primary hatch is closed";
				break;

			case NOT_APPLICABLE:
				description = "Not applicable";
				break;
		}

		return description;
	}
}
