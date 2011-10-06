/*
 *   Copyright 2011 Calytrix Technologies
 *
 *   This file is part of Calytrix Disco.
 *
 *   Calytrix Disco is free software; you can redistribute it and/or modify
 *   it under the terms of the Common Developer and Distribution License (CDDL) 
 *   as published by Sun Microsystems. For more information see the LICENSE file.
 *   
 *   Use of this software is strictly AT YOUR OWN RISK!!!
 *   If something bad happens you do not have permission to come crying to me.
 *   (that goes for your lawyer as well)
 *
 */
package com.calytrix.disco.pdu.field;

/**
 * The fuse shall be specified by a 16-bit enumeration.
 * 
 * @see "Section 5 in EBV-DOC"
 */
public class Fuse
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int OTHER = 0000;
	public static final int INTELLIGENT_INFLUENCE = 0010;
	public static final int SENSOR = 0020;
	public static final int SELFDESTRUCT = 0030;
	public static final int ULTRA_QUICK = 0040;
	public static final int BODY = 0050;
	public static final int DEEP_INTRUSION = 0060;
	public static final int MULTIFUNCTION = 0100;
	public static final int POINT_DETONATION = 0200;
	public static final int BASE_DETONATION = 0300;
	public static final int CONTACT = 1000;
	public static final int CONTACT_INSTANT = 1100;
	public static final int CONTACT_DELAYED = 1200;
	public static final int CONTACT_ELECTRONIC = 1300;
	public static final int CONTACT_GRAZE = 1400;
	public static final int CONTACT_CRUSH = 1500;
	public static final int CONTACT_HYDROSTATIC = 1600;
	public static final int CONTACT_MECHANICAL = 1700;
	public static final int CONTACT_CHEMICAL = 1800;
	public static final int CONTACT_PIEZOELECTRIC = 1900;
	public static final int CONTACT_POINT_INITIATING = 1910;
	public static final int CONTACT_POINT_INITIATING_BASE_DETONATING = 1920;
	public static final int CONTACT_BASE_DETONATING = 1930;
	public static final int CONTACT_BALLISTIC_CAP_AND_BASE = 1940;
	public static final int CONTACT_BASE = 1950;
	public static final int CONTACT_NOSE = 1960;
	public static final int CONTACT_FITTED_IN_STANDOFF_PROBE = 1970;
	public static final int CONTACT_NONALIGNED = 1980;
	public static final int TIMED = 2000;
	public static final int TIMED_PROGRAMMABLE = 2100;
	public static final int TIMED_BURNOUT = 2200;
	public static final int TIMED_PYROTECHNIC = 2300;
	public static final int TIMED_ELECTRONIC = 2400;
	public static final int TIMED_BASE_DELAY = 2500;
	public static final int TIMED_REINFORCED_NOSE_IMPACT_DELAY = 2600;
	public static final int TIMED_SHORT_DELAY_IMPACT = 2700;
	public static final int TIMED_NOSE_MOUNTED_VARIABLE_DELAY = 2800;
	public static final int TIMED_LONG_DELAY_SIDE = 2900;
	public static final int TIMED_SELECTABLE_DELAY = 2910;
	public static final int TIMED_IMPACT = 2920;
	public static final int TIMED_SEQUENCE = 2930;
	public static final int PROXIMITY = 3000;
	public static final int PROXIMITY_ACTIVE_LASER = 3100;
	public static final int PROXIMITY_MAGNETIC = 3200;
	public static final int PROXIMITY_ACTIVE_RADAR = 3300;
	public static final int PROXIMITY_RADIO_FREQUENCY = 3400;
	public static final int PROXIMITY_PROGRAMMABLE = 3500;
	public static final int PROXIMITY_PROGRAMMABLE_PREFRAGMENTED = 3600;
	public static final int PROXIMITY_INFRARED = 3700;
	public static final int COMMAND = 4000;
	public static final int COMMAND_ELECTRONIC_REMOTELY_SET = 4100;
	public static final int ALTITUDE = 5000;
	public static final int ALTITUDE_RADIO_ALTIMETER = 5100;
	public static final int ALTITUDE_AIR_BURST = 5200;
	public static final int DEPTH = 6000;
	public static final int ACOUSTIC = 7000;
	public static final int PRESSURE = 8000;
	public static final int PRESSURE_DELAY = 8010;
	public static final int INERT = 8100;
	public static final int DUMMY = 8110;
	public static final int PRACTICE = 8120;
	public static final int PLUG_REPRESENTING = 8130;
	public static final int TRAINING = 8150;
	public static final int PYROTECHNIC = 9000;
	public static final int PYROTECHNIC_DELAY = 9010;
	public static final int ELECTROOPTICAL = 9100;
	public static final int ELECTROMECHANICAL = 9110;
	public static final int ELECTROMECHANICAL_NOSE = 9120;
	public static final int STRIKERLESS = 9200;
	public static final int STRIKERLESS_NOSE_IMPACT = 9210;
	public static final int STRIKERLESS_COMPRESSIONIGNITION = 9220;
	public static final int COMPRESSIONIGNITION = 9300;
	public static final int COMPRESSIONIGNITION_STRIKERLESS_NOSE_IMPACT = 9310;
	public static final int PERCUSSION = 9400;
	public static final int PERCUSSION_INSTANTANEOUS = 9410;
	public static final int ELECTRONIC = 9500;
	public static final int ELECTRONIC_INTERNALLY_MOUNTED = 9510;
	public static final int ELECTRONIC_RANGE_SETTING = 9520;
	public static final int ELECTRONIC_PROGRAMMED = 9530;
	public static final int MECHANICAL = 9600;
	public static final int MECHANICAL_NOSE = 9610;
	public static final int MECHANICAL_TAIL = 9620;

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------

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
		    { OTHER, INTELLIGENT_INFLUENCE, SENSOR, SELFDESTRUCT, ULTRA_QUICK, BODY,
		     DEEP_INTRUSION, MULTIFUNCTION, POINT_DETONATION, BASE_DETONATION, CONTACT,
		     CONTACT_INSTANT, CONTACT_DELAYED, CONTACT_ELECTRONIC, CONTACT_GRAZE, CONTACT_CRUSH,
		     CONTACT_HYDROSTATIC, CONTACT_MECHANICAL, CONTACT_CHEMICAL, CONTACT_PIEZOELECTRIC,
		     CONTACT_POINT_INITIATING, CONTACT_POINT_INITIATING_BASE_DETONATING,
		     CONTACT_BASE_DETONATING, CONTACT_BALLISTIC_CAP_AND_BASE, CONTACT_BASE, CONTACT_NOSE,
		     CONTACT_FITTED_IN_STANDOFF_PROBE, CONTACT_NONALIGNED, TIMED, TIMED_PROGRAMMABLE,
		     TIMED_BURNOUT, TIMED_PYROTECHNIC, TIMED_ELECTRONIC, TIMED_BASE_DELAY,
		     TIMED_REINFORCED_NOSE_IMPACT_DELAY, TIMED_SHORT_DELAY_IMPACT,
		     TIMED_NOSE_MOUNTED_VARIABLE_DELAY, TIMED_LONG_DELAY_SIDE, TIMED_SELECTABLE_DELAY,
		     TIMED_IMPACT, TIMED_SEQUENCE, PROXIMITY, PROXIMITY_ACTIVE_LASER, PROXIMITY_MAGNETIC,
		     PROXIMITY_ACTIVE_RADAR, PROXIMITY_RADIO_FREQUENCY, PROXIMITY_PROGRAMMABLE,
		     PROXIMITY_PROGRAMMABLE_PREFRAGMENTED, PROXIMITY_INFRARED, COMMAND,
		     COMMAND_ELECTRONIC_REMOTELY_SET, ALTITUDE, ALTITUDE_RADIO_ALTIMETER,
		     ALTITUDE_AIR_BURST, DEPTH, ACOUSTIC, PRESSURE, PRESSURE_DELAY, INERT, DUMMY, PRACTICE,
		     PLUG_REPRESENTING, TRAINING, PYROTECHNIC, PYROTECHNIC_DELAY, ELECTROOPTICAL,
		     ELECTROMECHANICAL, ELECTROMECHANICAL_NOSE, STRIKERLESS, STRIKERLESS_NOSE_IMPACT,
		     STRIKERLESS_COMPRESSIONIGNITION, COMPRESSIONIGNITION,
		     COMPRESSIONIGNITION_STRIKERLESS_NOSE_IMPACT, PERCUSSION, PERCUSSION_INSTANTANEOUS,
		     ELECTRONIC, ELECTRONIC_INTERNALLY_MOUNTED, ELECTRONIC_RANGE_SETTING,
		     ELECTRONIC_PROGRAMMED, MECHANICAL, MECHANICAL_NOSE, MECHANICAL_TAIL };
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
		String description = "Mechanical, Tail";
		switch( value )
		{
			case MECHANICAL_NOSE:
				description = "Mechanical, Nose";
				break;

			case MECHANICAL:
				description = "Mechanical";
				break;

			case ELECTRONIC_PROGRAMMED:
				description = "Electronic, Programmed";
				break;

			case ELECTRONIC_RANGE_SETTING:
				description = "Electronic, Range Setting";
				break;

			case ELECTRONIC_INTERNALLY_MOUNTED:
				description = "Electronic, Internally Mounted";
				break;

			case ELECTRONIC:
				description = "Electronic";
				break;

			case PERCUSSION_INSTANTANEOUS:
				description = "Percussion, Instantaneous";
				break;

			case PERCUSSION:
				description = "Percussion";
				break;

			case COMPRESSIONIGNITION_STRIKERLESS_NOSE_IMPACT:
				description = "Compression-Ignition, Strikerless, Nose Impact";
				break;

			case COMPRESSIONIGNITION:
				description = "Compression-Ignition";
				break;

			case STRIKERLESS_COMPRESSIONIGNITION:
				description = "Strikerless, Compression-Ignition";
				break;

			case STRIKERLESS_NOSE_IMPACT:
				description = "Strikerless, Nose Impact";
				break;

			case STRIKERLESS:
				description = "Strikerless";
				break;

			case ELECTROMECHANICAL_NOSE:
				description = "Electromechanical, Nose";
				break;

			case ELECTROMECHANICAL:
				description = "Electromechanical";
				break;

			case ELECTROOPTICAL:
				description = "Electro-optical";
				break;

			case PYROTECHNIC_DELAY:
				description = "Pyrotechnic, Delay";
				break;

			case PYROTECHNIC:
				description = "Pyrotechnic";
				break;

			case TRAINING:
				description = "Training";
				break;

			case PLUG_REPRESENTING:
				description = "Plug Representing";
				break;

			case PRACTICE:
				description = "Practice";
				break;

			case DUMMY:
				description = "Dummy";
				break;

			case INERT:
				description = "Inert";
				break;

			case PRESSURE_DELAY:
				description = "Pressure, Delay";
				break;

			case PRESSURE:
				description = "Pressure";
				break;

			case ACOUSTIC:
				description = "Acoustic";
				break;

			case DEPTH:
				description = "Depth";
				break;

			case ALTITUDE_AIR_BURST:
				description = "Altitude, Air Burst";
				break;

			case ALTITUDE_RADIO_ALTIMETER:
				description = "Altitude, Radio Altimeter";
				break;

			case ALTITUDE:
				description = "Altitude";
				break;

			case COMMAND_ELECTRONIC_REMOTELY_SET:
				description = "Command, Electronic, Remotely Set";
				break;

			case COMMAND:
				description = "Command";
				break;

			case PROXIMITY_INFRARED:
				description = "Proximity, Infrared";
				break;

			case PROXIMITY_PROGRAMMABLE_PREFRAGMENTED:
				description = "Proximity, Programmable, Prefragmented";
				break;

			case PROXIMITY_PROGRAMMABLE:
				description = "Proximity, Programmable";
				break;

			case PROXIMITY_RADIO_FREQUENCY:
				description = "Proximity, Radio Frequency";
				break;

			case PROXIMITY_ACTIVE_RADAR:
				description = "Proximity, Active Radar";
				break;

			case PROXIMITY_MAGNETIC:
				description = "Proximity, Magnetic";
				break;

			case PROXIMITY_ACTIVE_LASER:
				description = "Proximity, Active Laser";
				break;

			case PROXIMITY:
				description = "Proximity";
				break;

			case TIMED_SEQUENCE:
				description = "Timed, Sequence";
				break;

			case TIMED_IMPACT:
				description = "Timed, Impact";
				break;

			case TIMED_SELECTABLE_DELAY:
				description = "Timed, Selectable Delay";
				break;

			case TIMED_LONG_DELAY_SIDE:
				description = "Timed, Long Delay Side";
				break;

			case TIMED_NOSE_MOUNTED_VARIABLE_DELAY:
				description = "Timed, Nose Mounted Variable Delay";
				break;

			case TIMED_SHORT_DELAY_IMPACT:
				description = "Timed, Short Delay Impact";
				break;

			case TIMED_REINFORCED_NOSE_IMPACT_DELAY:
				description = "Timed, Reinforced Nose Impact Delay";
				break;

			case TIMED_BASE_DELAY:
				description = "Timed, Base Delay";
				break;

			case TIMED_ELECTRONIC:
				description = "Timed, Electronic";
				break;

			case TIMED_PYROTECHNIC:
				description = "Timed, Pyrotechnic";
				break;

			case TIMED_BURNOUT:
				description = "Timed, Burnout";
				break;

			case TIMED_PROGRAMMABLE:
				description = "Timed, Programmable";
				break;

			case TIMED:
				description = "Timed";
				break;

			case CONTACT_NONALIGNED:
				description = "Contact, Non-aligned";
				break;

			case CONTACT_FITTED_IN_STANDOFF_PROBE:
				description = "Contact, Fitted in Standoff Probe";
				break;

			case CONTACT_NOSE:
				description = "Contact, Nose";
				break;

			case CONTACT_BASE:
				description = "Contact, Base";
				break;

			case CONTACT_BALLISTIC_CAP_AND_BASE:
				description = "Contact, Ballistic Cap and Base";
				break;

			case CONTACT_BASE_DETONATING:
				description = "Contact, Base Detonating";
				break;

			case CONTACT_POINT_INITIATING_BASE_DETONATING:
				description = "Contact, Point Initiating, Base Detonating";
				break;

			case CONTACT_POINT_INITIATING:
				description = "Contact, Point Initiating";
				break;

			case CONTACT_PIEZOELECTRIC:
				description = "Contact, Piezoelectric";
				break;

			case CONTACT_CHEMICAL:
				description = "Contact, Chemical";
				break;

			case CONTACT_MECHANICAL:
				description = "Contact, Mechanical";
				break;

			case CONTACT_HYDROSTATIC:
				description = "Contact, Hydrostatic";
				break;

			case CONTACT_CRUSH:
				description = "Contact, Crush";
				break;

			case CONTACT_GRAZE:
				description = "Contact, Graze";
				break;

			case CONTACT_ELECTRONIC:
				description = "Contact, Electronic";
				break;

			case CONTACT_DELAYED:
				description = "Contact, Delayed";
				break;

			case CONTACT_INSTANT:
				description = "Contact, Instant";
				break;

			case CONTACT:
				description = "Contact";
				break;

			case BASE_DETONATION:
				description = "Base Detonation";
				break;

			case POINT_DETONATION:
				description = "Point Detonation";
				break;

			case MULTIFUNCTION:
				description = "Multifunction";
				break;

			case DEEP_INTRUSION:
				description = "Deep Intrusion";
				break;

			case BODY:
				description = "Body";
				break;

			case ULTRA_QUICK:
				description = "Ultra Quick";
				break;

			case SELFDESTRUCT:
				description = "Self-destruct";
				break;

			case SENSOR:
				description = "Sensor";
				break;

			case INTELLIGENT_INFLUENCE:
				description = "Intelligent Influence";
				break;

			case OTHER:
				description = "Other";
				break;
		}

		return description;
	}
}
