package com.kiselev.ssu.flanguage.exception;

public class UnknownOperationException extends RuntimeException {

    public UnknownOperationException() {
    }

    public UnknownOperationException(String message) {
        super(message);
    }

    public UnknownOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
