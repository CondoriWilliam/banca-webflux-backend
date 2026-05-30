package com.wcod.webflux.ms_product.commons.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcod.webflux.ms_product.commons.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class GlobalErrorHandler implements WebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalErrorHandler.class);
    private final ObjectMapper objectMapper;
    private static final String MSG_INTERNAL_ERROR ="Se ha producido un error interno";

    public GlobalErrorHandler() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        System.out.println("\nHOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA \n");
        HttpStatus status = resolveStatus(ex);

        ApiErrorResponse error = new ApiErrorResponse(
                resolveCode(ex),
                resolveMessage(ex),
                ex.getMessage(),
                exchange.getRequest().getPath().value(),
                LocalDateTime.now()
        );

        logError(ex, status);

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders()
                .setContentType(MediaType.APPLICATION_JSON);

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(error);
            return exchange.getResponse()
                    .writeWith(Mono.just(
                            exchange.getResponse()
                                    .bufferFactory()
                                    .wrap(bytes)));
        } catch (Exception e) {
            System.out.println("\nERROR EN OBJECT MAPPER \n");
            return Mono.error(e);
        }
    }

    private HttpStatus resolveStatus(Throwable ex) {
        if (ex instanceof InvalidPathVariableException) {
            return HttpStatus.BAD_REQUEST;
        }
        if (ex instanceof ServiceException) {
            return HttpStatus.BAD_REQUEST;
        }
        if (ex instanceof DuplicateResourceException) {
            return HttpStatus.CONFLICT;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String resolveCode(Throwable ex) {
        if (ex instanceof InvalidPathVariableException) {
            return ErrorCode.VALIDATION_ERROR.name();
        }
        if (ex instanceof ServiceException) {
            return ErrorCode.VALIDATION_ERROR.name();
        }
        if (ex instanceof DuplicateResourceException) {
            return ErrorCode.RESOURCE_ALREADY_EXISTS.name();
        }
        return ErrorCode.INTERNAL_ERROR.name();
    }

    private String resolveMessage(Throwable ex) {
        if (ex instanceof InvalidPathVariableException) {
            return "Error de validación";
        }
        if (ex instanceof ServiceException) {
            return "Error de validación";
        }
        if (ex instanceof DuplicateResourceException) {
            return "El recurso ya existe";
        }
        return MSG_INTERNAL_ERROR;
    }

    private void logError(Throwable ex, HttpStatus status) {
        if (status.is5xxServerError()) {
            log.error("Error interno no controlado", ex);
        } else {
            log.warn("Error controlado [{}]: {}", status, ex.getMessage());
        }
    }
}
