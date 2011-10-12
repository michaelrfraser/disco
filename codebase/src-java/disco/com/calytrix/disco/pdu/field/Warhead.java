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
 * The warhead shall be specified by a 16-bit enumeration.
 * 
 * @see "Section 5 in EBV-DOC"
 */
public class Warhead
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int OTHER = 0000;
	public static final int CARGO = 0010;
	public static final int FUEL_AIR_EXPLOSIVE = 0020;
	public static final int GLASS_BLADS = 0030;
	public static final int ONE_UM = 0031;
	public static final int FIVE_UM = 0032;
	public static final int TEN_UM = 0033;
	public static final int HIGH_EXPLOSIVE = 1000;
	public static final int HE_PLASTIC = 1100;
	public static final int HE_INCENDIARY = 1200;
	public static final int HE_FRAGMENTATION = 1300;
	public static final int HE_ANTITANK = 1400;
	public static final int HE_BOMBLETS = 1500;
	public static final int HE_SHAPED_CHARGE = 1600;
	public static final int HE_CONTINUOUS_ROD = 1610;
	public static final int HE_TUNGSTEN_BALL = 1615;
	public static final int HE_BLAST_FRAGMENTATION = 1620;
	public static final int HE_STEERABLE_DARTS_WITH_HE = 1625;
	public static final int HE_DARTS = 1630;
	public static final int HE_FLECHETTES = 1635;
	public static final int HE_DIRECTED_FRAGMENTATION = 1640;
	public static final int HE_SEMIARMOR_PIERCING = 1645;
	public static final int HE_SHAPED_CHARGE_FRAGMENTATION = 1650;
	public static final int HE_SEMIARMOR_PIERCING_FRAGMENTATION = 1655;
	public static final int HE_HALLOW_CHARGE = 1660;
	public static final int HE_DOUBLE_HALLOW_CHARGE = 1665;
	public static final int HE_GENERAL_PURPOSE = 1670;
	public static final int HE_BLAST_PENETRATOR = 1675;
	public static final int HE_ROD_PENETRATOR = 1680;
	public static final int HE_ANTIPERSONNEL = 1685;
	public static final int SMOKE = 2000;
	public static final int ILLUMINATION = 3000;
	public static final int PRACTICE = 4000;
	public static final int KINETIC = 5000;
	public static final int MINES = 6000;
	public static final int NUCLEAR = 7000;
	public static final int NUCLEAR_IMT = 7010;
	public static final int CHEMICAL_GENERAL = 8000;
	public static final int CHEMICAL_BLISTER_AGENT = 8100;
	public static final int HD = 8110;
	public static final int THICKENED_HD = 8115;
	public static final int DUSTY_HD = 8120;
	public static final int CHEMICAL_BLOOD_AGENT = 8200;
	public static final int AC = 8210;
	public static final int CK = 8215;
	public static final int CG = 8220;
	public static final int CHEMICAL_NERVE_AGENT = 8300;
	public static final int VX = 8310;
	public static final int THICKENED_VX = 8315;
	public static final int DUSTY_VX = 8320;
	public static final int GA = 8325;
	public static final int THICKENED_GA = 8330;
	public static final int DUSTY_GA = 8335;
	public static final int GB = 8340;
	public static final int THICKENED_GB = 8345;
	public static final int DUSTY_GB = 8350;
	public static final int GD = 8355;
	public static final int THICKENED_GD = 8360;
	public static final int DUSTY_GD = 8365;
	public static final int GF = 8370;
	public static final int THICKENED_GF = 8375;
	public static final int DUSTY_GF = 8380;
	public static final int BIOLOGICAL = 9000;
	public static final int BIOLOGICAL_VIRUS = 9100;
	public static final int BIOLOGICAL_BACTERIA = 9200;
	public static final int BIOLOGICAL_RICKETTSIA = 9300;
	public static final int BIOLOGICAL_GENETICALLY_MODIFIED_MICROORGANISMS = 9400;
	public static final int BIOLOGICAL_TOXIN = 9500;

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
		int[] values = { OTHER,
		                 CARGO,
		                 FUEL_AIR_EXPLOSIVE,
		                 GLASS_BLADS,
		                 ONE_UM,
		                 FIVE_UM,
		                 TEN_UM,
		                 HIGH_EXPLOSIVE,
		                 HE_PLASTIC,
		                 HE_INCENDIARY,
		                 HE_FRAGMENTATION,
		                 HE_ANTITANK,
		                 HE_BOMBLETS,
		                 HE_SHAPED_CHARGE,
		                 HE_CONTINUOUS_ROD,
		                 HE_TUNGSTEN_BALL,
		                 HE_BLAST_FRAGMENTATION,
		                 HE_STEERABLE_DARTS_WITH_HE,
		                 HE_DARTS,
		                 HE_FLECHETTES,
		                 HE_DIRECTED_FRAGMENTATION,
		                 HE_SEMIARMOR_PIERCING,
		                 HE_SHAPED_CHARGE_FRAGMENTATION,
		                 HE_SEMIARMOR_PIERCING_FRAGMENTATION,
		                 HE_HALLOW_CHARGE,
		                 HE_DOUBLE_HALLOW_CHARGE,
		                 HE_GENERAL_PURPOSE,
		                 HE_BLAST_PENETRATOR,
		                 HE_ROD_PENETRATOR,
		                 HE_ANTIPERSONNEL,
		                 SMOKE,
		                 ILLUMINATION,
		                 PRACTICE,
		                 KINETIC,
		                 MINES,
		                 NUCLEAR,
		                 NUCLEAR_IMT,
		                 CHEMICAL_GENERAL,
		                 CHEMICAL_BLISTER_AGENT,
		                 HD,
		                 THICKENED_HD,
		                 DUSTY_HD,
		                 CHEMICAL_BLOOD_AGENT,
		                 AC,
		                 CK,
		                 CG,
		                 CHEMICAL_NERVE_AGENT,
		                 VX,
		                 THICKENED_VX,
		                 DUSTY_VX,
		                 GA,
		                 THICKENED_GA,
		                 DUSTY_GA,
		                 GB,
		                 THICKENED_GB,
		                 DUSTY_GB,
		                 GD,
		                 THICKENED_GD,
		                 DUSTY_GD,
		                 GF,
		                 THICKENED_GF,
		                 DUSTY_GF,
		                 BIOLOGICAL,
		                 BIOLOGICAL_VIRUS,
		                 BIOLOGICAL_BACTERIA,
		                 BIOLOGICAL_RICKETTSIA,
		                 BIOLOGICAL_GENETICALLY_MODIFIED_MICROORGANISMS,
		                 BIOLOGICAL_TOXIN };
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
		String description = "Biological, Toxin";
		switch( value )
		{
			case BIOLOGICAL_TOXIN:
				description = "Biological, Toxin";
				break;
				
			case BIOLOGICAL_GENETICALLY_MODIFIED_MICROORGANISMS:
				description = "Biological, Genetically Modified Micro-organisms";
				break;

			case BIOLOGICAL_RICKETTSIA:
				description = "Biological, Rickettsia";
				break;

			case BIOLOGICAL_BACTERIA:
				description = "Biological, Bacteria";
				break;

			case BIOLOGICAL_VIRUS:
				description = "Biological, Virus";
				break;

			case BIOLOGICAL:
				description = "Biological";
				break;

			case DUSTY_GF:
				description = "Dusty GF";
				break;

			case THICKENED_GF:
				description = "Thickened GF";
				break;

			case GF:
				description = "GF";
				break;

			case DUSTY_GD:
				description = "Dusty GD";
				break;

			case THICKENED_GD:
				description = "Thickened GD";
				break;

			case GD:
				description = "GD";
				break;

			case DUSTY_GB:
				description = "Dusty GB";
				break;

			case THICKENED_GB:
				description = "Thickened GB";
				break;

			case GB:
				description = "GB";
				break;

			case DUSTY_GA:
				description = "Dusty GA";
				break;

			case THICKENED_GA:
				description = "Thickened GA";
				break;

			case GA:
				description = "GA";
				break;

			case DUSTY_VX:
				description = "Dusty VX";
				break;

			case THICKENED_VX:
				description = "Thickened VX";
				break;

			case VX:
				description = "VX";
				break;

			case CHEMICAL_NERVE_AGENT:
				description = "Chemical, Nerve Agent";
				break;

			case CG:
				description = "CG";
				break;

			case CK:
				description = "CK";
				break;

			case AC:
				description = "AC";
				break;

			case CHEMICAL_BLOOD_AGENT:
				description = "Chemical, Blood Agent";
				break;

			case DUSTY_HD:
				description = "Dusty HD";
				break;

			case THICKENED_HD:
				description = "Thickened HD";
				break;

			case HD:
				description = "HD";
				break;

			case CHEMICAL_BLISTER_AGENT:
				description = "Chemical, Blister Agent";
				break;

			case CHEMICAL_GENERAL:
				description = "Chemical, General";
				break;

			case NUCLEAR_IMT:
				description = "Nuclear, IMT";
				break;

			case NUCLEAR:
				description = "Nuclear";
				break;

			case MINES:
				description = "Mines";
				break;

			case KINETIC:
				description = "Kinetic";
				break;

			case PRACTICE:
				description = "Practice";
				break;

			case ILLUMINATION:
				description = "Illumination";
				break;

			case SMOKE:
				description = "Smoke";
				break;

			case HE_ANTIPERSONNEL:
				description = "HE, Antipersonnel";
				break;

			case HE_ROD_PENETRATOR:
				description = "HE, Rod Penetrator";
				break;

			case HE_BLAST_PENETRATOR:
				description = "HE, Blast Penetrator";
				break;

			case HE_GENERAL_PURPOSE:
				description = "HE, General Purpose";
				break;

			case HE_DOUBLE_HALLOW_CHARGE:
				description = "HE, Double Hallow Charge";
				break;

			case HE_HALLOW_CHARGE:
				description = "HE, Hallow Charge";
				break;

			case HE_SEMIARMOR_PIERCING_FRAGMENTATION:
				description = "HE, Semi-Armor Piercing, Fragmentation";
				break;

			case HE_SHAPED_CHARGE_FRAGMENTATION:
				description = "HE, Shaped Charge Fragmentation";
				break;

			case HE_SEMIARMOR_PIERCING:
				description = "HE, Semi-Armor Piercing";
				break;

			case HE_DIRECTED_FRAGMENTATION:
				description = "HE, Directed Fragmentation";
				break;

			case HE_FLECHETTES:
				description = "HE, Flechettes";
				break;

			case HE_DARTS:
				description = "HE, Darts";
				break;

			case HE_STEERABLE_DARTS_WITH_HE:
				description = "HE, Steerable Darts with HE";
				break;

			case HE_BLAST_FRAGMENTATION:
				description = "HE, Blast Fragmentation";
				break;

			case HE_TUNGSTEN_BALL:
				description = "HE, Tungsten Ball";
				break;

			case HE_CONTINUOUS_ROD:
				description = "HE, Continuous Rod";
				break;

			case HE_SHAPED_CHARGE:
				description = "HE, Shaped Charge";
				break;

			case HE_BOMBLETS:
				description = "HE, Bomblets";
				break;

			case HE_ANTITANK:
				description = "HE, Antitank";
				break;

			case HE_FRAGMENTATION:
				description = "HE, Fragmentation";
				break;

			case HE_INCENDIARY:
				description = "HE, Incendiary";
				break;

			case HE_PLASTIC:
				description = "HE, Plastic";
				break;

			case HIGH_EXPLOSIVE:
				description = "High Explosive";
				break;

			case TEN_UM:
				description = "10 um";
				break;

			case FIVE_UM:
				description = "5 um";
				break;

			case ONE_UM:
				description = "1 um";
				break;

			case GLASS_BLADS:
				description = "Glass Blads";
				break;

			case FUEL_AIR_EXPLOSIVE:
				description = "Fuel/Air Explosive";
				break;

			case CARGO:
				description = "Cargo";
				break;

			case OTHER:
				description = "Other";
				break;
		}

		return description;
	}

}
