package com.challengeTenpo.exceptions;

public class CalculoDinamicoException extends RuntimeException{

    private final long serialVersionUID = 1L;
    private final String mensaje = "Error en el calculo dinamico";

    public CalculoDinamicoException() {
    }

    public CalculoDinamicoException(String message) {
        super(message);
    }

    public CalculoDinamicoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculoDinamicoException(Throwable cause) {
        super(cause);
    }

    public CalculoDinamicoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
