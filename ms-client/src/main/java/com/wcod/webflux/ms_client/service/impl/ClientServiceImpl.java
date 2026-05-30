package com.wcod.webflux.ms_client.service.impl;

import com.wcod.webflux.ms_client.commons.errors.DuplicateResourceException;
import com.wcod.webflux.ms_client.commons.exceptions.ServiceException;
import com.wcod.webflux.ms_client.controller.dto.ClientRequest;
import com.wcod.webflux.ms_client.controller.dto.ClientResponse;
import com.wcod.webflux.ms_client.repository.ClientRepository;
import com.wcod.webflux.ms_client.service.ClientService;
import com.wcod.webflux.ms_client.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.wcod.webflux.ms_client.service.exception.ClientHandlerException.mapDuplicateConstraint;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
    private final ClientRepository clientRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public ClientServiceImpl(ClientRepository clientRepository, R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.clientRepository = clientRepository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    @Override
    public Flux<ClientResponse> findAll() {
        return clientRepository
                .findAll()
                .map(ClientMapper::toResponse)
                .onErrorMap(e -> new ServiceException("\nError finding all clients: " + e));
    }

    @Override
    public Mono<ClientResponse> getById(String id) {
        return clientRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("\nClient not found: " + id)))
                .map(ClientMapper::toResponse)
                .doOnError(error -> log.error("Error finding client: {}", error.getMessage()))
                .onErrorMap(e -> !(e instanceof RuntimeException),
                        e -> new ServiceException("\nError to GET clients: ", e));
    }

    @Override
    public Mono<ClientResponse> create(ClientRequest clientRequest) {
        return r2dbcEntityTemplate
                .insert((ClientMapper.toEntity(clientRequest)))
                .map(ClientMapper::toResponse)
                .doOnError(e -> log.error("Error creating client: {}", e.getMessage()))
                .onErrorMap(e -> new ServiceException("Error creating client: ", e));
    }

    @Override
    public Mono<ClientResponse> update(String id, ClientRequest clientRequest) {
        return clientRepository
                .findById(id)
                .switchIfEmpty(Mono.error(
                        new ServiceException(String.format(
                                "No existe cliente con el id = %s", id))
                ))
                .map(clientExisting -> ClientMapper.toEntityUpdate(clientExisting, clientRequest))
                .flatMap(clientRepository::save)
                .map(ClientMapper::toResponse)
//                .doOnError(error -> log.error("Error updating client: {}", error.getMessage()))
//                .onErrorMap(e -> !(e instanceof RuntimeException),
//                        e -> new ServiceException("\nError updating client: ", e));
                .onErrorMap(ex -> {
                    Throwable throwable = mapDuplicateConstraint(
                            ex,
                            clientRequest.uniqueCode(),
                            clientRequest.documentNumber());
                    if (throwable instanceof DuplicateResourceException) {
                        return throwable;
                    }
                    return new ServiceException(
                            String.format("Error al actualizar el cliente con id = %s", id),
                            ex);
                });

    }

    @Override
    public Mono<Void> deleteById(String id) {
        return clientRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("\nClient without id " + id)))
                .flatMap(clientRepository::delete)
                .then()
                .doOnError(e -> log.error("Error deleting client: {}", e.getMessage()))
                .onErrorMap(e -> !(e instanceof RuntimeException),
                        e -> new ServiceException("Error deleting client: " + e));
    }
}








