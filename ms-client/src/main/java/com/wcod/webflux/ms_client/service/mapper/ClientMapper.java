package com.wcod.webflux.ms_client.service.mapper;

import com.wcod.webflux.ms_client.controller.dto.ClientRequest;
import com.wcod.webflux.ms_client.controller.dto.ClientResponse;
import com.wcod.webflux.ms_client.model.Client;

import java.util.UUID;

public class ClientMapper {
    public static Client toEntity(ClientRequest request) {
        return new Client(
                UUID.randomUUID().toString(),
                request.name(),
                request.lastName(),
                request.documentType(),
                request.documentNumber()
        );
    }

    public static ClientResponse toResponse(Client client) {
        return new ClientResponse(
                client.uniqueCode(),
                client.name(),
                client.lastName(),
                client.documentType(),
                client.documentNumber()
        );
    }

    public static Client toEntityUpdate(Client existing, ClientRequest request){
        return new Client(
                existing.uniqueCode(),
                request.name(),
                request.lastName(),
                request.documentType(),
                request.documentNumber()
        );
    }
}
