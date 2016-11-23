package com.thoughtworks.galactic.trader.parser.query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.thoughtworks.galactic.trader.GalacticCalculator;
import com.thoughtworks.galactic.trader.parser.query.GalacticNumberQueryParser;

public class GalacticNumberQueryParserTest {

	private GalacticCalculator calculator;
	private GalacticNumberQueryParser parser;

	@Before
	public void setup() {
		calculator = Mockito.mock(GalacticCalculator.class);
		parser = new GalacticNumberQueryParser(calculator);
	}

	@Test
	public void testParseValidQueryAboutGalacticExpression() {
		Mockito.when(calculator.calculateGalacticExpression("glob")).thenReturn(1);
		Assert.assertEquals("glob is 1", parser.parse("how much is glob ?"));

		Mockito.when(calculator.calculateGalacticExpression("glob blob")).thenReturn(9);
		Assert.assertEquals("glob blob is 9", parser.parse("how much is glob blob ?"));
	}

	@Test
	public void testParseInvalidQueryAboutGalacticExpression() {
		Assert.assertEquals(null, parser.parse("how much is blah ?"));
	}
}
