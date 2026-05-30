package com.wcod.webflux.ms_product.commons.errors;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String code,
        String message,
        String details,
        String path,
        LocalDateTime timestamp
) {}
