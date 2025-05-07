package com.challengeTenpo.models.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class HistorialCalculosDTO {

    private Long id;
    private LocalDateTime fecha;
    private String endpoint;
    private String parámetros;
    private String respuesta;
    private boolean error;
    private String mensajeError;

    public HistorialCalculosDTO(String endpoint, String parámetros, String respuesta, String mensajeError) {
        this.fecha = LocalDateTime.now();
        this.error = mensajeError != "";

        this.endpoint = endpoint;
        this.parámetros = parámetros;
        this.respuesta = respuesta;
        this.mensajeError = mensajeError;
    }
}
