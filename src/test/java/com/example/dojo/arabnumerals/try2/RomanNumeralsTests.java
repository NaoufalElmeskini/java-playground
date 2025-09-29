package com.example.dojo.arabnumerals.try2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralsTests {



	/*
	1 -> "I",
	2 -> "II",
	3 -> "III",
	4 -> "IV",
	 */
	@ParameterizedTest
	@DisplayName("should XXX when I YYY")
	@CsvSource({
			"1, I",
			"2, II",
			"3, III",
			"4, IV",
			"5, V",
			"6, VI",
			"7, VII",
			"8, VIII",
			"9, IX",
			"10, X",
			"11, XI",
			"14, XIV",
	})

	public void whenITryToConvertFrom1(int input, String output) {
		//    when
		//    then
		assertThat(RomanNumerals.fromArabNumeral(input))
				.isEqualTo(output);
	}


}
