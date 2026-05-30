package com.wcod.webflux.ms_product.service.impl;

import com.wcod.webflux.ms_product.model.Product;
import com.wcod.webflux.ms_product.repository.ProductRepository;
import com.wcod.webflux.ms_product.service.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Product> findById(Long id) {
        return productRepository
                .findById(id)
                .doOnNext(p -> System.out.println("\nFIND BY ID"));
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository
                .findAll()
                //.delayElements(Duration.ofSeconds(2))
                .doOnNext(p -> System.out.println("\nFIND ALL"));
    }

    @Override
    public Mono<Product> create(Product product) {
        return productRepository
                .save(product)
                .doOnNext(p -> System.out.println("\nCREATE"));
    }

    @Override
    public Mono<Product> update(Long id, Product product) {
        return productRepository
                .findById(id)
                .flatMap(existing -> {
                    Product newProduct = new Product(
                            existing.id(),
                            product.productType(),
                            product.name(),
                            product.balance(),
                            product.clientId(),
                            product.openingDate(),
                            product.limitAmount()
                    );
                    return productRepository.save(newProduct);
                })
                .doOnNext(p -> System.out.println("\nUPDATE"));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return productRepository
                .deleteById(id);
    }

    @Override
    public Flux<Product> findByClientId(String id) {
        return productRepository
                .getByClientId(id)
                .delayElements(Duration.ofSeconds(1))
                .switchIfEmpty(Flux.error(new RuntimeException("Error find to " + id)));
    }
}
