package com.sa.calificacion.calificacionpelicula.aplicaion.puerto.entrada;

import com.sa.calificacion.calificacionpelicula.aplicaion.dto.FiltroCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;

import java.util.List;
import java.util.UUID;

public interface ListarCalificacionesPeliculaInputPort {

    List<CalificacionPelicula> listarCalificaciones(FiltroCalificacionPeliculaDTO filtro);
    CalificacionPelicula obtenerCalificacionPorId(UUID id);
    Double obtenerPromedioCalificacion(UUID peliculaId);
}
