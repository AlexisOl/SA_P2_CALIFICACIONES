package com.sa.calificacion.calificacionsala.aplicacion.puertos.entrada;

import com.sa.calificacion.calificacionsala.aplicacion.dto.FiltroCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;

import java.util.List;
import java.util.UUID;

public interface ListarCalificacionesSalaInputPort {

    List<CalificacionSala> listarCalificaciones(FiltroCalificacionSalaDTO filtro);
    CalificacionSala obtenerCalificacionPorId(UUID id);
    Double obtenerPromedioCalificacion(UUID salaId);
}
