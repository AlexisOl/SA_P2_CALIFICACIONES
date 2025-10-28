package com.sa.calificacion.calificacionpelicula.aplicaion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroCalificacionPeliculaDTO {

    private UUID usuarioId;
    private UUID peliculaId;
    private Integer puntuacionMinima;
    private Integer puntuacionMaxima;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
