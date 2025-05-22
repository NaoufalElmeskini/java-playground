package com.example.dojo.arabnumerals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * 1 -> "I", 2 -> "II"...
 * nom : toNombreRomain(Integer) : String
 */
public class RomanNumeralsTest {

	@ParameterizedTest
	@CsvSource({
			"1, I",
			"2, II",
			"3, III",
			"4, IV",
			"5, V",
			"6, VI",
			"7, VII",
			"9, IX",
	})
	public void shouldReturnI(int input, String attendu) {
		//    when

		//    then
		assertThat(Converter.toNombreRomain(input))
				.isEqualTo(attendu);
	}

	@Test
	public void shouldReturnEmptyFor0() {
		//    when
		//    then
		assertThat(Converter.toNombreRomain(0))
				.isEqualTo("");
	}

}
