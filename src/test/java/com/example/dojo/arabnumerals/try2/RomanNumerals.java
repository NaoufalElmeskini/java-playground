package com.example.dojo.arabnumerals.try2;

import java.util.NavigableMap;
import java.util.TreeMap;

public class RomanNumerals {
	public static String fromArabNumeral(int i) {
		NavigableMap<Integer, String> symbolMap = new TreeMap<>();
		symbolMap.put(14, "XIV");
		symbolMap.put(10, "X");
		symbolMap.put(9, "IX");
		symbolMap.put(5, "V");
		symbolMap.put(4, "IV");
		symbolMap.put(1, "I");

		return symbolMap.descendingKeySet().stream()
				.filter(key -> i >= key).findFirst()
				.map(key -> symbolMap.get(key) + "I".repeat(i - key)).orElse("");
	}
}
