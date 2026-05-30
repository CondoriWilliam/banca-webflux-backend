package com.wcod.webflux.ms_client.controller.dto;

import com.wcod.webflux.ms_client.model.DocumentType;

public record ClientResponse(
    String id,
    String name,
    String lastName,
    DocumentType documentType,
    String documentNumber
) {
}
