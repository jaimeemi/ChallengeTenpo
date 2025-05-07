package com.challengeTenpo.exceptions;

public class SinHistorialCalculosException extends RuntimeException{

    private final long serialVersionUID = 2L;
    private final String mensaje = "No hay historial de calculos";

    public SinHistorialCalculosException() {
    }

    public SinHistorialCalculosException(String message) {
        super(message);
    }

    public SinHistorialCalculosException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinHistorialCalculosException(Throwable cause) {
        super(cause);
    }

    public SinHistorialCalculosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
