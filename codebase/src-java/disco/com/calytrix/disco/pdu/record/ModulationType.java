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
package com.calytrix.disco.pdu.record;

import java.io.IOException;

import com.calytrix.disco.network.DISInputStream;
import com.calytrix.disco.network.DISOutputStream;
import com.calytrix.disco.pdu.IPDUComponent;
import com.calytrix.disco.pdu.field.MajorModulationType;
import com.calytrix.disco.util.DISSizes;

/**
 * Information about the type of modulation used for radio transmission shall be 
 * represented by a Modulation Type Record. This record uniquely identifies the 
 * various sets of signal parameters (i.e. the modulation type) that are used to 
 * determine whether two radios may interoperate. The modulation is 
 * characterized in a generic fashion by the spread spectrum, major modulation 
 * type, and detail fields. The classes of interoperable modulation types are 
 * enumerated by the System field. This record shall specify the spread-spectrum 
 * usage, major modulation type, detailed information, and system compatibility. 
 * The fields for this record are described in the paragraphs that follow.
 *
 * <ol>
 * 	<li><b>Spread Spectrum</b> - This field shall indicate the spread spectrum 
 * 	technique or combination of spread spectrum techniques in use. The Spread 
 * 	Spectrum field shall consist of a 16 element boolean array. Each independent 
 * 	type of spread spectrum technique shall be represented by a single element 
 * 	of this array. If a particular spread spectrum technique is in use, the 
 * 	corresponding array element shall be set to one, otherwise it shall be set 
 * 	to zero. All unused array elements shall be set to zero. The supported 
 * 	spread spectrum techniques and their assignment to elements of the 16 
 * 	element array are defined in Section 9 of EBV-DOC.
 * 	<table>
 * 		<tr>
 * 			<th>Bits 3-15</th>
 * 			<th>Bit 2</th>
 * 			<th>Bit 1</th>
 * 			<th>Bit 0</th>
 * 		</tr>
 * 		<tr>
 * 			<td>TBD</td>
 * 			<td>Time Hop</td>
 * 			<td>Pseudo Noise</td>
 * 			<td>Frequency Hop</td>
 * 		</tr>
 * 	</table></li>
 * 	<li><b>Major Modulation Type</b> - This field shall specify the major 
 * 	classification of the modulation type. This field shall be represented by a 
 * 	16-bit enumeration (see Section 9 in EBV-DOC).</li>
 * 	<li><b>Detail</b> This field shall provide certain detailed information 
 * 	depending upon the Major Modulation Type. This field shall be represented by 
 * 	a 16-bit enumeration (see Section 9 in EBV-DOC).</li>
 * 	<li><b>System</b> - This field shall specify the interpretation of the 
 * 	modulation parameter field(s) in the Transmitter PDU. This field shall be 
 * 	represented by a 16-bit enumeration (see Section 9 in EBV-DOC).</li>
 * </ol>
 * 
 *  @see "Section 9 in EBV-DOC"
 */
public class ModulationType implements IPDUComponent, Cloneable
{
	//----------------------------------------------------------
	//                    STATIC VARIABLES
	//----------------------------------------------------------

	//----------------------------------------------------------
	//                   INSTANCE VARIABLES
	//----------------------------------------------------------
	private int spreadSpectrum;
	private int majorModulationType;
	private int detail;
	private int system;
	
	//----------------------------------------------------------
	//                      CONSTRUCTORS
	//----------------------------------------------------------
	public ModulationType()
	{
		this( 0, MajorModulationType.OTHER, 0, 0 );
	}
	
	public ModulationType( int spreadSpectrum,
	                       int majorModulationType, 
	                       int detail,
	                       int system )
	{
		this.spreadSpectrum = spreadSpectrum;
		this.majorModulationType = majorModulationType;
		this.detail = detail;
		this.system = system;
	}
	
	//----------------------------------------------------------
	//                    INSTANCE METHODS
	//----------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals( Object other )
	{
		if( other == this )
			return true;
		
		if( other instanceof ModulationType )
		{
			ModulationType asModulationType = (ModulationType)other;
			if( asModulationType.spreadSpectrum == this.spreadSpectrum &&
				asModulationType.majorModulationType == this.majorModulationType &&
				asModulationType.detail == this.detail &&
				asModulationType.system == this.system )
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ModulationType clone()
	{
		return new ModulationType( spreadSpectrum, majorModulationType, detail, system );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void read( DISInputStream dis ) throws IOException
    {
		spreadSpectrum = dis.readUI16();
		majorModulationType = dis.readUI16(); 
        detail = dis.readUI16();
        system = dis.readUI16();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public void write( DISOutputStream dos ) throws IOException
    {
		dos.writeUI16( spreadSpectrum );
		dos.writeUI16( majorModulationType );
		dos.writeUI16( detail );
		dos.writeUI16( system );
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getByteLength()
	{
		return DISSizes.UI16_SIZE * 4;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Accessor and Mutator Methods ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	public int getSpreadSpectrum()
	{
		return spreadSpectrum;
	}
	
	public void setSpreadSpectrum( int spreadSpectrum )
	{
		this.spreadSpectrum = spreadSpectrum;
	}
	
	public int getMajorModulationType()
	{
		return majorModulationType;
	}
	
	public void setMajorModulationType( int majorModulationType )
	{
		this.majorModulationType = majorModulationType;
	}
	
	public int getDetail()
	{
		return detail;
	}
	
	public void setDetail( int detail )
	{
		this.detail = detail;
	}
	
	public int getSystem()
	{
		return system;
	}
	
	public void setSystem( int system )
	{
		this.system = system;
	}

	//----------------------------------------------------------
	//                     STATIC METHODS
	//----------------------------------------------------------
}
