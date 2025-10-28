package com.sa.calificacion.calificacionpelicula.infraestructura.salida.entrada.rest.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class ResponseCalificacionPeliculaDTO {

    UUID calificacionId;
    UUID usuarioId;
    UUID peliculaId;
    Integer puntuacion;
    String comentario;
    LocalDateTime fecha;
}
