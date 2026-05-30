package com.wcod.webflux.ms_product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Product(
    @Id
    @Column("id")
    Long id,

    @Column("product_type")
    ProductType productType,

    @Column("name")
       String name,

    @Column("balance")
    BigDecimal balance,

    @Column("client_id")
    String clientId,

    @Column("opening_date")
    LocalDate openingDate,

    @Column("limit_amount")
    BigDecimal limitAmount
) {}
