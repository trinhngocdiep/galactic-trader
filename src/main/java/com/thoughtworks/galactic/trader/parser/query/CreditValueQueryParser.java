package com.thoughtworks.galactic.trader.parser.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.galactic.trader.GalacticCalculator;

/**
 * Answers query about credit value of product, such as "how many Credits is glob prok Silver ?"
 */
public class CreditValueQueryParser {

	private static Pattern QUERY_PATTERN = Pattern.compile("how many Credits is ([a-z ]+) ([A-Z][a-z]+) \\?");
	private static final String ANSWER_FORMAT = "%s %s is %s Credits";

	private final GalacticCalculator calculator;

	public CreditValueQueryParser(GalacticCalculator calculator) {
		this.calculator = calculator;
	}

	public String parse(String query) {
		Matcher matcher = QUERY_PATTERN.matcher(query);
		if (!matcher.matches()) {
			return null;
		}
		String galacticNumber = matcher.group(1);
		String unitName = matcher.group(2);
		int quantity = calculator.calculateGalacticExpression(galacticNumber);
		float unitValue = calculator.getUnitValue(unitName);

		return String.format(ANSWER_FORMAT, galacticNumber, unitName, (int) (quantity * unitValue));
	}

}
