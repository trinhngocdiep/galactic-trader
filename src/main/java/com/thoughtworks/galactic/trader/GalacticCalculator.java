package com.thoughtworks.galactic.trader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stores galactic numeral definition and product unit definition to provide
 * methods for calculating galactic expression.
 */
public class GalacticCalculator {

	private Map<String, String> numberDefs = new HashMap<>();
	private Map<String, Float> unitDefs = new HashMap<>();
	private final RomanToDecimalConverter romanConverter;

	public GalacticCalculator(RomanToDecimalConverter romanConverter) {
		this.romanConverter = romanConverter;
	}

	public void defineGalacticNumeral(String galacticNumeral, String romanNumeral) {
		numberDefs.put(galacticNumeral, romanNumeral);
	}

	public void defineUnitValue(String unit, float value) {
		unitDefs.put(unit, value);
	}

	public float getUnitValue(String unit) {
		return unitDefs.get(unit);
	}

	public int calculateGalacticExpression(String galacticNumber) {
		String romanNumber = toRomanNumber(galacticNumber);
		return romanConverter.toDecimals(romanNumber);
	}

	String toRomanNumber(String galacticNumber) {
		List<String> galacticNumerals = Arrays.asList(galacticNumber.split(" "));

		galacticNumerals.forEach(e -> {
			if (null == getRomanNumeral(e)) {
				throw new IllegalArgumentException("Undefined galactic numeral: " + e);
			}
		});

		return galacticNumerals.stream().map(this::getRomanNumeral).collect(Collectors.joining());
	}

	String getRomanNumeral(String galacticNumeral) {
		return numberDefs.get(galacticNumeral);
	}
}
