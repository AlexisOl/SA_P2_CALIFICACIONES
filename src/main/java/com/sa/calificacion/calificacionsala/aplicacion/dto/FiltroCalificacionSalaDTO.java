package com.sa.calificacion.calificacionsala.aplicacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroCalificacionSalaDTO {

    private UUID usuarioId;
    private UUID salaId;
    private Integer puntuacionMinima;
    private Integer puntuacionMaxima;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

}
