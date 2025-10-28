package com.sa.calificacion.calificacionsnack.aplicacion.casouso;

import com.sa.calificacion.calificacionsnack.aplicacion.dto.FiltroCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.aplicacion.puertos.entrada.ListarCalificacionesSnackInputPort;
import com.sa.calificacion.calificacionsnack.aplicacion.puertos.salida.CalificacionSnackOutputPort;
import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarCalificacionesSnackCasoUso implements ListarCalificacionesSnackInputPort {

    private final CalificacionSnackOutputPort calificacionSnackOutputPort;

    public ListarCalificacionesSnackCasoUso(CalificacionSnackOutputPort calificacionSnackOutputPort) {
        this.calificacionSnackOutputPort = calificacionSnackOutputPort;
    }

    @Override
    public List<CalificacionSnack> listarCalificaciones(FiltroCalificacionSnackDTO filtro) {
        return calificacionSnackOutputPort.listarCalificaciones(filtro);
    }

    @Override
    public CalificacionSnack obtenerCalificacionPorId(UUID id) {
        CalificacionSnack calificacion = calificacionSnackOutputPort.obtenerCalificacionPorId(id);
        if (calificacion == null) {
            throw new IllegalArgumentException("La calificación no existe");
        }
        return calificacion;
    }

    @Override
    public Double obtenerPromedioCalificacion(UUID snackId) {
        return calificacionSnackOutputPort.obtenerPromedioCalificacion(snackId);
    }
}
