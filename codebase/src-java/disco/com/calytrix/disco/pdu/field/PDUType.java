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
 * This field shall indicate the type of PDU that follows.
 */
public class PDUType
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------
	public static final short OTHER = 0;
	public static final short ENTITY_STATE = 1;
	public static final short FIRE = 2;
	public static final short DETONATION = 3;
	public static final short COLLISION = 4;
	public static final short SERVICE_REQUEST = 5;
	public static final short RESUPPLY_OFFER = 6;
	public static final short RESUPPLY_RECEIVED = 7;
	public static final short RESUPPLY_CANCEL = 8;
	public static final short REPAIR_COMPLETE = 9;
	public static final short REPAIR_RESPONSE = 10;
	public static final short CREATE_ENTITY = 11;
	public static final short REMOVE_ENTITY = 12;
	public static final short START_RESUME = 13;
	public static final short STOP_FREEZE = 14;
	public static final short ACKNOWLEDGE = 15;
	public static final short ACTION_REQUEST = 16;
	public static final short ACTION_RESPONSE = 17;
	public static final short DATA_QUERY = 18;
	public static final short SET_DATA = 19;
	public static final short DATA = 20;
	public static final short EVENT_REPORT = 21;
	public static final short MESSAGE = 22;
	public static final short ELECTROMAGNETIC_EMISSION = 23;
	public static final short DESIGNATOR = 24;
	public static final short TRANSMITTER = 25;
	public static final short SIGNAL = 26;
	public static final short RECEIVER = 27;
	
	public static final short ANNOUNCE_OBJECT = 129;
	public static final short DELETE_OBJECT = 130;
	public static final short DESCRIBE_APPLICATION = 131;
	public static final short DESCRIBE_EVENT = 132;
	public static final short DESCRIBE_OBJECT = 133;
	public static final short REQUEST_EVENT = 134;
	public static final short REQUEST_OBJECT = 135;
	
	public static final short TIME_SPACE_POSITION_INDICATOR_FI = 140;
	public static final short APPEARANCE_FI = 141;
	public static final short ARTICULATED_PARTS_FI = 142;
	public static final short FIRE_FI = 143;
	public static final short DETONATION_FI = 144;
	
	public static final short POINT_OBJECT_STATE = 150;
	public static final short LINEAR_OBJECT_STATE = 151;
	public static final short AREAL_OBJECT_STATE = 152;
	public static final short ENVIRONMENT = 153;
	public static final short TRANSFER_CONTROL_REQUEST = 155;
	public static final short TRANSFER_CONTROL = 156;
	public static final short TRANSFER_CONTROL_ACKNOWLEDGE = 157;
	
	public static final short INTERCOM_CONTROL = 160;
	public static final short INTERCOM_SIGNAL = 161;
	
	public static final short AGGREGATE = 170;
		
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
	 * @return An short[] containing the ordered set of values that this 
	 * enumeration field can assume
	 */
	public static short[] getValues()
	{
		short[] values = { OTHER, 
		                   ENTITY_STATE, 
		                   FIRE, 
		                   DETONATION, 
		                   COLLISION,
		                   SERVICE_REQUEST, 
		                   RESUPPLY_OFFER, 
		                   RESUPPLY_RECEIVED,
		                   RESUPPLY_CANCEL, 
		                   REPAIR_COMPLETE, 
		                   REPAIR_RESPONSE,
		                   CREATE_ENTITY, 
		                   REMOVE_ENTITY, 
		                   START_RESUME,
		                   STOP_FREEZE, 
		                   ACKNOWLEDGE, 
		                   ACTION_REQUEST,
		                   ACTION_RESPONSE, 
		                   DATA_QUERY,
		                   SET_DATA,
		                   DATA,
		                   EVENT_REPORT,
		                   MESSAGE,
		                   ELECTROMAGNETIC_EMISSION,
		                   DESIGNATOR,
		                   TRANSMITTER,
		                   SIGNAL,
		                   RECEIVER,
		                   ANNOUNCE_OBJECT,
		                   DELETE_OBJECT,
		                   DESCRIBE_APPLICATION,
		                   DESCRIBE_EVENT,
		                   DESCRIBE_OBJECT,
		                   REQUEST_EVENT,
		                   REQUEST_OBJECT,
		                   TIME_SPACE_POSITION_INDICATOR_FI,
		                   APPEARANCE_FI,
		                   ARTICULATED_PARTS_FI,
		                   FIRE_FI,
		                   DETONATION_FI,
		                   POINT_OBJECT_STATE,
		                   LINEAR_OBJECT_STATE,
		                   AREAL_OBJECT_STATE,
		                   ENVIRONMENT,
		                   TRANSFER_CONTROL_REQUEST,
		                   TRANSFER_CONTROL,
		                   TRANSFER_CONTROL_ACKNOWLEDGE,
		                   INTERCOM_CONTROL,
		                   INTERCOM_SIGNAL,
		                   AGGREGATE };
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
		String description = "Other";
		
		switch ( value )
		{
			case ENTITY_STATE:
				description = "Entity State";
				break;
				
			case FIRE:
				description = "Fire";
				break;
				
			case DETONATION:
				description = "Detonation";
				break;
				
			case COLLISION:
				description = "Collision";
				break;
				
			case SERVICE_REQUEST:
				description = "Service Request";
				break;
				
			case RESUPPLY_OFFER:
				description = "Resupply Offer";
				break;
				
			case RESUPPLY_RECEIVED:
				description = "Resupply Received";
				break;
				
			case RESUPPLY_CANCEL:
				description = "Resupply Cancel";
				break;
				
			case REPAIR_COMPLETE:
				description = "Repair Complete";
				break;
				
			case REPAIR_RESPONSE:
				description = "Repair Response";
				break;
				
			case CREATE_ENTITY:
				description = "Create Entity";
				break;
				
			case REMOVE_ENTITY:
				description = "Remove Entity";
				break;
            
			case START_RESUME:
				description = "Start/Resume";
				break;
            
			case STOP_FREEZE:
				description = "Stop/Freeze";
				break;
            
			case ACKNOWLEDGE:
				description = "Acknowledge";
				break;
			
			case ACTION_REQUEST:
				description = "Action Request";
				break;
				
			case ACTION_RESPONSE:
				description = "Action Response";
				break;
				
			case DATA_QUERY:
				description = "Data Query";
				break;
				
			case SET_DATA:
				description = "Set Data";
				break;
            
			case DATA:
				description = "Data";
				break;
            
			case EVENT_REPORT:
				description = "Event Report";
				break;
            
			case MESSAGE:
				description = "Message";
				break;
            
			case ELECTROMAGNETIC_EMISSION:
				description = "Electromagnetic Emission";
				break;
				
			case DESIGNATOR:
				description = "Designator";
				break;
			
			case TRANSMITTER:
				description = "Transmitter";
				break;
				
			case SIGNAL:
				description = "Signal";
				break;
				
			case RECEIVER:
				description = "Receiver";
				break;
				
			case ANNOUNCE_OBJECT:
				description = "Announce Object";
				break;
				
			case DELETE_OBJECT:
				description = "Delete Object";
				break;
				
			case DESCRIBE_APPLICATION:
				description = "Describe Application";
				break;
			
			case DESCRIBE_EVENT:
				description = "Describe Event";
				break;
				
			case DESCRIBE_OBJECT:
				description = "Describe Object";
				break;
				
			case REQUEST_EVENT:
				description = "Request Event";
				break;
            
			case REQUEST_OBJECT:
				description = "Request Object";
				break;
				
			case TIME_SPACE_POSITION_INDICATOR_FI:
				description = "Time Space Position Indicator - FI";
				break;
            
			case APPEARANCE_FI:
				description = "Appearance - FI";
				break;
            
			case ARTICULATED_PARTS_FI:
				description = "Fire - FI";
				break;
            
			case DETONATION_FI:
				description = "Detonation - FI";
				break;
            
			case POINT_OBJECT_STATE:
				description = "Point Object State";
				break;
				
			case LINEAR_OBJECT_STATE:
				description = "Linear Object State";
				break;
				
			case AREAL_OBJECT_STATE:
				description = "Areal Object State";
				break;
				
			case ENVIRONMENT:
				description = "Environment";
				break;
				
			case TRANSFER_CONTROL_REQUEST:
				description = "Transfer Control Request";
				break;
				
			case TRANSFER_CONTROL:
				description = "Transfer Control";
				break;
				
			case TRANSFER_CONTROL_ACKNOWLEDGE:
				description = "Transfer Control Acknowledge";
				break;
				
			case INTERCOM_CONTROL:
				description = "Intercom Control";
				break;
				
			case INTERCOM_SIGNAL:
				description = "Intercom Signal";
				break;
				
			case AGGREGATE:
				description = "Aggregate";
				break;
		}
		
		return description;
	}
}
