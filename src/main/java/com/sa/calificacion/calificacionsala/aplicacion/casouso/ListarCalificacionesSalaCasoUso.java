package com.sa.calificacion.calificacionsala.aplicacion.casouso;

import com.sa.calificacion.calificacionsala.aplicacion.dto.FiltroCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.aplicacion.puertos.entrada.ListarCalificacionesSalaInputPort;

import com.sa.calificacion.calificacionsala.aplicacion.puertos.salida.CalificacionSalaOutputPort;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ListarCalificacionesSalaCasoUso implements ListarCalificacionesSalaInputPort {

    private final CalificacionSalaOutputPort calificacionSalaOutputPort;

    public ListarCalificacionesSalaCasoUso(CalificacionSalaOutputPort calificacionSalaOutputPort) {
        this.calificacionSalaOutputPort = calificacionSalaOutputPort;
    }

    @Override
    public List<CalificacionSala> listarCalificaciones(FiltroCalificacionSalaDTO filtro) {
        return calificacionSalaOutputPort.listarCalificaciones(filtro);
    }

    @Override
    public CalificacionSala obtenerCalificacionPorId(UUID id) {
        CalificacionSala calificacion = calificacionSalaOutputPort.obtenerCalificacionPorId(id);
        if (calificacion == null) {
            throw new IllegalArgumentException("La calificación no existe");
        }
        return calificacion;
    }

    @Override
    public Double obtenerPromedioCalificacion(UUID salaId) {
        return calificacionSalaOutputPort.obtenerPromedioCalificacion(salaId);
    }
}
