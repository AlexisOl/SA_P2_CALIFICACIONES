package com.sa.calificacion.calificacionpelicula.aplicaion.puerto.entrada;

import com.sa.calificacion.calificacionpelicula.aplicaion.dto.CrearCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;

public interface CrearCalificacionPeliculaInputPort {

    CalificacionPelicula crearCalificacion(CrearCalificacionPeliculaDTO dto);
}
