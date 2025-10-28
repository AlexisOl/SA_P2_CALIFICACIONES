package com.sa.calificacion.calificacionsala.aplicacion.puertos.entrada;

import com.sa.calificacion.calificacionsala.aplicacion.dto.CrearCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;

public interface CrearCalificacionSalaInputPort {

    CalificacionSala crearCalificacion(CrearCalificacionSalaDTO dto);
}
