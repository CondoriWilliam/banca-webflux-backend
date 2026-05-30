package com.wcod.webflux.ms_bff.service.impl;

import com.wcod.webflux.ms_bff.http.response.ProductsByClientResponse;
import com.wcod.webflux.ms_bff.models.ClientResponse;
import com.wcod.webflux.ms_bff.models.DocumentType;
import com.wcod.webflux.ms_bff.models.ProductResponse;
import com.wcod.webflux.ms_bff.models.ProductType;
import com.wcod.webflux.ms_bff.service.ProductsByClientService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductsByClientImpl implements ProductsByClientService {

    private final WebClient clientWebClient;
    private final WebClient clientWebProduct;

    public ProductsByClientImpl(WebClient clientWebClient, WebClient clientWebProduct) {
        this.clientWebClient = clientWebClient;
        this.clientWebProduct = clientWebProduct;
    }

    // Via codigo
    @Override
    @CircuitBreaker(name = "productsByClient", fallbackMethod = "productsByClientFallback")
    @Retry(name = "productsByClient")
    @TimeLimiter(name = "productsByClient")
    public Mono<ProductsByClientResponse> getAllProductsByClient(String id) {

        Mono<ClientResponse> client = clientWebClient
                .get()
                .uri("/clients/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new RuntimeException("Client not found")))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Client service error")))
                .bodyToMono(ClientResponse.class);

        Mono<List<ProductResponse>> products = clientWebProduct
                .get()
                .uri("/products/client/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new RuntimeException("Products not found for client: " + id)))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Product service error")))
                .bodyToFlux(ProductResponse.class)
                .collectList();

        return Mono.zip(client, products)
                .map(t -> new ProductsByClientResponse(
                        t.getT1(),
                        t.getT2()
                ));
    }

    public Mono<ProductsByClientResponse> productsByClientFallback(String id, Throwable ex) {

        System.out.println("\n=================================");
        System.out.println("FALLBACK EJECUTADO");
        System.out.println("ERROR: " + ex.getClass().getSimpleName());
        System.out.println("MENSAJE: " + ex.getMessage());
        System.out.println("=================================\n");

        ClientResponse fallbackClient = new ClientResponse(
                "Servicio",
                "No Disponible",
                DocumentType.DNI,
                "00000000"
        );

        ProductResponse fallbackProduct = new ProductResponse(
                -1L,
                ProductType.CREDIT_CARD,
                "Productos no disponibles",
                BigDecimal.ZERO,
                id,
                LocalDate.now(),
                BigDecimal.ZERO
        );

        ProductsByClientResponse response = new ProductsByClientResponse(
                fallbackClient,
                List.of(fallbackProduct)
        );

        return Mono.just(response);
    }
}
