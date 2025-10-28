package com.sa.calificacion.calificacionsnack.infraestructura.salida.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calificacion_snack")
public class CalificacionSnackEntity {

    @Id
    @Column(name = "calificacion_id", updatable = false, nullable = false)
    private UUID calificacionId;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "snack_id", nullable = false)
    private UUID snackId;

    @Column(nullable = false)
    private Integer puntuacion;

    @Column(length = 500)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime fecha;
}
