package com.wcod.webflux.ms_product.repository;

import com.wcod.webflux.ms_product.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> getByClientId(String id);
}
