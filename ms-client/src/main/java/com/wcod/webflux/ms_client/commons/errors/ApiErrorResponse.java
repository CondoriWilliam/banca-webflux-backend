package com.wcod.webflux.ms_client.commons.errors;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String code,
        String message,
        String details,
        String path,
        LocalDateTime timestamp
) {}
