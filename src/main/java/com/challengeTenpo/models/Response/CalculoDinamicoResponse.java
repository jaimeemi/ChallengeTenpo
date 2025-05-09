package com.challengeTenpo.models.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CalculoDinamicoResponse {


    public CalculoDinamicoResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public CalculoDinamicoResponse(Double resultado) {
        this.resultado = resultado;
        this.timestamp = LocalDateTime.now();
    }

    @Schema(description = "Resultado del cálculo", example = "1250.75")
    private Double resultado;

    @Schema(description = "Fecha y hora del cálculo", example = "2023-05-15T14:30:45")
    private LocalDateTime timestamp;

}
