package com.challengeTenpo.exceptions;

import java.sql.SQLException;

public class BaseDatosException extends SQLException {

    private static final long serialVersionUID = 4L;
    private String mensaje = "Error durante la comunicacion con la base de datos";

    public BaseDatosException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public BaseDatosException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public BaseDatosException(String reason) {
        super(reason);
    }

    public BaseDatosException() {
    }

    public BaseDatosException(Throwable cause) {
        super(cause);
    }

    public BaseDatosException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public BaseDatosException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public BaseDatosException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}
