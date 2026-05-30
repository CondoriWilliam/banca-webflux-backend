package com.wcod.webflux.ms_client.controller;

import com.wcod.webflux.ms_client.controller.dto.ClientRequest;
import com.wcod.webflux.ms_client.controller.dto.ClientResponse;
import com.wcod.webflux.ms_client.model.Client;
import com.wcod.webflux.ms_client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClientResponse>> findById(@PathVariable String id) {
        return clientService
                .getById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping
    public Mono<ResponseEntity<?>> getAll() {
        return clientService
                .findAll()
                .collectList()
                .map(clients -> clients.isEmpty()
                        ? ResponseEntity.noContent().build()
                        : ResponseEntity.ok(clients))
                .onErrorMap(RuntimeException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<ClientResponse>> create(@RequestBody ClientRequest clientRequest){
        return clientService
                .create(clientRequest)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ClientResponse>> update(@PathVariable String id, @RequestBody ClientRequest clientRequest) {
        return clientService
                .update(id, clientRequest)
                .map(ResponseEntity::ok)
                .onErrorMap(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return  clientService
                .deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

}
