package com.example.dojo.arabnumerals;

import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;

public class Converter {
	private static final NavigableMap<Integer, String> symbolMap = new TreeMap<>();

	static {
		symbolMap.put(1, "I");
		symbolMap.put(4, "IV");
		symbolMap.put(5, "V");
		symbolMap.put(9, "IX");
	}

	public static String toNombreRomain(int arabNumber) {
		if(arabNumber == 0) {
			return "";
		}

		return symbolMap.descendingKeySet().stream()
				.filter(key -> arabNumber >= key)
				.map(key -> symbolWithIs(symbolMap.get(key), arabNumber-key))
				.findFirst()
				.orElseThrow();

	}

	private static String symbolWithIs(String symbol, int count) {
		return symbol + "I".repeat(count);
	}

}
