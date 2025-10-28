package com.sa.calificacion.calificacionpelicula.aplicaion.casouso;

import com.sa.calificacion.calificacionpelicula.aplicaion.dto.FiltroCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.aplicaion.puerto.entrada.ListarCalificacionesPeliculaInputPort;
import com.sa.calificacion.calificacionpelicula.aplicaion.puerto.salida.CalificacionPeliculaOutputPort;
import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarCalificacionesPeliculaCasoUso implements ListarCalificacionesPeliculaInputPort {

    private final CalificacionPeliculaOutputPort calificacionPeliculaOutputPort;

    public ListarCalificacionesPeliculaCasoUso(CalificacionPeliculaOutputPort calificacionPeliculaOutputPort) {
        this.calificacionPeliculaOutputPort = calificacionPeliculaOutputPort;
    }

    @Override
    public List<CalificacionPelicula> listarCalificaciones(FiltroCalificacionPeliculaDTO filtro) {
        return calificacionPeliculaOutputPort.listarCalificaciones(filtro);
    }

    @Override
    public CalificacionPelicula obtenerCalificacionPorId(UUID id) {
        CalificacionPelicula calificacion = calificacionPeliculaOutputPort.obtenerCalificacionPorId(id);
        if (calificacion == null) {
            throw new IllegalArgumentException("La calificación no existe");
        }
        return calificacion;
    }

    @Override
    public Double obtenerPromedioCalificacion(UUID peliculaId) {
        return calificacionPeliculaOutputPort.obtenerPromedioCalificacion(peliculaId);
    }
}
