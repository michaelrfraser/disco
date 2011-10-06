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
 * Indicates the type of dead reckoning algorithm used by an entity.
 */
public class DeadReckoningAlgorithm
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int OTHER = 0;
	public static final int STATIC = 1;
	public static final int DRM_F_P_W = 2;
	public static final int DRM_R_P_W = 3;
	public static final int DRM_R_V_W = 4;
	public static final int DRM_F_V_W = 5;
	public static final int DRM_F_P_B = 6;
	public static final int DRM_R_P_B = 7;
	public static final int DRM_R_V_B = 8;
	public static final int DRM_F_V_B = 9;

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
		    { OTHER, STATIC, DRM_F_P_W, DRM_R_P_W, DRM_R_V_W, DRM_F_V_W, DRM_F_P_B, DRM_R_P_B,
		     DRM_R_V_B, DRM_F_V_B };
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
			case DRM_F_V_B:
				description = "DRM_F_V_B";
				break;
				
			case DRM_R_V_B:
				description = "DRM(R, V, B)";
				break;

			case DRM_R_P_B:
				description = "DRM(R, P, B)";
				break;

			case DRM_F_P_B:
				description = "DRM(F, P, B)";
				break;

			case DRM_F_V_W:
				description = "DRM(F, V, W)";
				break;

			case DRM_R_V_W:
				description = "DRM(R, V, W)";
				break;

			case DRM_R_P_W:
				description = "DRM(R, P, W)";
				break;

			case DRM_F_P_W:
				description = "DRM(F, P, W)";
				break;

			case STATIC:
				description = "Static (Entity does not move.)";
				break;

			case OTHER:
				description = "Other";
				break;
		}

		return description;
	}
}
