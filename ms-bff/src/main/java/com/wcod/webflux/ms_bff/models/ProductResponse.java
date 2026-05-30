package com.wcod.webflux.ms_bff.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductResponse(
    Long id,
    ProductType productType,
    String name,
    BigDecimal balance,
    String clientId,
    LocalDate openingDate,
    BigDecimal limitAmount
) {
}
