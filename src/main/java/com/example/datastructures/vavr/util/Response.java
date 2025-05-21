package com.example.datastructures.vavr.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Response {
    private String value;

    public abstract boolean isFailure();
    public abstract boolean isSuccess();

    public static class Failure extends Response {
        public Failure(String value) {
            super(value);
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }
    }

    public static class Success extends Response {
        public Success(String value) {
            super(value);
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }
    }
}
