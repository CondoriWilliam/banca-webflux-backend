package com.wcod.webflux.ms_product.service;

import com.wcod.webflux.ms_product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> findById(Long id);
    Flux<Product> findAll();
    Mono<Product> create(Product product);
    Mono<Product> update(Long id, Product product);
    Mono<Void> delete(Long id);
    Flux<Product> findByClientId(String id);
}
