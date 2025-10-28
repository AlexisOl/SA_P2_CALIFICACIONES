package com.sa.calificacion.calificacionsnack.infraestructura.entrada.rest.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class ResponseCalificacionSnackDTO {

    UUID calificacionId;
    UUID usuarioId;
    UUID snackId;
    Integer puntuacion;
    String comentario;
    LocalDateTime fecha;
}
