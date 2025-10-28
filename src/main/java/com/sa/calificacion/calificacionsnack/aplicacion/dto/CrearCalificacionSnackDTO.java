package com.sa.calificacion.calificacionsnack.aplicacion.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearCalificacionSnackDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private UUID usuarioId;
    @NotNull(message = "El ID del snack es obligatorio")
    private UUID snackId;
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    private Integer puntuacion;
    @Size(max = 500, message = "El comentario no puede exceder 500 caracteres")
    private String comentario;
    private LocalDateTime fecha;
}
