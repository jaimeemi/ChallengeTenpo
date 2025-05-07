package com.challengeTenpo.exceptions;

public class FeignApiException extends RuntimeException{

    private final long serialVersionUID = 3L;

    private final String mensaje = "Error en la comunicacion con la api externa";

    public FeignApiException() {
    }

    public FeignApiException(String message) {
        super(message);
    }

    public FeignApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignApiException(Throwable cause) {
        super(cause);
    }

    public FeignApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
