package com.sa.calificacion.calificacionpelicula.aplicaion.puerto.salida;

import com.sa.calificacion.calificacionpelicula.aplicaion.dto.FiltroCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;

import java.util.List;
import java.util.UUID;

public interface CalificacionPeliculaOutputPort {

    CalificacionPelicula guardarCalificacion(CalificacionPelicula calificacion);
    CalificacionPelicula obtenerCalificacionPorId(UUID id);
    List<CalificacionPelicula> listarCalificaciones(FiltroCalificacionPeliculaDTO filtro);
    Double obtenerPromedioCalificacion(UUID peliculaId);
    boolean existeCalificacion(UUID usuarioId, UUID peliculaId);
}
