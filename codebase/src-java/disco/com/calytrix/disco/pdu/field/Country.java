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
 * This field shall specify the country to which the design of the entity is attributed. This
 * field shall be represented by a 16-bit enumeration.
 * 
 * @see "Section 4 in EBV-DOC"
 */
public class Country
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final int OTHER = 0;
	public static final int AFGHANISTAN = 1;
	public static final int ARGENTINA = 10;
	public static final int INDONESIA = 100;
	public static final int IRAN = 101;
	public static final int IRAQ = 102;
	public static final int IRELAND = 104;
	public static final int ISRAEL = 105;
	public static final int ITALY = 106;
	public static final int COTE_DIVOIRE = 107;
	public static final int JAMAICA = 108;
	public static final int JAN_MAYEN = 109;
	public static final int ARUBA = 11;
	public static final int JAPAN = 110;
	public static final int JARVIS_ISLAND = 111;
	public static final int JERSEY = 112;
	public static final int JOHNSTON_ATOLL = 113;
	public static final int JORDAN = 114;
	public static final int JUAN_DE_NOVA_ISLAND = 115;
	public static final int KENYA = 116;
	public static final int KINGMAN_REEF = 117;
	public static final int KIRIBATI = 118;
	public static final int KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF = 119;
	public static final int ASHMORE_AND_CARTIER_ISLANDS = 12;
	public static final int KOREA_REPUBLIC_OF = 120;
	public static final int KUWAIT = 121;
	public static final int LAOS = 122;
	public static final int LEBANON = 123;
	public static final int LESOTHO = 124;
	public static final int LIBERIA = 125;
	public static final int LIBYA = 126;
	public static final int LIECHTENSTEIN = 127;
	public static final int LUXEMBOURG = 128;
	public static final int MADAGASCAR = 129;
	public static final int AUSTRALIA = 13;
	public static final int MACAU = 130;
	public static final int MALAWI = 131;
	public static final int MALAYSIA = 132;
	public static final int MALDIVES = 133;
	public static final int MALI = 134;
	public static final int MALTA = 135;
	public static final int MAN_ISLE_OF = 136;
	public static final int MARSHALL_ISLANDS = 137;
	public static final int MARTINIQUE = 138;
	public static final int MAURITANIA = 139;
	public static final int AUSTRIA = 14;
	public static final int MAURITIUS = 140;
	public static final int MAYOTTE = 141;
	public static final int MEXICO = 142;
	public static final int MICRONESIA_FEDERATIVE_STATES_OF = 143;
	public static final int MONACO = 144;
	public static final int MONGOLIA = 145;
	public static final int MONTSERRAT = 146;
	public static final int MOROCCO = 147;
	public static final int MOZAMBIQUE = 148;
	public static final int NAMIBIA = 149;
	public static final int BAHAMAS = 15;
	public static final int NAURU = 150;
	public static final int NAVASSA_ISLAND = 151;
	public static final int NEPAL = 152;
	public static final int NETHERLANDS = 153;
	public static final int NETHERLANDS_ANTILLES = 154;
	public static final int NEW_CALEDONIA = 155;
	public static final int NEW_ZEALAND = 156;
	public static final int NICARAGUA = 157;
	public static final int NIGER = 158;
	public static final int NIGERIA = 159;
	public static final int BAHRAIN = 16;
	public static final int NIUE = 160;
	public static final int NORFOLK_ISLAND = 161;
	public static final int NORTHERN_MARIANA_ISLANDS = 162;
	public static final int NORWAY = 163;
	public static final int OMAN = 164;
	public static final int PAKISTAN = 165;
	public static final int PALMYRA_ATOLL = 166;
	public static final int PANAMA = 168;
	public static final int PAPUA_NEW_GUINEA = 169;
	public static final int BAKER_ISLAND = 17;
	public static final int PARACEL_ISLANDS = 170;
	public static final int PARAGUAY = 171;
	public static final int PERU = 172;
	public static final int PHILIPPINES = 173;
	public static final int PITCAIRN_ISLANDS = 174;
	public static final int POLAND = 175;
	public static final int PORTUGAL = 176;
	public static final int PUERTO_RICO = 177;
	public static final int QATAR = 178;
	public static final int REUNION = 179;
	public static final int BANGLADESH = 18;
	public static final int ROMANIA = 180;
	public static final int RWANDA = 181;
	public static final int ST_KITTS_AND_NEVIS = 182;
	public static final int ST_HELENA = 183;
	public static final int ST_LUCIA = 184;
	public static final int ST_PIERRE_AND_MIQUELON = 185;
	public static final int ST_VINCENT_AND_THE_GRENADINES = 186;
	public static final int SAN_MARINO = 187;
	public static final int SAO_TOME_AND_PRINCIPE = 188;
	public static final int SAUDI_ARABIA = 189;
	public static final int BARBADOS = 19;
	public static final int SENEGAL = 190;
	public static final int SEYCHELLES = 191;
	public static final int SIERRA_LEONE = 192;
	public static final int SINGAPORE = 193;
	public static final int SOLOMON_ISLANDS = 194;
	public static final int SOMALIA = 195;
	public static final int SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS = 196;
	public static final int SOUTH_AFRICA = 197;
	public static final int SPAIN = 198;
	public static final int SPRATLY_ISLANDS = 199;
	public static final int ALBANIA = 2;
	public static final int BASSAS_DA_INDIA = 20;
	public static final int SRI_LANKA = 200;
	public static final int SUDAN = 201;
	public static final int SURINAME = 202;
	public static final int SVALBARD = 203;
	public static final int SWAZILAND = 204;
	public static final int SWEDEN = 205;
	public static final int SWITZERLAND = 206;
	public static final int SYRIA = 207;
	public static final int TAIWAN = 208;
	public static final int TANZANIA = 209;
	public static final int BELGIUM = 21;
	public static final int THAILAND = 210;
	public static final int TOGO = 211;
	public static final int TOKELAU = 212;
	public static final int TONGA = 213;
	public static final int TRINIDAD_AND_TOBAGO = 214;
	public static final int TROMELIN_ISLAND = 215;
	public static final int PACIFIC_ISLANDS_TRUST_TERRITORY_OF_THE = 216;
	public static final int TUNISIA = 217;
	public static final int TURKEY = 218;
	public static final int TURKS_AND_CAICOS_ISLANDS = 219;
	public static final int BELIZE = 22;
	public static final int TUVALU = 220;
	public static final int UGANDA = 221;
	public static final int COMMONWEALTH_OF_INDEPENDENT_STATES = 222;
	public static final int UNITED_ARAB_EMIRATES = 223;
	public static final int UNITED_KINGDOM = 224;
	public static final int UNITED_STATES = 225;
	public static final int URUGUAY = 226;
	public static final int VANUATU = 227;
	public static final int VATICAN_CITY = 228;
	public static final int VENEZUELA = 229;
	public static final int BENIN = 23;
	public static final int VIETNAM = 230;
	public static final int VIRGIN_ISLANDS = 231;
	public static final int WAKE_ISLAND = 232;
	public static final int WALLIS_AND_FUTUNA = 233;
	public static final int WESTERN_SAHARA = 234;
	public static final int WEST_BANK = 235;
	public static final int WESTERN_SAMOA = 236;
	public static final int YEMEN = 237;
	public static final int BERMUDA = 24;
	public static final int ZAIRE = 241;
	public static final int ZAMBIA = 242;
	public static final int ZIMBABWE = 243;
	public static final int ARMENIA = 244;
	public static final int AZERBAIJAN = 245;
	public static final int BELARUS = 246;
	public static final int BOSNIA_AND_HERCEGOVINA = 247;
	public static final int CLIPPERTON_ISLAND = 248;
	public static final int CROATIA = 249;
	public static final int BHUTAN = 25;
	public static final int ESTONIA = 250;
	public static final int GEORGIA = 251;
	public static final int KAZAKHSTAN = 252;
	public static final int KYRGYZSTAN = 253;
	public static final int LATVIA = 254;
	public static final int LITHUANIA = 255;
	public static final int MACEDONIA = 256;
	public static final int MIDWAY_ISLANDS = 257;
	public static final int MOLDOVA = 258;
	public static final int MONTENEGRO = 259;
	public static final int BOLIVIA = 26;
	public static final int RUSSIA = 260;
	public static final int SERBIA_AND_MONTENEGRO = 261;
	public static final int SLOVENIA = 262;
	public static final int TAJIKISTAN = 263;
	public static final int TURKMENISTAN = 264;
	public static final int UKRAINE = 265;
	public static final int UZBEKISTAN = 266;
	public static final int BOTSWANA = 27;
	public static final int BOUVET_ISLAND = 28;
	public static final int BRAZIL = 29;
	public static final int ALGERIA = 3;
	public static final int BRITISH_INDIAN_OCEAN_TERRITORY = 30;
	public static final int BRITISH_VIRGIN_ISLANDS = 31;
	public static final int BRUNEI = 32;
	public static final int BULGARIA = 33;
	public static final int BURKINA = 34;
	public static final int BURMA = 35;
	public static final int BURUNDI = 36;
	public static final int CAMBODIA = 37;
	public static final int CAMEROON = 38;
	public static final int CANADA = 39;
	public static final int AMERICAN_SAMOA = 4;
	public static final int CAPE_VERDE_REPUBLIC_OF = 40;
	public static final int CAYMAN_ISLANDS = 41;
	public static final int CENTRAL_AFRICAN_REPUBLIC = 42;
	public static final int CHAD = 43;
	public static final int CHILE = 44;
	public static final int CHINA_PEOPLES_REPUBLIC_OF = 45;
	public static final int CHRISTMAS_ISLAND = 46;
	public static final int COCOS = 47;
	public static final int COLOMBIA = 48;
	public static final int COMOROS = 49;
	public static final int ANDORRA = 5;
	public static final int CONGO_REPUBLIC_OF = 50;
	public static final int COOK_ISLANDS = 51;
	public static final int CORAL_SEA_ISLANDS = 52;
	public static final int COSTA_RICA = 53;
	public static final int CUBA = 54;
	public static final int CYPRUS = 55;
	public static final int CZECHOSLOVAKIA = 56;
	public static final int DENMARK = 57;
	public static final int DJIBOUTI = 58;
	public static final int DOMINICA = 59;
	public static final int ANGOLA = 6;
	public static final int DOMINICAN_REPUBLIC = 60;
	public static final int ECUADOR = 61;
	public static final int EGYPT = 62;
	public static final int EL_SALVADOR = 63;
	public static final int EQUATORIAL_GUINEA = 64;
	public static final int ETHIOPIA = 65;
	public static final int EUROPA_ISLAND = 66;
	public static final int FALKLAND_ISLANDS = 67;
	public static final int FAROE_ISLANDS = 68;
	public static final int FIJI = 69;
	public static final int ANGUILLA = 7;
	public static final int FINLAND = 70;
	public static final int FRANCE = 71;
	public static final int FRENCH_GUIANA = 72;
	public static final int FRENCH_POLYNESIA = 73;
	public static final int FRENCH_SOUTHERN_AND_ANTARCTIC_ISLANDS = 74;
	public static final int GABON = 75;
	public static final int GAMBIA_THE = 76;
	public static final int GAZA_STRIP = 77;
	public static final int GERMANY = 78;
	public static final int GHANA = 79;
	public static final int ANTARCTICA = 8;
	public static final int GIBRALTAR = 80;
	public static final int GLORIOSO_ISLANDS = 81;
	public static final int GREECE = 82;
	public static final int GREENLAND = 83;
	public static final int GRENADA = 84;
	public static final int GUADALOUPE = 85;
	public static final int GUAM = 86;
	public static final int GUATEMALA = 87;
	public static final int GUERNSEY = 88;
	public static final int GUINEA = 89;
	public static final int ANTIGUA_AND_BARBUDA = 9;
	public static final int GUINEA_BISSAU = 90;
	public static final int GUYANA = 91;
	public static final int HAITI = 92;
	public static final int HEARD_ISLAND_AND_MCDONALD_ISLANDS = 93;
	public static final int HONDURAS = 94;
	public static final int HONG_KONG = 95;
	public static final int HOWLAND_ISLAND = 96;
	public static final int HUNGARY = 97;
	public static final int ICELAND = 98;
	public static final int INDIA = 99;

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
		    { OTHER, AFGHANISTAN, ARGENTINA, INDONESIA, IRAN, IRAQ, IRELAND, ISRAEL, ITALY,
		     COTE_DIVOIRE, JAMAICA, JAN_MAYEN, ARUBA, JAPAN, JARVIS_ISLAND, JERSEY, JOHNSTON_ATOLL,
		     JORDAN, JUAN_DE_NOVA_ISLAND, KENYA, KINGMAN_REEF, KIRIBATI,
		     KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF, ASHMORE_AND_CARTIER_ISLANDS, KOREA_REPUBLIC_OF,
		     KUWAIT, LAOS, LEBANON, LESOTHO, LIBERIA, LIBYA, LIECHTENSTEIN, LUXEMBOURG, MADAGASCAR,
		     AUSTRALIA, MACAU, MALAWI, MALAYSIA, MALDIVES, MALI, MALTA, MAN_ISLE_OF,
		     MARSHALL_ISLANDS, MARTINIQUE, MAURITANIA, AUSTRIA, MAURITIUS, MAYOTTE, MEXICO,
		     MICRONESIA_FEDERATIVE_STATES_OF, MONACO, MONGOLIA, MONTSERRAT, MOROCCO, MOZAMBIQUE,
		     NAMIBIA, BAHAMAS, NAURU, NAVASSA_ISLAND, NEPAL, NETHERLANDS, NETHERLANDS_ANTILLES,
		     NEW_CALEDONIA, NEW_ZEALAND, NICARAGUA, NIGER, NIGERIA, BAHRAIN, NIUE, NORFOLK_ISLAND,
		     NORTHERN_MARIANA_ISLANDS, NORWAY, OMAN, PAKISTAN, PALMYRA_ATOLL, PANAMA,
		     PAPUA_NEW_GUINEA, BAKER_ISLAND, PARACEL_ISLANDS, PARAGUAY, PERU, PHILIPPINES,
		     PITCAIRN_ISLANDS, POLAND, PORTUGAL, PUERTO_RICO, QATAR, REUNION, BANGLADESH, ROMANIA,
		     RWANDA, ST_KITTS_AND_NEVIS, ST_HELENA, ST_LUCIA, ST_PIERRE_AND_MIQUELON,
		     ST_VINCENT_AND_THE_GRENADINES, SAN_MARINO, SAO_TOME_AND_PRINCIPE, SAUDI_ARABIA,
		     BARBADOS, SENEGAL, SEYCHELLES, SIERRA_LEONE, SINGAPORE, SOLOMON_ISLANDS, SOMALIA,
		     SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS, SOUTH_AFRICA, SPAIN, SPRATLY_ISLANDS,
		     ALBANIA, BASSAS_DA_INDIA, SRI_LANKA, SUDAN, SURINAME, SVALBARD, SWAZILAND, SWEDEN,
		     SWITZERLAND, SYRIA, TAIWAN, TANZANIA, BELGIUM, THAILAND, TOGO, TOKELAU, TONGA,
		     TRINIDAD_AND_TOBAGO, TROMELIN_ISLAND, PACIFIC_ISLANDS_TRUST_TERRITORY_OF_THE, TUNISIA,
		     TURKEY, TURKS_AND_CAICOS_ISLANDS, BELIZE, TUVALU, UGANDA,
		     COMMONWEALTH_OF_INDEPENDENT_STATES, UNITED_ARAB_EMIRATES, UNITED_KINGDOM,
		     UNITED_STATES, URUGUAY, VANUATU, VATICAN_CITY, VENEZUELA, BENIN, VIETNAM,
		     VIRGIN_ISLANDS, WAKE_ISLAND, WALLIS_AND_FUTUNA, WESTERN_SAHARA, WEST_BANK,
		     WESTERN_SAMOA, YEMEN, BERMUDA, ZAIRE, ZAMBIA, ZIMBABWE, ARMENIA, AZERBAIJAN, BELARUS,
		     BOSNIA_AND_HERCEGOVINA, CLIPPERTON_ISLAND, CROATIA, BHUTAN, ESTONIA, GEORGIA,
		     KAZAKHSTAN, KYRGYZSTAN, LATVIA, LITHUANIA, MACEDONIA, MIDWAY_ISLANDS, MOLDOVA,
		     MONTENEGRO, BOLIVIA, RUSSIA, SERBIA_AND_MONTENEGRO, SLOVENIA, TAJIKISTAN,
		     TURKMENISTAN, UKRAINE, UZBEKISTAN, BOTSWANA, BOUVET_ISLAND, BRAZIL, ALGERIA,
		     BRITISH_INDIAN_OCEAN_TERRITORY, BRITISH_VIRGIN_ISLANDS, BRUNEI, BULGARIA, BURKINA,
		     BURMA, BURUNDI, CAMBODIA, CAMEROON, CANADA, AMERICAN_SAMOA, CAPE_VERDE_REPUBLIC_OF,
		     CAYMAN_ISLANDS, CENTRAL_AFRICAN_REPUBLIC, CHAD, CHILE, CHINA_PEOPLES_REPUBLIC_OF,
		     CHRISTMAS_ISLAND, COCOS, COLOMBIA, COMOROS, ANDORRA, CONGO_REPUBLIC_OF, COOK_ISLANDS,
		     CORAL_SEA_ISLANDS, COSTA_RICA, CUBA, CYPRUS, CZECHOSLOVAKIA, DENMARK, DJIBOUTI,
		     DOMINICA, ANGOLA, DOMINICAN_REPUBLIC, ECUADOR, EGYPT, EL_SALVADOR, EQUATORIAL_GUINEA,
		     ETHIOPIA, EUROPA_ISLAND, FALKLAND_ISLANDS, FAROE_ISLANDS, FIJI, ANGUILLA, FINLAND,
		     FRANCE, FRENCH_GUIANA, FRENCH_POLYNESIA, FRENCH_SOUTHERN_AND_ANTARCTIC_ISLANDS, GABON,
		     GAMBIA_THE, GAZA_STRIP, GERMANY, GHANA, ANTARCTICA, GIBRALTAR, GLORIOSO_ISLANDS,
		     GREECE, GREENLAND, GRENADA, GUADALOUPE, GUAM, GUATEMALA, GUERNSEY, GUINEA,
		     ANTIGUA_AND_BARBUDA, GUINEA_BISSAU, GUYANA, HAITI, HEARD_ISLAND_AND_MCDONALD_ISLANDS,
		     HONDURAS, HONG_KONG, HOWLAND_ISLAND, HUNGARY, ICELAND, INDIA };
		
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
			case INDIA:
				description = "India";
				break;
				
			case ICELAND:
				description = "Iceland";
				break;

			case HUNGARY:
				description = "Hungary";
				break;

			case HOWLAND_ISLAND:
				description = "Howland Island";
				break;

			case HONG_KONG:
				description = "Hong Kong";
				break;

			case HONDURAS:
				description = "Honduras";
				break;

			case HEARD_ISLAND_AND_MCDONALD_ISLANDS:
				description = "Heard Island and McDonald Islands";
				break;

			case HAITI:
				description = "Haiti";
				break;

			case GUYANA:
				description = "Guyana";
				break;

			case GUINEA_BISSAU:
				description = "Guinea- Bissau";
				break;

			case ANTIGUA_AND_BARBUDA:
				description = "Antigua and Barbuda";
				break;

			case GUINEA:
				description = "Guinea";
				break;

			case GUERNSEY:
				description = "Guernsey";
				break;

			case GUATEMALA:
				description = "Guatemala";
				break;

			case GUAM:
				description = "Guam";
				break;

			case GUADALOUPE:
				description = "Guadaloupe";
				break;

			case GRENADA:
				description = "Grenada";
				break;

			case GREENLAND:
				description = "Greenland";
				break;

			case GREECE:
				description = "Greece";
				break;

			case GLORIOSO_ISLANDS:
				description = "Glorioso Islands";
				break;

			case GIBRALTAR:
				description = "Gibraltar";
				break;

			case ANTARCTICA:
				description = "Antarctica";
				break;

			case GHANA:
				description = "Ghana";
				break;

			case GERMANY:
				description = "Germany";
				break;

			case GAZA_STRIP:
				description = "Gaza Strip";
				break;

			case GAMBIA_THE:
				description = "Gambia, The";
				break;

			case GABON:
				description = "Gabon";
				break;

			case FRENCH_SOUTHERN_AND_ANTARCTIC_ISLANDS:
				description = "French Southern and Antarctic Islands";
				break;

			case FRENCH_POLYNESIA:
				description = "French Polynesia";
				break;

			case FRENCH_GUIANA:
				description = "French Guiana";
				break;

			case FRANCE:
				description = "France";
				break;

			case FINLAND:
				description = "Finland";
				break;

			case ANGUILLA:
				description = "Anguilla";
				break;

			case FIJI:
				description = "Fiji";
				break;

			case FAROE_ISLANDS:
				description = "Faroe Islands";
				break;

			case FALKLAND_ISLANDS:
				description = "Falkland Islands";
				break;

			case EUROPA_ISLAND:
				description = "Europa Island";
				break;

			case ETHIOPIA:
				description = "Ethiopia";
				break;

			case EQUATORIAL_GUINEA:
				description = "Equatorial Guinea";
				break;

			case EL_SALVADOR:
				description = "El Salvador";
				break;

			case EGYPT:
				description = "Egypt";
				break;

			case ECUADOR:
				description = "Ecuador";
				break;

			case DOMINICAN_REPUBLIC:
				description = "Dominican Republic";
				break;

			case ANGOLA:
				description = "Angola";
				break;

			case DOMINICA:
				description = "Dominica";
				break;

			case DJIBOUTI:
				description = "Djibouti";
				break;

			case DENMARK:
				description = "Denmark";
				break;

			case CZECHOSLOVAKIA:
				description = "Czechoslovakia";
				break;

			case CYPRUS:
				description = "Cyprus";
				break;

			case CUBA:
				description = "Cuba";
				break;

			case COSTA_RICA:
				description = "Costa Rica";
				break;

			case CORAL_SEA_ISLANDS:
				description = "Coral Sea Islands";
				break;

			case COOK_ISLANDS:
				description = "Cook Islands";
				break;

			case CONGO_REPUBLIC_OF:
				description = "Congo, Republic of";
				break;

			case ANDORRA:
				description = "Andorra";
				break;

			case COMOROS:
				description = "Comoros";
				break;

			case COLOMBIA:
				description = "Colombia";
				break;

			case COCOS:
				description = "Cocos";
				break;

			case CHRISTMAS_ISLAND:
				description = "Christmas Island";
				break;

			case CHINA_PEOPLES_REPUBLIC_OF:
				description = "China, People's Republic of";
				break;

			case CHILE:
				description = "Chile";
				break;

			case CHAD:
				description = "Chad";
				break;

			case CENTRAL_AFRICAN_REPUBLIC:
				description = "Central African Republic";
				break;

			case CAYMAN_ISLANDS:
				description = "Cayman Islands";
				break;

			case CAPE_VERDE_REPUBLIC_OF:
				description = "Cape Verde, Republic of";
				break;

			case AMERICAN_SAMOA:
				description = "American Samoa";
				break;

			case CANADA:
				description = "Canada";
				break;

			case CAMEROON:
				description = "Cameroon";
				break;

			case CAMBODIA:
				description = "Cambodia";
				break;

			case BURUNDI:
				description = "Burundi";
				break;

			case BURMA:
				description = "Burma";
				break;

			case BURKINA:
				description = "Burkina";
				break;

			case BULGARIA:
				description = "Bulgaria";
				break;

			case BRUNEI:
				description = "Brunei";
				break;

			case BRITISH_VIRGIN_ISLANDS:
				description = "British Virgin Islands";
				break;

			case BRITISH_INDIAN_OCEAN_TERRITORY:
				description = "British Indian Ocean Territory";
				break;

			case ALGERIA:
				description = "Algeria";
				break;

			case BRAZIL:
				description = "Brazil";
				break;

			case BOUVET_ISLAND:
				description = "Bouvet Island";
				break;

			case BOTSWANA:
				description = "Botswana";
				break;

			case UZBEKISTAN:
				description = "Uzbekistan";
				break;

			case UKRAINE:
				description = "Ukraine";
				break;

			case TURKMENISTAN:
				description = "Turkmenistan";
				break;

			case TAJIKISTAN:
				description = "Tajikistan";
				break;

			case SLOVENIA:
				description = "Slovenia";
				break;

			case SERBIA_AND_MONTENEGRO:
				description = "Serbia and Montenegro";
				break;

			case RUSSIA:
				description = "Russia";
				break;

			case BOLIVIA:
				description = "Bolivia";
				break;

			case MONTENEGRO:
				description = "Montenegro";
				break;

			case MOLDOVA:
				description = "Moldova";
				break;

			case MIDWAY_ISLANDS:
				description = "Midway Islands";
				break;

			case MACEDONIA:
				description = "Macedonia";
				break;

			case LITHUANIA:
				description = "Lithuania";
				break;

			case LATVIA:
				description = "Latvia";
				break;

			case KYRGYZSTAN:
				description = "Kyrgyzstan";
				break;

			case KAZAKHSTAN:
				description = "Kazakhstan";
				break;

			case GEORGIA:
				description = "Georgia";
				break;

			case ESTONIA:
				description = "Estonia";
				break;

			case BHUTAN:
				description = "Bhutan";
				break;

			case CROATIA:
				description = "Croatia";
				break;

			case CLIPPERTON_ISLAND:
				description = "Clipperton Island";
				break;

			case BOSNIA_AND_HERCEGOVINA:
				description = "Bosnia and Hercegovina";
				break;

			case BELARUS:
				description = "Belarus";
				break;

			case AZERBAIJAN:
				description = "Azerbaijan";
				break;

			case ARMENIA:
				description = "Armenia";
				break;

			case ZIMBABWE:
				description = "Zimbabwe";
				break;

			case ZAMBIA:
				description = "Zambia";
				break;

			case ZAIRE:
				description = "Zaire";
				break;

			case BERMUDA:
				description = "Bermuda";
				break;

			case YEMEN:
				description = "Yemen";
				break;

			case WESTERN_SAMOA:
				description = "Western Samoa";
				break;

			case WEST_BANK:
				description = "West Bank";
				break;

			case WESTERN_SAHARA:
				description = "Western Sahara";
				break;

			case WALLIS_AND_FUTUNA:
				description = "Wallis and Futuna";
				break;

			case WAKE_ISLAND:
				description = "Wake Island";
				break;

			case VIRGIN_ISLANDS:
				description = "Virgin Islands";
				break;

			case VIETNAM:
				description = "Vietnam";
				break;

			case BENIN:
				description = "Benin";
				break;

			case VENEZUELA:
				description = "Venezuela";
				break;

			case VATICAN_CITY:
				description = "Vatican City";
				break;

			case VANUATU:
				description = "Vanuatu";
				break;

			case URUGUAY:
				description = "Uruguay";
				break;

			case UNITED_STATES:
				description = "United States";
				break;

			case UNITED_KINGDOM:
				description = "United Kingdom";
				break;

			case UNITED_ARAB_EMIRATES:
				description = "United Arab Emirates";
				break;

			case COMMONWEALTH_OF_INDEPENDENT_STATES:
				description = "Commonwealth of Independent States";
				break;

			case UGANDA:
				description = "Uganda";
				break;

			case TUVALU:
				description = "Tuvalu";
				break;

			case BELIZE:
				description = "Belize";
				break;

			case TURKS_AND_CAICOS_ISLANDS:
				description = "Turks and Caicos Islands";
				break;

			case TURKEY:
				description = "Turkey";
				break;

			case TUNISIA:
				description = "Tunisia";
				break;

			case PACIFIC_ISLANDS_TRUST_TERRITORY_OF_THE:
				description = "Pacific Islands, Trust Territory of the";
				break;

			case TROMELIN_ISLAND:
				description = "Tromelin Island";
				break;

			case TRINIDAD_AND_TOBAGO:
				description = "Trinidad and Tobago";
				break;

			case TONGA:
				description = "Tonga";
				break;

			case TOKELAU:
				description = "Tokelau";
				break;

			case TOGO:
				description = "Togo";
				break;

			case THAILAND:
				description = "Thailand";
				break;

			case BELGIUM:
				description = "Belgium";
				break;

			case TANZANIA:
				description = "Tanzania";
				break;

			case TAIWAN:
				description = "Taiwan";
				break;

			case SYRIA:
				description = "Syria";
				break;

			case SWITZERLAND:
				description = "Switzerland";
				break;

			case SWEDEN:
				description = "Sweden";
				break;

			case SWAZILAND:
				description = "Swaziland";
				break;

			case SVALBARD:
				description = "Svalbard";
				break;

			case SURINAME:
				description = "Suriname";
				break;

			case SUDAN:
				description = "Sudan";
				break;

			case SRI_LANKA:
				description = "Sri Lanka";
				break;

			case BASSAS_DA_INDIA:
				description = "Bassas da India";
				break;

			case ALBANIA:
				description = "Albania";
				break;

			case SPRATLY_ISLANDS:
				description = "Spratly Islands";
				break;

			case SPAIN:
				description = "Spain";
				break;

			case SOUTH_AFRICA:
				description = "South Africa";
				break;

			case SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS:
				description = "South Georgia and the South Sandwich Islands";
				break;

			case SOMALIA:
				description = "Somalia";
				break;

			case SOLOMON_ISLANDS:
				description = "Solomon Islands";
				break;

			case SINGAPORE:
				description = "Singapore";
				break;

			case SIERRA_LEONE:
				description = "Sierra Leone";
				break;

			case SEYCHELLES:
				description = "Seychelles";
				break;

			case SENEGAL:
				description = "Senegal";
				break;

			case BARBADOS:
				description = "Barbados";
				break;

			case SAUDI_ARABIA:
				description = "Saudi Arabia";
				break;

			case SAO_TOME_AND_PRINCIPE:
				description = "Sao Tome and Principe";
				break;

			case SAN_MARINO:
				description = "San Marino";
				break;

			case ST_VINCENT_AND_THE_GRENADINES:
				description = "St. Vincent and the Grenadines";
				break;

			case ST_PIERRE_AND_MIQUELON:
				description = "St. Pierre and Miquelon";
				break;

			case ST_LUCIA:
				description = "St. Lucia";
				break;

			case ST_HELENA:
				description = "St. Helena";
				break;

			case ST_KITTS_AND_NEVIS:
				description = "St. Kitts and Nevis";
				break;

			case RWANDA:
				description = "Rwanda";
				break;

			case ROMANIA:
				description = "Romania";
				break;

			case BANGLADESH:
				description = "Bangladesh";
				break;

			case REUNION:
				description = "Reunion";
				break;

			case QATAR:
				description = "Qatar";
				break;

			case PUERTO_RICO:
				description = "Puerto Rico";
				break;

			case PORTUGAL:
				description = "Portugal";
				break;

			case POLAND:
				description = "Poland";
				break;

			case PITCAIRN_ISLANDS:
				description = "Pitcairn Islands";
				break;

			case PHILIPPINES:
				description = "Philippines";
				break;

			case PERU:
				description = "Peru";
				break;

			case PARAGUAY:
				description = "Paraguay";
				break;

			case PARACEL_ISLANDS:
				description = "Paracel Islands";
				break;

			case BAKER_ISLAND:
				description = "Baker Island";
				break;

			case PAPUA_NEW_GUINEA:
				description = "Papua New Guinea";
				break;

			case PANAMA:
				description = "Panama";
				break;

			case PALMYRA_ATOLL:
				description = "Palmyra Atoll";
				break;

			case PAKISTAN:
				description = "Pakistan";
				break;

			case OMAN:
				description = "Oman";
				break;

			case NORWAY:
				description = "Norway";
				break;

			case NORTHERN_MARIANA_ISLANDS:
				description = "Northern Mariana Islands";
				break;

			case NORFOLK_ISLAND:
				description = "Norfolk Island";
				break;

			case NIUE:
				description = "Niue";
				break;

			case BAHRAIN:
				description = "Bahrain";
				break;

			case NIGERIA:
				description = "Nigeria";
				break;

			case NIGER:
				description = "Niger";
				break;

			case NICARAGUA:
				description = "Nicaragua";
				break;

			case NEW_ZEALAND:
				description = "New Zealand";
				break;

			case NEW_CALEDONIA:
				description = "New Caledonia";
				break;

			case NETHERLANDS_ANTILLES:
				description = "Netherlands Antilles";
				break;

			case NETHERLANDS:
				description = "Netherlands";
				break;

			case NEPAL:
				description = "Nepal";
				break;

			case NAVASSA_ISLAND:
				description = "Navassa Island";
				break;

			case NAURU:
				description = "Nauru";
				break;

			case BAHAMAS:
				description = "Bahamas";
				break;

			case NAMIBIA:
				description = "Namibia";
				break;

			case MOZAMBIQUE:
				description = "Mozambique";
				break;

			case MOROCCO:
				description = "Morocco";
				break;

			case MONTSERRAT:
				description = "Montserrat";
				break;

			case MONGOLIA:
				description = "Mongolia";
				break;

			case MONACO:
				description = "Monaco";
				break;

			case MICRONESIA_FEDERATIVE_STATES_OF:
				description = "Micronesia, Federative States of";
				break;

			case MEXICO:
				description = "Mexico";
				break;

			case MAYOTTE:
				description = "Mayotte";
				break;

			case MAURITIUS:
				description = "Mauritius";
				break;

			case AUSTRIA:
				description = "Austria";
				break;

			case MAURITANIA:
				description = "Mauritania";
				break;

			case MARTINIQUE:
				description = "Martinique";
				break;

			case MARSHALL_ISLANDS:
				description = "Marshall Islands";
				break;

			case MAN_ISLE_OF:
				description = "Man, Isle of";
				break;

			case MALTA:
				description = "Malta";
				break;

			case MALI:
				description = "Mali";
				break;

			case MALDIVES:
				description = "Maldives";
				break;

			case MALAYSIA:
				description = "Malaysia";
				break;

			case MALAWI:
				description = "Malawi";
				break;

			case MACAU:
				description = "Macau";
				break;

			case AUSTRALIA:
				description = "Australia";
				break;

			case MADAGASCAR:
				description = "Madagascar";
				break;

			case LUXEMBOURG:
				description = "Luxembourg";
				break;

			case LIECHTENSTEIN:
				description = "Liechtenstein";
				break;

			case LIBYA:
				description = "Libya";
				break;

			case LIBERIA:
				description = "Liberia";
				break;

			case LESOTHO:
				description = "Lesotho";
				break;

			case LEBANON:
				description = "Lebanon";
				break;

			case LAOS:
				description = "Laos";
				break;

			case KUWAIT:
				description = "Kuwait";
				break;

			case KOREA_REPUBLIC_OF:
				description = "Korea, Republic of";
				break;

			case ASHMORE_AND_CARTIER_ISLANDS:
				description = "Ashmore and Cartier Islands";
				break;

			case KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF:
				description = "Korea, Democratic People's Republic of";
				break;

			case KIRIBATI:
				description = "Kiribati";
				break;

			case KINGMAN_REEF:
				description = "Kingman Reef";
				break;

			case KENYA:
				description = "Kenya";
				break;

			case JUAN_DE_NOVA_ISLAND:
				description = "Juan de Nova Island";
				break;

			case JORDAN:
				description = "Jordan";
				break;

			case JOHNSTON_ATOLL:
				description = "Johnston Atoll";
				break;

			case JERSEY:
				description = "Jersey";
				break;

			case JARVIS_ISLAND:
				description = "Jarvis Island";
				break;

			case JAPAN:
				description = "Japan";
				break;

			case ARUBA:
				description = "Aruba";
				break;

			case JAN_MAYEN:
				description = "Jan Mayen";
				break;

			case JAMAICA:
				description = "Jamaica";
				break;

			case COTE_DIVOIRE:
				description = "Cote D'Ivoire";
				break;

			case ITALY:
				description = "Italy";
				break;

			case ISRAEL:
				description = "Israel";
				break;

			case IRELAND:
				description = "Ireland";
				break;

			case IRAQ:
				description = "Iraq";
				break;

			case IRAN:
				description = "Iran";
				break;

			case INDONESIA:
				description = "Indonesia";
				break;

			case ARGENTINA:
				description = "Argentina";
				break;

			case AFGHANISTAN:
				description = "Afghanistan";
				break;

			case OTHER:
				description = "Other";
				break;
		}

		return description;
	}
}
