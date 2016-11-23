package com.thoughtworks.galactic.trader.parser.query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.thoughtworks.galactic.trader.GalacticCalculator;
import com.thoughtworks.galactic.trader.parser.query.CreditValueQueryParser;

public class CreditValueQueryParserTest {

	private GalacticCalculator calculator;
	private CreditValueQueryParser parser;

	@Before
	public void setup() {
		calculator = Mockito.mock(GalacticCalculator.class);
		parser = new CreditValueQueryParser(calculator);
	}

	@Test
	public void testParseValidQueryAboutCreditValue() {
		Mockito.when(calculator.getUnitValue("Silver")).thenReturn(10f);
		Mockito.when(calculator.calculateGalacticExpression("glob")).thenReturn(1);
		Assert.assertEquals("glob Silver is 10 Credits", parser.parse("how many Credits is glob Silver ?"));

		Mockito.when(calculator.calculateGalacticExpression("glob prok")).thenReturn(4);
		Assert.assertEquals("glob prok Silver is 40 Credits", parser.parse("how many Credits is glob prok Silver ?"));
	}

}
