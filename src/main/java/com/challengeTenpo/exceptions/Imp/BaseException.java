package com.challengeTenpo.exceptions.Imp;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException{

    private final String codigoError;
    private final String mensaje;

    public BaseException(String mensaje, String codigoError) {
        super(mensaje);
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

    public BaseException(String mensaje, Throwable cause, String codigoError) {
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }
}
