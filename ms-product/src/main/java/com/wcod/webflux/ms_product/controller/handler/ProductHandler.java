package com.wcod.webflux.ms_product.controller.handler;


import com.wcod.webflux.ms_product.commons.errors.InvalidPathVariableException;
import com.wcod.webflux.ms_product.model.Product;
import com.wcod.webflux.ms_product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String idRaw = request.pathVariable("id");
        long id;
        try {
            id = Long.parseLong(idRaw);
        } catch (NumberFormatException e) {
            throw new InvalidPathVariableException("Invalid country id: " + idRaw);
        }
        return productService
                .findById(id)
                .flatMap(client -> ServerResponse.ok().bodyValue(client))
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getAll(ServerRequest request){
        return productService.
                findAll()
                .collectList()
                .flatMap(list -> list.isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(list));
    }

    public Mono<ServerResponse> save(ServerRequest request){
        return request
                .bodyToMono(Product.class)
                .flatMap(productService::create)
                .flatMap(saved -> ServerResponse
                        .status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(saved)
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(Product.class)
                .flatMap(req -> productService.update(id, req))
                .flatMap(updated -> ServerResponse.ok().bodyValue(updated));

    }

    public Mono<ServerResponse> delete(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return productService
                .delete(id)
                .then(ServerResponse.noContent().build());
    }

//    @RequestMapping("/client/{id}")
//    public Flux<Product> findByClientId(@PathVariable String id) {
//        return productService
//                .findByClientId(id);
//    }

    public Mono<ServerResponse> findByClientId(ServerRequest request) {
        String clientId = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findByClientId(clientId), Product.class);
    }
}