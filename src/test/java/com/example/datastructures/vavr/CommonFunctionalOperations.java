package com.example.datastructures.vavr;

import io.vavr.API;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.vavr.collection.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonFunctionalOperations {

    @Test
    public void testListOptional() {
        List<Optional<String>> dictionnary = List.of(
                Optional.of("zero"),
                Optional.of("one"),
                Optional.of("two"),
                Optional.ofNullable(null));

        List<String> collected = dictionnary.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertEquals(3, collected.size());
        assertEquals("ZERO", collected.get(0));
    }

    @Test
    public void testOptionalMap() {
        Option<String> option = Option.of("zero");

        Option mappedValue = option.map(e -> Option.of(e.toUpperCase()));

        assertEquals(Option.of(Option.of("ZERO")), mappedValue);
    }
    @Test
    public void testOptionalFlatMap() {
        Option<String> option = Option.of("zero");

        Option mappedValue = option.flatMap(e -> Option.of(e.toUpperCase()));

        assertEquals(Option.of("ZERO"), mappedValue);
    }


    @Test
    public void testListMap() {
        List<List<String>> dictionnary = List.of(
                List.of("zero"),
                List.of("one", "two"));

        List<List<String>> dictionnaryAfterMap = dictionnary.stream()
                .map(list -> list.stream().map(String::toUpperCase).collect(Collectors.toList()))
                .collect(Collectors.toList());

        assertEquals(2, dictionnaryAfterMap.size());
        assertEquals(List.of("ZERO"), dictionnaryAfterMap.get(0));
        assertEquals(List.of("ONE", "TWO"), dictionnaryAfterMap.get(1));
    }


    @Test
    public void testListFlatMap() {
        List<List<String>> dictionnary = List.of(
                List.of("zero"),
                List.of("one", "two"));

        List<String> dictionnaryAfterMap = dictionnary.stream()
                .flatMap(list -> list.stream())
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertEquals(3, dictionnaryAfterMap.size());
        assertEquals("ZERO", dictionnaryAfterMap.get(0));
        assertEquals("ONE", dictionnaryAfterMap.get(1));
        assertEquals("TWO", dictionnaryAfterMap.get(2));
    }


    @Test
    public void testGroupBy() {
        io.vavr.collection.List<String> friendNames = API.List("Albert", "Alex", "Bob");

        Function<String, String> alphabeticalClassifier = name -> String.valueOf(name.charAt(0));
        Map<String, io.vavr.collection.List<String>> grouppedAlphabeticcaly = friendNames
                .groupBy(alphabeticalClassifier);


        assertEquals(true, grouppedAlphabeticcaly.get("A").isDefined());
        assertEquals(false, grouppedAlphabeticcaly.get("A").get().isEmpty());
        assertEquals(2, grouppedAlphabeticcaly.get("A").get().size());
        assertEquals("Albert", grouppedAlphabeticcaly.get("A").get().get(0));
        assertEquals("Alex", grouppedAlphabeticcaly.get("A").get().get(1));
    }


    @Test
    public void testVavrMapValues() {
        io.vavr.collection.List<String> friendNames = API.List("Albert", "Alex", "Bob");

        Function<String, String> alphabeticalClassifier = name -> String.valueOf(name.charAt(0));
        Map<String, String> grouppedAlphabeticcaly = friendNames
                .groupBy(alphabeticalClassifier)
                .mapValues(list -> list.last());

        assertEquals(true, grouppedAlphabeticcaly.get("A").isDefined());
        assertEquals("Alex", grouppedAlphabeticcaly.get("A").get());
    }

    @Test
    public void testVavrMapToJavaMap() {
        io.vavr.collection.List<String> friendNames = API.List("Albert", "Alex", "Bob");

        Function<String, String> alphabeticalClassifier = name -> String.valueOf(name.charAt(0));
        Map<String, String> vavrMap = friendNames
                .groupBy(alphabeticalClassifier)
                .mapValues(list -> list.last());

        java.util.Map<String, String> javaMap = vavrMap.toJavaMap();


        assertEquals("Alex", javaMap.get("A"));
    }





}
