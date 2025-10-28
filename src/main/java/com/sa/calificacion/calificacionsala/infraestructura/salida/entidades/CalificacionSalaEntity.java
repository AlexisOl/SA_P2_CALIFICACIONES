package com.sa.calificacion.calificacionsala.infraestructura.salida.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calificacion_sala")
public class CalificacionSalaEntity {

    @Id
    @Column(name = "calificacion_id", updatable = false, nullable = false)
    private UUID calificacionId;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "sala_id", nullable = false)
    private UUID salaId;

    @Column(nullable = false)
    private Integer puntuacion;

    @Column(length = 500)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime fecha;
}
