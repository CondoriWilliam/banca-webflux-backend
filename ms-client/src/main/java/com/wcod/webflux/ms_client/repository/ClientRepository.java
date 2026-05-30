package com.wcod.webflux.ms_client.repository;

import com.wcod.webflux.ms_client.model.Client;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ClientRepository extends ReactiveCrudRepository<Client, String> {
    @Query("""
    SELECT * FROM clients
    WHERE(
           UPPER(name)) LIKE UPPER(CONCAT(%, :name, %))
            or (UPPER(last_name)) LIKE UPPER(CONCAT(%, :name, %))
    """)
    Flux<Client> findByName(String name);
}
