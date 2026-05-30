package com.wcod.webflux.ms_product.dto;

import com.wcod.webflux.ms_product.model.ProductType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductRequest(

        @NotNull(message = "Product type is required")
        ProductType productType,

        @Size(max = 100, message = "Product name must not exceed 100 characters")
        String name,

        @NotNull(message = "Balance is required")
        @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
        BigDecimal balance,

        @Size(max = 50, message = "Client ID must not exceed 50 characters")
        String clientId,

        @NotNull(message = "Opening date is required")
        LocalDate openingDate,

        @DecimalMin(value = "0.0", inclusive = true, message = "Limit amount must be non-negative")
        BigDecimal limitAmount
) {}
