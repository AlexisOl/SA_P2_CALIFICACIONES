package com.sa.calificacion.calificacionsala.aplicacion.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearCalificacionSalaDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private UUID usuarioId;
    @NotNull(message = "El ID de la sala es obligatorio")
    private UUID salaId;
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    private Integer puntuacion;
    @Size(max = 500, message = "El comentario no puede exceder 500 caracteres")
    private String comentario;
    private LocalDateTime fecha;
}
