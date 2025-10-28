package com.sa.calificacion.calificacionsnack.dominio;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CalificacionSnack {

    private UUID calificacionId;
    private UUID usuarioId;
    private UUID snackId;
    private Integer puntuacion;
    private String comentario;
    private LocalDateTime fecha;

    public CalificacionSnack(UUID calificacionId, UUID usuarioId, UUID snackId,
                             Integer puntuacion, String comentario, LocalDateTime fecha) {
        this.calificacionId = calificacionId;
        this.usuarioId = usuarioId;
        this.snackId = snackId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
        validarCalificacion();
    }

    private void validarCalificacion() {
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5");
        }
        if (usuarioId == null) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
        if (snackId == null) {
            throw new IllegalArgumentException("El snack es obligatorio");
        }
    }
}
