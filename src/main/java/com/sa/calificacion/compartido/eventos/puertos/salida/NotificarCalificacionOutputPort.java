package com.sa.calificacion.compartido.eventos.puertos.salida;

import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;

public interface NotificarCalificacionOutputPort {

    void notificarCalificacionPeliculaCreada(CalificacionPelicula calificacion);
    void notificarCalificacionSalaCreada(CalificacionSala calificacion);
    void notificarCalificacionSnackCreada(Object calificacion);
}
