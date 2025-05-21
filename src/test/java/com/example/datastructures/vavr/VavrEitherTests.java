package com.example.datastructures.vavr;

import com.example.datastructures.vavr.util.Response;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VavrEitherTests {

    @Test
    public void testMatchesWithoutEither() {

        EitherDemo.Request request = new EitherDemo.Request("urls", "", "");
        String response = EitherDemo.matchesWithoutEither(request);
        assertEquals("Url_Failure", response);

        request = new EitherDemo.Request("url", "body", "footer");
        response = EitherDemo.matchesWithoutEither(request);
        assertEquals("success!!", response);
    }



    @Test
    public void testMatchesWithEither() {
        /* WHEN : appel appel avec parametres non correctes */
        EitherDemo.Request request = new EitherDemo.Request("urls", "", "");
        Response response = EitherDemo.matchesWithEither(request);

        /* THEN : resultat est failure */
        assertEquals(true, response.isFailure());
        assertEquals("Url_Failure", response.getValue());

        /* WHEN : appel avec des parametres correctes */
        request = new EitherDemo.Request("url", "body", "footer");
        response = EitherDemo.matchesWithEither(request);

        /* THEN : resultat est success */
        assertEquals(true, response.isSuccess());
        assertEquals("success!!", response.getValue());
    }

    @Test
    public void testSwap() {
        Either<String, Integer> eitherResult = calculateSquare(5);

        assertEquals(true, eitherResult.isRight());
        Either<Integer, String> swappedValue = eitherResult.swap();
        assertEquals(false, swappedValue.isRight());
    }



    public Either<String, Integer> calculateSquare(int number) {
        if (number < 0 || number > 10) {
            return Either.left("failure");
        } else {
            return Either.right(number * number);
        }
    }
}
