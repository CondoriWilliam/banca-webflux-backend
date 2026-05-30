package com.wcod.webflux.ms_client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "client")
public record Client (
    @Id
    @Column("unique_code")
    String uniqueCode,

    @Column("name")
    String name,

    @Column("last_name")
    String lastName,

    @Column("document_type")
    DocumentType documentType,

    @Column("document_number")
    String documentNumber
) {}
