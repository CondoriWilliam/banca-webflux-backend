package com.wcod.webflux.ms_product.controller.router;

import com.wcod.webflux.ms_product.controller.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

    @Bean
    RouterFunction<ServerResponse> clientRoutes(ProductHandler clientHandler){
        return RouterFunctions.route()
                .path("/api/v1/products", builder -> builder
                        .GET("" , clientHandler::getAll)
                        .GET("/{id}" , clientHandler::findById)
                        .POST("", clientHandler::save)
                        .PUT("/{id}", clientHandler::update)
                        .DELETE("/{id}", clientHandler::delete)
                        .GET("/client/{id}", clientHandler::findByClientId)
                )
                .build();

    }
}
