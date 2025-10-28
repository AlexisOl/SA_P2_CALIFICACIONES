package com.sa.calificacion.calificacionsala.infraestructura.entrada.rest.dto;

import lombok.Value;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class ResponseCalificacionSalaDTO {

    UUID calificacionId;
    UUID usuarioId;
    UUID salaId;
    Integer puntuacion;
    String comentario;
    LocalDateTime fecha;
}
