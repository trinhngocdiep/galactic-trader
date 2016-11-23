package com.thoughtworks.galactic.trader.parser.definition;

import org.junit.Test;
import org.mockito.Mockito;

import com.thoughtworks.galactic.trader.GalacticCalculator;
import com.thoughtworks.galactic.trader.parser.definition.NumeralDefinitionParser;

public class NumberDefinitionParserTest {

	private GalacticCalculator calculator = Mockito.mock(GalacticCalculator.class);
	private NumeralDefinitionParser parser = new NumeralDefinitionParser(calculator);

	@Test
	public void testParse() {
		parser.parse("glob is I");
		Mockito.verify(calculator).defineGalacticNumeral("glob", "I");
		
		parser.parse("pish is X");
		Mockito.verify(calculator).defineGalacticNumeral("pish", "X");
		
		parser.parse("invalid exp");
		Mockito.verifyZeroInteractions(calculator);
	}

}