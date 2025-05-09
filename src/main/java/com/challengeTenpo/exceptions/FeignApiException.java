package com.challengeTenpo.exceptions;

import lombok.Getter;

@Getter
public class FeignApiException extends RuntimeException {
    private static final long serialVersionUID = 3L;
    private final String codigoError;
    private final String mensaje;

    public FeignApiException() {
        this("Servicio externo no disponible", "API-001");
    }

    public FeignApiException(String mensaje) {
        this(mensaje, "API-001");
    }

    public FeignApiException(String mensaje, String codigoError) {
        super(mensaje);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

    public FeignApiException(String mensaje, Throwable cause, String codigoError) {
        super(mensaje, cause);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

}
