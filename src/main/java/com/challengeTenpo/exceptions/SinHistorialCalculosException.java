package com.challengeTenpo.exceptions;

import lombok.Getter;

@Getter
public class SinHistorialCalculosException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private final String codigoError;
    private final String mensaje;

    public SinHistorialCalculosException() {
        this("No hay historial de cálculos", "HIST-001");
    }

    public SinHistorialCalculosException(String mensaje) {
        this(mensaje, "HIST-001");
    }

    public SinHistorialCalculosException(String mensaje, String codigoError) {
        super(mensaje);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

    public SinHistorialCalculosException(String mensaje, Throwable cause, String codigoError) {
        super(mensaje, cause);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

}
