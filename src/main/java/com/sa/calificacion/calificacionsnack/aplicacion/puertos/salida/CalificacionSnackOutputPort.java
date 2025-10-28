package com.sa.calificacion.calificacionsnack.aplicacion.puertos.salida;

import com.sa.calificacion.calificacionsnack.aplicacion.dto.FiltroCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;

import java.util.List;
import java.util.UUID;

public interface CalificacionSnackOutputPort {

    CalificacionSnack guardarCalificacion(CalificacionSnack calificacion);
    CalificacionSnack obtenerCalificacionPorId(UUID id);
    List<CalificacionSnack> listarCalificaciones(FiltroCalificacionSnackDTO filtro);
    Double obtenerPromedioCalificacion(UUID snackId);
    boolean existeCalificacion(UUID usuarioId, UUID snackId);
}
