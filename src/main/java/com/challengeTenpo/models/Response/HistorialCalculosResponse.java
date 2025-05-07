package com.challengeTenpo.models.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialCalculosResponse {

    @Schema(description = "Lista de cálculos históricos")
    private List<HistorialItem> historial;

    @Schema(description = "Total de registros")
    private Long total;

    @Data
    @Builder
    public static class HistorialItem {
        private Long id;
        private LocalDateTime fecha;
        private String endpoint;
        private String parametros;
        private String respuesta;
        private Boolean error;
    }
}
