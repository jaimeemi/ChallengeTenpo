package com.challengeTenpo.models.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculoDinamicoResponse {


    @Schema(description = "Resultado del cálculo", example = "1250.75")
    private Double resultado;

    @Schema(description = "Fecha y hora del cálculo", example = "2023-05-15T14:30:45")
    private LocalDateTime timestamp;

    @Schema(description = "Indica si el cálculo fue exitoso")
    private Boolean success;

}
