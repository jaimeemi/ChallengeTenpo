package com.challengeTenpo.exceptions;

import lombok.Getter;

@Getter
public class BaseDatosException extends RuntimeException {
    private static final long serialVersionUID = 4L;
    private final String codigoError;
    private final String mensaje;

    public BaseDatosException() {
        this("Error durante la comunicación con la base de datos", "DB-001");
    }

    public BaseDatosException(String mensaje) {
        this(mensaje, "DB-001");
    }

    public BaseDatosException(String mensaje, String codigoError) {
        super(mensaje);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

    public BaseDatosException(String mensaje, Throwable cause, String codigoError) {
        super(mensaje, cause);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }
}
