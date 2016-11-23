package com.thoughtworks.galactic.trader.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.thoughtworks.galactic.trader.GalacticCalculator;
import com.thoughtworks.galactic.trader.RomanToDecimalConverter;
import com.thoughtworks.galactic.trader.parser.definition.NumeralDefinitionParser;
import com.thoughtworks.galactic.trader.parser.definition.UnitDefinitionParser;
import com.thoughtworks.galactic.trader.parser.query.QueryParser;

/**
 * Main entry class to run the program.
 */
public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Please provide the absolute path to 1 input text file!");
			return;
		}

		Path inputFilePath = Paths.get(args[0]);
		if (!Files.exists(inputFilePath)) {
			System.out.println("File not found: " + inputFilePath);
			return;
		}
		System.out.println("Reading input from file: " + inputFilePath);
		System.out.println("--------------------------");

		GalacticCalculator calculator = new GalacticCalculator(new RomanToDecimalConverter());
		NumeralDefinitionParser numeralDefinitionParser = new NumeralDefinitionParser(calculator);
		UnitDefinitionParser unitDefinitionParser = new UnitDefinitionParser(calculator);
		QueryParser queryParser = new QueryParser(calculator);
		List<String> input = Files.readAllLines(inputFilePath, StandardCharsets.UTF_8);

		input.forEach(line -> {
			numeralDefinitionParser.parse(line);
			unitDefinitionParser.parse(line);
			if (queryParser.isQuery(line)) {
				System.out.println(queryParser.parse(line));
			}
		});
	}

}
