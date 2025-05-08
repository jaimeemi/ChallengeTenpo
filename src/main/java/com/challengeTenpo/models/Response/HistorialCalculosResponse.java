package com.challengeTenpo.models.Response;

import com.challengeTenpo.models.entities.HistorialCalculosEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialCalculosResponse {

    private Long id;
    private LocalDateTime fecha;
    private String endpoint;
    private String parametros;
    private String respuesta;
    private boolean error;
    private String mensajeError;

    public static HistorialCalculosResponse fromEntity(HistorialCalculosEntity entity) {
        return HistorialCalculosResponse.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .endpoint(entity.getEndpoint())
                .parametros(entity.getParametros())
                .respuesta(entity.getRespuesta())
                .error(entity.getError())
                .mensajeError(entity.getMensajeError())
                .build();
    }

    public static List<HistorialCalculosResponse> fromEntities(List<HistorialCalculosEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(HistorialCalculosResponse::fromEntity)
                .collect(Collectors.toList());
    }

}
