package com.sa.calificacion.calificacionpelicula.infraestructura.salida.repositorio;

import com.sa.calificacion.calificacionpelicula.infraestructura.salida.entidades.CalificacionPeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CalificacionPeliculaRepository extends JpaRepository<CalificacionPeliculaEntity, UUID> {

    List<CalificacionPeliculaEntity> findByPeliculaId(UUID peliculaId);
    List<CalificacionPeliculaEntity> findByUsuarioId(UUID usuarioId);
    boolean existsByUsuarioIdAndPeliculaId(UUID usuarioId, UUID peliculaId);

    @Query("SELECT AVG(c.puntuacion) FROM CalificacionPeliculaEntity c WHERE c.peliculaId = :peliculaId")
    Double obtenerPromedioCalificacion(@Param("peliculaId") UUID peliculaId);

    @Query("SELECT c FROM CalificacionPeliculaEntity c WHERE " +
            "(:usuarioId IS NULL OR c.usuarioId = :usuarioId) AND " +
            "(:peliculaId IS NULL OR c.peliculaId = :peliculaId) AND " +
            "(:puntuacionMinima IS NULL OR c.puntuacion >= :puntuacionMinima) AND " +
            "(:puntuacionMaxima IS NULL OR c.puntuacion <= :puntuacionMaxima) AND " +
            "(:fechaInicio IS NULL OR c.fecha >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR c.fecha <= :fechaFin)")
    List<CalificacionPeliculaEntity> buscarConFiltros(
            @Param("usuarioId") UUID usuarioId,
            @Param("peliculaId") UUID peliculaId,
            @Param("puntuacionMinima") Integer puntuacionMinima,
            @Param("puntuacionMaxima") Integer puntuacionMaxima,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
}
