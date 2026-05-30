package com.wcod.webflux.ms_bff.models;

public record ClientResponse (
        String name,
        String lastName,
        DocumentType documentType,
        String documentNumber
) {}

