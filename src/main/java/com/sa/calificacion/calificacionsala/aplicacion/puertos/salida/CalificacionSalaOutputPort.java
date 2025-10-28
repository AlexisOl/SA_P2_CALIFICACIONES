package com.sa.calificacion.calificacionsala.aplicacion.puertos.salida;

import com.sa.calificacion.calificacionsala.aplicacion.dto.FiltroCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;

import java.util.List;
import java.util.UUID;

public interface CalificacionSalaOutputPort {

    CalificacionSala guardarCalificacion(CalificacionSala calificacion);
    CalificacionSala obtenerCalificacionPorId(UUID id);
    List<CalificacionSala> listarCalificaciones(FiltroCalificacionSalaDTO filtro);
    Double obtenerPromedioCalificacion(UUID salaId);
    boolean existeCalificacion(UUID usuarioId, UUID salaId);
}
