package com.wcod.webflux.ms_bff.controller;

import com.wcod.webflux.ms_bff.http.response.ProductsByClientResponse;
import com.wcod.webflux.ms_bff.service.ProductsByClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/bffs")
public class ProductsByClientController {

    private final ProductsByClientService productsByClientService;

    public ProductsByClientController(ProductsByClientService productsByClientService) {
        this.productsByClientService = productsByClientService;
    }

    @GetMapping("/produtcsByClient/{id}")
    public Mono<ProductsByClientResponse> findAllProductsByClient(@PathVariable String id){
        return productsByClientService
                .getAllProductsByClient(id);
    }
}
