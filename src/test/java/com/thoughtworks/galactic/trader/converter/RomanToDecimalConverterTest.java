package com.thoughtworks.galactic.trader.converter;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.galactic.trader.RomanToDecimalConverter;

public class RomanToDecimalConverterTest {

	private RomanToDecimalConverter converter = new RomanToDecimalConverter();
	
	@Test
	public void testValid() {
		Assert.assertEquals(1, testConvert("I"));
		Assert.assertEquals(5, testConvert("V"));
		Assert.assertEquals(30, testConvert("XXX"));
		Assert.assertEquals(16, testConvert("XVI"));
		Assert.assertEquals(1500, testConvert("MD"));
		Assert.assertEquals(3000, testConvert("MMM"));
		Assert.assertEquals(220, testConvert("CCXX"));
		Assert.assertEquals(42, testConvert("XLII"));
		Assert.assertEquals(19, testConvert("XIX"));
		Assert.assertEquals(13, testConvert("XIII"));
		Assert.assertEquals(1004, testConvert("MIV"));
		Assert.assertEquals(1599, testConvert("MDXCIX"));
		Assert.assertEquals(3999, testConvert("MMMCMXCIX"));
		Assert.assertEquals(2006, testConvert("MMVI"));
		Assert.assertEquals(1944, testConvert("MCMXLIV"));
	}

	@Test
	public void testInvalid() {
		Assert.assertEquals(0, testConvert("AAA"));
		Assert.assertEquals(0, testConvert("XA"));
		Assert.assertEquals(0, testConvert("AX"));
		Assert.assertEquals(0, testConvert("MAX"));
		Assert.assertEquals(0, testConvert("MVX"));
		Assert.assertEquals(0, testConvert("CIXD"));
		Assert.assertEquals(0, testConvert("MIXL"));
		Assert.assertEquals(0, testConvert("XIIV"));
		Assert.assertEquals(0, testConvert("XIIIIV"));
		Assert.assertEquals(0, testConvert("XIM"));
		Assert.assertEquals(0, testConvert("MMMM"));
		Assert.assertEquals(0, testConvert("MXMXMXMXMXMXM"));
		Assert.assertEquals(0, testConvert(""));
	}

	private int testConvert(String value) {
		// try catch here so we dont have to write many testInvalid methods
		try {
			return converter.toDecimals(value);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
}