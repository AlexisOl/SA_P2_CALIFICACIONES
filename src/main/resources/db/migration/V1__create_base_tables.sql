-- Tabla: calificacion_pelicula
CREATE TABLE calificacion_pelicula (
                                       calificacion_id CHAR(36) NOT NULL,
                                       usuario_id CHAR(36) NOT NULL,
                                       pelicula_id CHAR(36) NOT NULL,
                                       puntuacion INT NOT NULL,
                                       comentario VARCHAR(500),
                                       fecha DATETIME NOT NULL,
                                       PRIMARY KEY (calificacion_id),
                                       UNIQUE KEY uk_usuario_pelicula (usuario_id, pelicula_id),
                                       CONSTRAINT chk_puntuacion_pelicula CHECK (puntuacion >= 1 AND puntuacion <= 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Índices para calificacion_pelicula
CREATE INDEX idx_calificacion_pelicula_usuario ON calificacion_pelicula(usuario_id);
CREATE INDEX idx_calificacion_pelicula_pelicula ON calificacion_pelicula(pelicula_id);
CREATE INDEX idx_calificacion_pelicula_fecha ON calificacion_pelicula(fecha);
CREATE INDEX idx_calificacion_pelicula_puntuacion ON calificacion_pelicula(puntuacion);

-- Tabla: calificacion_sala
CREATE TABLE calificacion_sala (
                                   calificacion_id CHAR(36) NOT NULL,
                                   usuario_id CHAR(36) NOT NULL,
                                   sala_id CHAR(36) NOT NULL,
                                   puntuacion INT NOT NULL,
                                   comentario VARCHAR(500),
                                   fecha DATETIME NOT NULL,
                                   PRIMARY KEY (calificacion_id),
                                   UNIQUE KEY uk_usuario_sala (usuario_id, sala_id),
                                   CONSTRAINT chk_puntuacion_sala CHECK (puntuacion >= 1 AND puntuacion <= 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Índices para calificacion_sala
CREATE INDEX idx_calificacion_sala_usuario ON calificacion_sala(usuario_id);
CREATE INDEX idx_calificacion_sala_sala ON calificacion_sala(sala_id);
CREATE INDEX idx_calificacion_sala_fecha ON calificacion_sala(fecha);
CREATE INDEX idx_calificacion_sala_puntuacion ON calificacion_sala(puntuacion);

-- Tabla: calificacion_snack
CREATE TABLE calificacion_snack (
                                    calificacion_id CHAR(36) NOT NULL,
                                    usuario_id CHAR(36) NOT NULL,
                                    snack_id CHAR(36) NOT NULL,
                                    puntuacion INT NOT NULL,
                                    comentario VARCHAR(500),
                                    fecha DATETIME NOT NULL,
                                    PRIMARY KEY (calificacion_id),
                                    UNIQUE KEY uk_usuario_snack (usuario_id, snack_id),
                                    CONSTRAINT chk_puntuacion_snack CHECK (puntuacion >= 1 AND puntuacion <= 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Índices para calificacion_snack
CREATE INDEX idx_calificacion_snack_usuario ON calificacion_snack(usuario_id);
CREATE INDEX idx_calificacion_snack_snack ON calificacion_snack(snack_id);
CREATE INDEX idx_calificacion_snack_fecha ON calificacion_snack(fecha);
CREATE INDEX idx_calificacion_snack_puntuacion ON calificacion_snack(puntuacion);

-- Comentarios de las tablas
ALTER TABLE calificacion_pelicula COMMENT = 'Tabla de calificaciones de películas';
ALTER TABLE calificacion_sala COMMENT = 'Tabla de calificaciones de salas';
ALTER TABLE calificacion_snack COMMENT = 'Tabla de calificaciones de snacks';