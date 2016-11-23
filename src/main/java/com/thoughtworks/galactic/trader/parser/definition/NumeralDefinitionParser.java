package com.thoughtworks.galactic.trader.parser.definition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.galactic.trader.GalacticCalculator;

/**
 * Parses a string into galactic numeral definition.
 */
public class NumeralDefinitionParser {

	private static Pattern NUMBER_DEF_PATTERN = Pattern.compile("([a-z]+) is (I|V|X|L|C|D|M)");

	private final GalacticCalculator calculator;

	public NumeralDefinitionParser(GalacticCalculator calculator) {
		this.calculator = calculator;
	}

	/**
	 * Parses and stores the numeral definition into the calculator.
	 */
	public void parse(String definition) {
		Matcher matcher = NUMBER_DEF_PATTERN.matcher(definition);
		if (matcher.matches()) {
			calculator.defineGalacticNumeral(matcher.group(1), matcher.group(2));
		}
	}
}
