package com.sa.calificacion.calificacionsala.infraestructura.salida.repositorio;

import com.sa.calificacion.calificacionsala.infraestructura.salida.entidades.CalificacionSalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CalificacionSalaRepository extends JpaRepository<CalificacionSalaEntity, UUID> {

    List<CalificacionSalaEntity> findBySalaId(UUID salaId);
    List<CalificacionSalaEntity> findByUsuarioId(UUID usuarioId);
    boolean existsByUsuarioIdAndSalaId(UUID usuarioId, UUID salaId);

    @Query("SELECT AVG(c.puntuacion) FROM CalificacionSalaEntity c WHERE c.salaId = :salaId")
    Double obtenerPromedioCalificacion(@Param("salaId") UUID salaId);

    @Query("SELECT c FROM CalificacionSalaEntity c WHERE " +
            "(:usuarioId IS NULL OR c.usuarioId = :usuarioId) AND " +
            "(:salaId IS NULL OR c.salaId = :salaId) AND " +
            "(:puntuacionMinima IS NULL OR c.puntuacion >= :puntuacionMinima) AND " +
            "(:puntuacionMaxima IS NULL OR c.puntuacion <= :puntuacionMaxima) AND " +
            "(:fechaInicio IS NULL OR c.fecha >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR c.fecha <= :fechaFin)")
    List<CalificacionSalaEntity> buscarConFiltros(
            @Param("usuarioId") UUID usuarioId,
            @Param("salaId") UUID salaId,
            @Param("puntuacionMinima") Integer puntuacionMinima,
            @Param("puntuacionMaxima") Integer puntuacionMaxima,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
}
