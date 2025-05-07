package com.challengeTenpo.models.entityes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "HistorialCalculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class CalculosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "parametros", nullable = false)
    private String parámetros;

    @Column(name = "respuesta", nullable = false)
    private String respuesta;

    @Column(name = "error", nullable = false)
    private boolean error;

    @Column(name = "mensaje_error", nullable = false)
    private String mensajeError;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}
