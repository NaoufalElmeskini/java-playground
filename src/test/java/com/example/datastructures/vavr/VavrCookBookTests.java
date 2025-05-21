package com.example.datastructures.vavr;


import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class VavrCookBookTests {


    @Test
    public void option() {
        Option nullOption = Option.of(null);
        Option someOption = Option.of("value");

        assertEquals("None", nullOption.toString());
        assertEquals(null, nullOption.getOrNull());

        assertEquals("Some(value)", someOption.toString());
        assertEquals("value", someOption.get());
    }

    @Test
    public void optionGetOrElse() {
        Option someOption = Option.of(null);

        assertEquals("other", someOption.getOrElse("other"));
    }

    @Test
    public void optionGetOrElseSupply() {
        Option someOption = Option.of(null);

        assertEquals("supplied_value", someOption.getOrElse(() -> "supplied_value"));
    }

    @Test
    public void optionalGetOrElseSupply() {
        Optional someOption = Optional.ofNullable(null);

        assertEquals("supplied_value", someOption.orElseGet(() -> "supplied_value"));
    }

    @Test
    public void testTuples() {
        Tuple2 couteauSuisse = new Tuple2<>("couteau_suisse", 15);
        assertEquals("couteau_suisse", couteauSuisse._1);
        assertEquals(15, couteauSuisse._2);
    }

    @Test()
    public void testException() {
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> {
            int n = 1/0;
        });

        assertEquals("/ by zero", arithmeticException.getMessage());
    }

    @Test
    public void testVavrTry() {
        Try tryResult = Try.of(() -> {
            return 1/0;
        });
        assertTrue(tryResult.isFailure());
    }

    @Test
    public void javaFunction() {
        Function<Integer, Integer> square = n -> n * n;

        assertEquals(25, square.apply(5));
    }

    @Test
    public void javaBiFunction() {
        BiFunction<Integer, Integer, String> add = (a, b) -> Integer.toString(a + b);

        assertEquals("6", add.apply(5, 1));
    }

    @Test
    public void vavrFunction() {
        Function2<Integer, Integer, String> addFunction = (a, b) -> String.valueOf(a + b);

        assertEquals("5", addFunction.apply(2, 3));
    }


}
