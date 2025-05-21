package com.example.dojo.stringcalculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * probleme : voire StringCalculatorKata.pdf
 */
class StringCalculatorTest {
	@Test
	public void testCasEmpty() {

		//    then
		assertThat(StringCalculator.add(""))
				.isEqualTo(0);
	}
	@Test
	public void testCasNumber1() {

		//    then
		assertThat(StringCalculator.add("1"))
				.isEqualTo(1);
		assertThat(StringCalculator.add("2"))
				.isEqualTo(2);
	}

	@Test
	public void testCasNumber2() {

		//    then
		assertThat(StringCalculator.add("1,2"))
				.isEqualTo(3);
		assertThat(StringCalculator.add("1,5"))
				.isEqualTo(6);
	}

	@Test
	public void testCasNumberUnknownAmountOfNumbers() {

		//    then
		assertThat(StringCalculator.add("1,2,3,4,5,6,7,8,9,10"))
				.isEqualTo(55);
	}


	@Test
	public void testCasNumberWithLineSeparator() {

		//    then
		assertThat(StringCalculator.add("1\n2"))
				.isEqualTo(3);
	}
	@Test
	public void testCasNumberWithLineAndComaSeparator() {

		//    then
		assertThat(StringCalculator.add("1\n2,3"))
				.isEqualTo(6);
	}

	public class StringCalculator {

		private static final String SEPARATOR_COMA = ",";
		private static final String SEPARATOR_LINE = "\n";

		public static int add(String numbers) {
			if(numbers.isEmpty()) return 0;

			List<Integer> operandes = extractOperandes(numbers);
			return operandes.stream()
					.reduce(0, Integer::sum);
		}

		private static List<Integer> extractOperandes(String numbers) {
			List<String> splitNumbers = splitGivenSeparators(numbers, SEPARATOR_COMA, SEPARATOR_LINE);
			return splitNumbers.stream()
					.map(Integer::valueOf)
					.toList();
		}

		private static List<String> splitGivenSeparators(String numbers, String... separatorArray) {
			if (separatorArray.length == 0) return List.of(numbers);

			List<String> separators = List.of(separatorArray[0], separatorArray[1]);
			return applySeparation(numbers, separators.get(0)).stream()
					.flatMap(unseparated -> applySeparation(unseparated, separators.get(1)).stream())
					.toList();
		}

		private static List<String> applySeparation(String text, String sep) {
			return Arrays.stream(text.split(sep)).toList();
		}
	}


}