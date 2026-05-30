package com.wcod.webflux.ms_bff.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    @Value("${services.client.base-url}")
    private String clientBaseUrl;

    @Value("${services.product.base-url}")
    private String productBaseUrl;

    @Bean
    @Qualifier("clientWebClient")
    public WebClient clientWebClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(3));
        return builder
                .baseUrl(clientBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    @Qualifier("clientWebProduct")
    public WebClient clientWebProduct(WebClient.Builder builder) {

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(3));
        return builder
                .baseUrl(productBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
