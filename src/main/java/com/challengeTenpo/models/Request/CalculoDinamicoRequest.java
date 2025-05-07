package com.challengeTenpo.models.Request;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CalculoDinamicoRequest {

    @NonNull
    @DecimalMin("0.1")
    private double monto;

    @NonNull
    @DecimalMin("0.1")
    private double porcentaje;
}
