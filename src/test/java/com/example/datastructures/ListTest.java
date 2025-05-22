package com.example.datastructures;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListTest {

	@Test
	public void testLinkedListPopAndPoll() {
		//    when
		LinkedList<String> list = new LinkedList<>(List.of("first", "second"));
		LinkedList<String> otherlist = new LinkedList<>(List.of("first", "second"));
		//    then
		assertThat(list.poll())
				.isEqualTo("first");
		assertThat(otherlist.poll())
				.isEqualTo("first");
	}
}
