package com.thoughtworks.galactic.trader.parser.definition;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.thoughtworks.galactic.trader.GalacticCalculator;
import com.thoughtworks.galactic.trader.parser.definition.UnitDefinitionParser;

public class UnitDefinitionParserTest {

	private GalacticCalculator calculator;
	private UnitDefinitionParser parser;

	@Before
	public void setup() {
		calculator = Mockito.mock(GalacticCalculator.class);
		parser = new UnitDefinitionParser(calculator);
	}

	@Test
	public void testParseValid1() {
		Mockito.when(calculator.calculateGalacticExpression("glob")).thenReturn(1);
		parser.parse("glob Silver is 34 Credits");
		Mockito.verify(calculator).defineUnitValue("Silver", 34);
	}

	@Test
	public void testParseValid2() {
		Mockito.when(calculator.calculateGalacticExpression("glob glob")).thenReturn(2);
		parser.parse("glob glob Silver is 34 Credits");
		Mockito.verify(calculator).defineUnitValue("Silver", 17);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseInvalidNumber() {
		parser.parse("blah Silver is 34 Credits");
	}
	
	@Test
	public void testParseInvalidUnitName() {
		parser.parse("blah notAUnitName is 34 Credits");
		Mockito.verifyZeroInteractions(calculator);
	}

}