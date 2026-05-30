package com.wcod.webflux.ms_client.commons.errors;

public class InvalidPathVariableException extends RuntimeException {
    public InvalidPathVariableException(String message) {
        super(message);
    }
}
