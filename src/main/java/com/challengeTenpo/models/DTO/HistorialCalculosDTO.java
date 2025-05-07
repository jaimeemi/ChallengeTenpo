package com.challengeTenpo.models.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialCalculosDTO {

    private Long id;
    private LocalDateTime fecha;

    @NotBlank(message = "El endpoint es obligatorio")
    private String endpoint;

    @NotBlank(message = "Los parámetros son obligatorios")
    private String parametros;

    @NotBlank(message = "La respuesta es obligatoria")
    private String respuesta;

    private Boolean error;
    private String mensajeError;

    public static HistorialCalculosDTO of(String endpoint, String parametros, String respuesta, String mensajeError) {
        return HistorialCalculosDTO.builder()
                .fecha(LocalDateTime.now())
                .endpoint(endpoint)
                .parametros(parametros)
                .respuesta(respuesta)
                .mensajeError(mensajeError)
                .error(mensajeError != null && !mensajeError.isEmpty())
                .build();
    }
}