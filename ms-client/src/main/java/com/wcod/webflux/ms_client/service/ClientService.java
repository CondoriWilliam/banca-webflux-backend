package com.wcod.webflux.ms_client.service;

import com.wcod.webflux.ms_client.controller.dto.ClientRequest;
import com.wcod.webflux.ms_client.controller.dto.ClientResponse;
import com.wcod.webflux.ms_client.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<ClientResponse> findAll();
    Mono<ClientResponse> getById(String id);
    Mono<ClientResponse> create(ClientRequest clientRequest);
    Mono<ClientResponse> update(String id, ClientRequest clientRequest);
    Mono<Void> deleteById(String id);
}
