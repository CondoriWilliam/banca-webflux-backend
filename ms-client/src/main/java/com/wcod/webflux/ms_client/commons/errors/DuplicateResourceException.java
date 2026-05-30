package com.wcod.webflux.ms_client.commons.errors;

public class DuplicateResourceException extends RuntimeException{
    private final String field;
    private final String value;

    public DuplicateResourceException(String field, String value) {
        super(String.format("El valor '%s' ya existe para el campo '%s'", value, field));
        this.field = field;
        this.value = value;
    }
}
