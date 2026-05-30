package com.wcod.webflux.ms_product.commons.errors;

public class InvalidPathVariableException extends RuntimeException {
    public InvalidPathVariableException(String message) {
        super(message);
    }
}
