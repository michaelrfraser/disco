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
 * Indicates the type of dead reckoning algorithm used by an entity.
 */
public class DeadReckoningAlgorithm
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int BYTE_LENGTH = DISSizes.UI8_SIZE;
	
	public static final short OTHER = 0;
	public static final short STATIC = 1;
	public static final short FPW = 2;
	public static final short RPW = 3;
	public static final short RVW = 4;
	public static final short FVW = 5;
	public static final short FPB = 6;
	public static final short RPB = 7;
	public static final short RVB = 8;
	public static final short FVB = 9;

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
		                   STATIC,
		                   FPW,
		                   RPW,
		                   RVW,
		                   FVW,
		                   FPB,
		                   RPB,
		                   RVB,
		                   FVB };
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
			case FVB:
				description = "FVB";
				break;
				
			case RVB:
				description = "RVB";
				break;

			case RPB:
				description = "RPB";
				break;

			case FPB:
				description = "FPB";
				break;

			case FVW:
				description = "FVW";
				break;

			case RVW:
				description = "RVW";
				break;

			case RPW:
				description = "RPW";
				break;

			case FPW:
				description = "FPW";
				break;

			case STATIC:
				description = "Static";
				break;

			case OTHER:
				description = "Other";
				break;
		}

		return description;
	}
}
