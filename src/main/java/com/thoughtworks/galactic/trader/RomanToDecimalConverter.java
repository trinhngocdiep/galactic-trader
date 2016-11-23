package com.thoughtworks.galactic.trader;

public class RomanToDecimalConverter {

	// Standard roman numbers contains at most 4 sections in correct order:
	// section (3000-1000): M, MM, MMM => M{0,3}
	// section (800-100): CM, DCCC, DCC, DC, D, CD, CCC, CC, C
	// section (90-10): XC, LXXX, LXX, LX, L, XL, XXX, XX, X
	// section (9-1): IX, VIII, VII, VI, V, IV, III, II, I
	// TODO: this regex can be simplified
	private static final String ROMAN_EXP = "M{0,3}(CM|DCCC|DCC|DC|D|CD|CCC|CC|C)?(XC|LXXX|LXX|LX|L|XL|XXX|XX|X)?(IX|VIII|VII|VI|V|IV|III|II|I)?";

	public int toDecimals(String romanNumber) {
		if (romanNumber == null || romanNumber.isEmpty() || !romanNumber.matches(ROMAN_EXP)) {
			throw new NumberFormatException("Invalid roman number: " + romanNumber);
		}

		int total = 0;
		int currentValue = 0;
		char currentChar;
		char nextChar;
		int nextValue = 0;

		for (int i = 0; i < romanNumber.length(); i++) {
			currentChar = romanNumber.charAt(i);
			currentValue = RomanLiterals.toDemical(currentChar);

			if (i == romanNumber.length() - 1) {
				return total + currentValue;
			}

			nextChar = romanNumber.charAt(i + 1);
			nextValue = RomanLiterals.toDemical(nextChar);

			if (currentValue < nextValue) {
				total += (nextValue - currentValue);
				i++;
			} else {
				total += currentValue;
			}
		}
		return total;
	}

	private enum RomanLiterals {
		I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

		private final int decimal;

		private RomanLiterals(int decimal) {
			this.decimal = decimal;
		}

		static int toDemical(char c) {
			for (RomanLiterals literal : values()) {
				if (literal.name().equals(String.valueOf(c))) {
					return literal.decimal;
				}
			}
			throw new NumberFormatException("Unknown roman literal " + c);
		}
	}

}