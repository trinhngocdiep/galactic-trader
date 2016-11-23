package com.thoughtworks.galactic.trader.parser.query;

import com.thoughtworks.galactic.trader.GalacticCalculator;

/**
 * Answer all queries about galactic trading.
 */
public class QueryParser {

	public static final String ANSWER_UNKNOWN = "I have no idea what you are talking about";
	private GalacticNumberQueryParser galacticNumberQueryParser;
	private CreditValueQueryParser creditValueQueryParser;

	public QueryParser(GalacticCalculator registry) {
		galacticNumberQueryParser = new GalacticNumberQueryParser(registry);
		creditValueQueryParser = new CreditValueQueryParser(registry);
	}

	public boolean isQuery(String query) {
		return query != null && query.startsWith("how ");
	}

	public String parse(String query) {
		String answer = galacticNumberQueryParser.parse(query);
		if (null == answer) {
			answer = creditValueQueryParser.parse(query);
		}

		if (null == answer) {
			return ANSWER_UNKNOWN;
		}

		return answer;
	}

}
