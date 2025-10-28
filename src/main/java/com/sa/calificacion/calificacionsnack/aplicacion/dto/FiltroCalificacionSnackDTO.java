package com.sa.calificacion.calificacionsnack.aplicacion.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroCalificacionSnackDTO {

    private UUID usuarioId;
    private UUID snackId;
    private Integer puntuacionMinima;
    private Integer puntuacionMaxima;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
