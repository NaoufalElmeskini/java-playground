package com.example.datastructures.vavr;

import com.example.datastructures.vavr.util.Response;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

public class EitherDemo {

    public static String matchesWithoutEither(Request request) {
        if (request.url != "url")
            return ("Url_Failure");
        if (request.body != "body")
            return ("body_failure");
        return ("success!!");
    }


    public static Response matchesWithEither(Request request) {
        String SUCCESS_MESSAGE = "success!!";

        return matchesUrls(request)
                .flatMap(req -> matchesBody(req))
                .flatMap(req -> matchesFooter(req))
                .fold(errorMessage -> new Response.Failure(errorMessage), req -> new Response.Success(SUCCESS_MESSAGE));
    }


    private static Either<String, Request> matchesUrls(Request request) {
        if (!"url".equals(request.url)) {
            return Either.left("Url_Failure");
        }
        return Either.right(request);
    }

    private static Either<String, Request> matchesBody(Request request) {
        if (!"body".equals(request.body)) {
            return Either.left("body_failure");
        }
        return Either.right(request);
    }
    private static Either<String, Request> matchesFooter(Request request) {
        if (!"footer".equals(request.footer)) {
            return Either.left("footer_failure");
        }
        return Either.right(request);
    }

    @AllArgsConstructor
    public static class Request {
        public String url;
        public String body;
        public String footer;
    }
}
