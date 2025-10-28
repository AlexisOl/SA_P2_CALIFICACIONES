package com.sa.calificacion.calificacionpelicula.dominio;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CalificacionPelicula {

    private UUID calificacionId;
    private UUID usuarioId;
    private UUID peliculaId;
    private Integer puntuacion;
    private String comentario;
    private LocalDateTime fecha;

    public CalificacionPelicula(UUID calificacionId, UUID usuarioId, UUID peliculaId,
                                Integer puntuacion, String comentario, LocalDateTime fecha) {
        this.calificacionId = calificacionId;
        this.usuarioId = usuarioId;
        this.peliculaId = peliculaId;
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
        if (peliculaId == null) {
            throw new IllegalArgumentException("La película es obligatoria");
        }
    }
}
