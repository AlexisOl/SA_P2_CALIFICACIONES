package com.sa.calificacion.calificacionsnack.infraestructura.salida.repositorio;

import com.sa.calificacion.calificacionsnack.infraestructura.salida.entidades.CalificacionSnackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CalificacionSnackRepository extends JpaRepository<CalificacionSnackEntity, UUID> {

    List<CalificacionSnackEntity> findBySnackId(UUID snackId);
    List<CalificacionSnackEntity> findByUsuarioId(UUID usuarioId);
    boolean existsByUsuarioIdAndSnackId(UUID usuarioId, UUID snackId);

    @Query("SELECT AVG(c.puntuacion) FROM CalificacionSnackEntity c WHERE c.snackId = :snackId")
    Double obtenerPromedioCalificacion(@Param("snackId") UUID snackId);

    @Query("SELECT c FROM CalificacionSnackEntity c WHERE " +
            "(:usuarioId IS NULL OR c.usuarioId = :usuarioId) AND " +
            "(:snackId IS NULL OR c.snackId = :snackId) AND " +
            "(:puntuacionMinima IS NULL OR c.puntuacion >= :puntuacionMinima) AND " +
            "(:puntuacionMaxima IS NULL OR c.puntuacion <= :puntuacionMaxima) AND " +
            "(:fechaInicio IS NULL OR c.fecha >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR c.fecha <= :fechaFin)")
    List<CalificacionSnackEntity> buscarConFiltros(
            @Param("usuarioId") UUID usuarioId,
            @Param("snackId") UUID snackId,
            @Param("puntuacionMinima") Integer puntuacionMinima,
            @Param("puntuacionMaxima") Integer puntuacionMaxima,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
}
