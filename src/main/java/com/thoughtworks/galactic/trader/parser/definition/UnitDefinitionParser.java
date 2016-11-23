package com.thoughtworks.galactic.trader.parser.definition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.galactic.trader.GalacticCalculator;

/**
 * Parses a string into product (Silver, Gold, etc) unit value definition.
 */
public class UnitDefinitionParser {

	private static Pattern UNIT_VALUE_DEF_PATTERN = Pattern
			.compile("([a-z ]+)([A-Z][a-z]*) is (\\d+) Credits");

	private final GalacticCalculator calculator;

	public UnitDefinitionParser(GalacticCalculator calculator) {
		this.calculator = calculator;
	}

	/**
	 * Parses and stores the unit definition into the calculator.
	 */
	public void parse(String definition) {
		Matcher matcher = UNIT_VALUE_DEF_PATTERN.matcher(definition);
		if (!matcher.matches()) {
			return;
		}

		// calculate unit value and store it in memory
		int quantity = calculator.calculateGalacticExpression(matcher.group(1).trim());
		if (quantity == 0) {
			throw new IllegalArgumentException("Unknown galactic number: " + matcher.group(1));
		}
		String unitName = matcher.group(2).trim();
		int totalValue = Integer.parseInt(matcher.group(3).trim());
		float unitValue = (float) totalValue / quantity;
		calculator.defineUnitValue(unitName, unitValue);
	}

}