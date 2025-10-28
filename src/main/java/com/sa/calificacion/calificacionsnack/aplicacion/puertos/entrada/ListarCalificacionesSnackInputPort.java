package com.sa.calificacion.calificacionsnack.aplicacion.puertos.entrada;

import com.sa.calificacion.calificacionsnack.aplicacion.dto.FiltroCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;

import java.util.List;
import java.util.UUID;

public interface ListarCalificacionesSnackInputPort {

    List<CalificacionSnack> listarCalificaciones(FiltroCalificacionSnackDTO filtro);
    CalificacionSnack obtenerCalificacionPorId(UUID id);
    Double obtenerPromedioCalificacion(UUID snackId);
}
