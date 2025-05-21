package com.example.dojo.arabnumerals;

import org.junit.jupiter.api.Test;

import java.util.NavigableMap;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * probleme : https://codingdojo.org/kata/RomanNumerals/
 * wikipedia : https://en.wikipedia.org/wiki/Roman_numerals
 */
public class ChiffreArabeTest {

	@Test
	public void shouldReturnEmptyFor0() {
		//    when
		String result = ChiffreArabe.toRomans(0);
		//    then
		assertThat(result).isEmpty();
	}
	@Test
	public void shouldReturnIFor1() {
		//    when
		String result = ChiffreArabe.toRomans(1);
		//    then
		assertThat(result).isEqualTo("I");
	}
	@Test
	public void shouldReturnMultipleIsUntil3() {
		assertThat(ChiffreArabe.toRomans(2))
				.isEqualTo("II");
		assertThat(ChiffreArabe.toRomans(3))
				.isEqualTo("III");
	}

	@Test
	public void shouldReturnVFor5() {
		assertThat(ChiffreArabe.toRomans(5))
				.isEqualTo("V");
	}

	@Test
	public void shouldReturnIVFor4() {
		assertThat(ChiffreArabe.toRomans(4))
				.isEqualTo("IV");
	}

	@Test
	public void shouldReturnVFollowedByIsbetween5And8() {
		assertThat(ChiffreArabe.toRomans(6))
				.isEqualTo("VI");
		assertThat(ChiffreArabe.toRomans(7))
				.isEqualTo("VII");
		assertThat(ChiffreArabe.toRomans(8))
				.isEqualTo("VIII");
	}

	@Test
	public void shouldReturnIXFor9() {
		assertThat(ChiffreArabe.toRomans(9))
				.isEqualTo("IX");
	}

	@Test
	public void shouldReturnXFollowedByIsBetween10And13() {
		assertThat(ChiffreArabe.toRomans(10))
				.isEqualTo("X");
		assertThat(ChiffreArabe.toRomans(11))
				.isEqualTo("XI");
		assertThat(ChiffreArabe.toRomans(12))
				.isEqualTo("XII");
	}

	@Test
	public void shouldReturnXIVFor14() {
		assertThat(ChiffreArabe.toRomans(14))
				.isEqualTo("XIV");
	}
	@Test
	public void shouldReturnXVFor15() {
		assertThat(ChiffreArabe.toRomans(15))
				.isEqualTo("XV");
	}


	// tester : 4, 5-8, 9, 10-13, 14, 50, 100



	private static class ChiffreArabe {
		static NavigableMap<Integer, String> map = new TreeMap<>();
		static {
			map.put(1, "I");
			map.put(4, "IV");
			map.put(5, "V");
			map.put(9, "IX");
			map.put(10, "X");
		}

		public static String toRomans(int arabeNumber) {
			String result = "";
			if (arabeNumber == 0) return "";

			result = map.descendingKeySet().stream()
					.filter(order -> (arabeNumber >= order))
					.findFirst()
					.map(order -> transformWithSymbol(arabeNumber, map.get(order), order))
					.orElse("");
			return result;
		}

		private static String transformWithSymbol(int arabeNumber, String romanSymbol, Integer order) {
			return romanSymbol + repeatI(arabeNumber - order);
		}

		private static String repeatI(int n) {
			// iterer sur map.keys (descending), filter first key tl : n >= key; return value(key)
			return map.descendingKeySet().stream()
					.filter(key -> n >= key)
					.map(key -> {
						if (n == key) {
							return map.get(key);
						}
						return "I".repeat(n);
					})
					.findFirst().orElse("");
		}
	}
}
