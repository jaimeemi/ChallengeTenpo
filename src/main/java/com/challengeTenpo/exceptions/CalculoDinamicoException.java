package com.challengeTenpo.exceptions;

import lombok.Getter;

@Getter
public class CalculoDinamicoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String codigoError;
    private final String mensaje;

    public CalculoDinamicoException() {
        this("Error en el cálculo dinámico", "CALC-001");
    }

    public CalculoDinamicoException(String mensaje) {
        this(mensaje, "CALC-001");
    }

    public CalculoDinamicoException(String mensaje, String codigoError) {
        super(mensaje);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

    public CalculoDinamicoException(String mensaje, Throwable cause, String codigoError) {
        super(mensaje, cause);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }
}