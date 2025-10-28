package com.sa.calificacion.calificacionsala.dominio;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CalificacionSala {

    private UUID calificacionId;
    private UUID usuarioId;
    private UUID salaId;
    private Integer puntuacion;
    private String comentario;
    private LocalDateTime fecha;
    private Boolean bloqueada; // Para indicar si la sala tiene comentarios bloqueados

    public CalificacionSala(UUID calificacionId, UUID usuarioId, UUID salaId,
                            Integer puntuacion, String comentario, LocalDateTime fecha) {
        this.calificacionId = calificacionId;
        this.usuarioId = usuarioId;
        this.salaId = salaId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.bloqueada = false;
        validarCalificacion();
    }

    private void validarCalificacion() {
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5");
        }
        if (usuarioId == null) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
        if (salaId == null) {
            throw new IllegalArgumentException("La sala es obligatoria");
        }
    }
}
