package com.wcod.webflux.ms_client.controller.dto;

import com.wcod.webflux.ms_client.model.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record ClientRequest(

    @NotBlank(message = "ClientEntity code is required")
    @Size(min = 2, max = 3, message = "ClientEntity code must have 2 or 3 characters")
    String uniqueCode,

    @NotBlank(message = "ClientEntity name is required")
    @Size(min = 3, max = 10, message = "ClientEntity name must have {min} characters and must not exceed {max} characters")
    String name,

    @NotBlank(message = "ClientEntity last name is required")
    @Size(min = 3, max = 20, message = "ClientEntity last name must have {min} characters and must not exceed {max} characters")
    String lastName,

    @NotNull(message = "ClientEntity document type is required")
    DocumentType documentType,

    @NotBlank(message = "ClientEntity document number is required")
    @Pattern(regexp = "\\d{8,12}", message = "ClientEntity document number must contain between 8 and 12 digits")
    String documentNumber
) {
}
