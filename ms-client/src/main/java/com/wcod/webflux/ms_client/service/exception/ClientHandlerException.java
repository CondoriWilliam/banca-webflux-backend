package com.wcod.webflux.ms_client.service.exception;

import com.wcod.webflux.ms_client.commons.errors.DuplicateResourceException;

public class ClientHandlerException {
    public static Throwable mapDuplicateConstraint(
            Throwable ex,
            String uniqueCode,
            String documentNumber) {

        String msg = ex.getMessage();

        if (msg != null) {

            if (msg.contains("unique_code")) {
                return new DuplicateResourceException("code", uniqueCode);
            }

            if (msg.contains("document_number")) {
                return new DuplicateResourceException("name", documentNumber);
            }
        }

        return ex;
    }
}
