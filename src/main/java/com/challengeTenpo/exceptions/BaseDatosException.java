package com.challengeTenpo.exceptions;

import com.challengeTenpo.exceptions.Imp.BaseException;
import lombok.Getter;

@Getter
public class BaseDatosException extends BaseException {
    private static final long serialVersionUID = 4L;

    public BaseDatosException() {
        this("Error durante la comunicación con la base de datos", "DB-001");
    }

    public BaseDatosException(String mensaje) {
        this(mensaje, "DB-001");
    }

    public BaseDatosException(String mensaje, String codigoError) {
        super(mensaje, codigoError);
    }

    public BaseDatosException(String mensaje, Throwable cause, String codigoError) {
        super(mensaje, cause, codigoError);
    }
}
