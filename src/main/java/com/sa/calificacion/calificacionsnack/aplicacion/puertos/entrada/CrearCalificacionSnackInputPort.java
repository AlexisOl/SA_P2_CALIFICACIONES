package com.sa.calificacion.calificacionsnack.aplicacion.puertos.entrada;

import com.sa.calificacion.calificacionsnack.aplicacion.dto.CrearCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;

public interface CrearCalificacionSnackInputPort {

    CalificacionSnack crearCalificacion(CrearCalificacionSnackDTO dto);
}
