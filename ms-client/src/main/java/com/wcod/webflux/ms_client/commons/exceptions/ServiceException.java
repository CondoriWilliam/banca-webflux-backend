package com.wcod.webflux.ms_client.commons.exceptions;

import java.io.Serial;

public class ServiceException extends Exception{

    private static final long serialVersionUID = 4197562784843371628L;

    public ServiceException(){}

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable throwable){
        super(throwable);
    }

    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
