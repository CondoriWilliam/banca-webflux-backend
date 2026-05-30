package com.wcod.webflux.ms_bff.service;

import com.wcod.webflux.ms_bff.http.response.ProductsByClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductsByClientService {
    Mono<ProductsByClientResponse> getAllProductsByClient(String id);
}
