package com.thoughtworks.galactic.trader.parser.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.galactic.trader.GalacticCalculator;

/**
 * Answers query about galactic number value, such as "how much is pish tegj glob glob ?"
 */
public class GalacticNumberQueryParser {

	private static Pattern QUERY_PATTERN = Pattern.compile("how much is ([a-z ]+) \\?");
	private static final String ANSWER_FORMAT = "%s is %s";
	private final GalacticCalculator calculator;

	public GalacticNumberQueryParser(GalacticCalculator calculator) {
		this.calculator = calculator;
	}

	public String parse(String query) {
		Matcher matcher = QUERY_PATTERN.matcher(query);
		if (!matcher.matches()) {
			return null;
		}
		String galacticNumber = matcher.group(1);
		int decimalNumber = calculator.calculateGalacticExpression(galacticNumber);
		if (decimalNumber == 0) {
			return null;
		}

		return String.format(ANSWER_FORMAT, galacticNumber, decimalNumber);
	}

}
