package com.sa.calificacion.calificacionsala.infraestructura.salida.adaptador;

import com.sa.calificacion.calificacionsala.aplicacion.dto.FiltroCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.aplicacion.puertos.salida.CalificacionSalaOutputPort;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;
import com.sa.calificacion.calificacionsala.infraestructura.salida.mapper.CalificacionSalaMapper;
import com.sa.calificacion.calificacionsala.infraestructura.salida.repositorio.CalificacionSalaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CalificacionSalaPersistenciaAdaptador implements CalificacionSalaOutputPort {

    private final CalificacionSalaRepository repository;
    private final CalificacionSalaMapper mapper;

    @Override
    @Transactional
    public CalificacionSala guardarCalificacion(CalificacionSala calificacion) {
        return mapper.toDomain(
                repository.save(mapper.toEntity(calificacion))
        );
    }

    @Override
    public CalificacionSala obtenerCalificacionPorId(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<CalificacionSala> listarCalificaciones(FiltroCalificacionSalaDTO filtro) {
        return mapper.toDomainList(
                repository.buscarConFiltros(
                        filtro.getUsuarioId(),
                        filtro.getSalaId(),
                        filtro.getPuntuacionMinima(),
                        filtro.getPuntuacionMaxima(),
                        filtro.getFechaInicio(),
                        filtro.getFechaFin()
                )
        );
    }

    @Override
    public Double obtenerPromedioCalificacion(UUID salaId) {
        Double promedio = repository.obtenerPromedioCalificacion(salaId);
        return promedio != null ? promedio : 0.0;
    }

    @Override
    public boolean existeCalificacion(UUID usuarioId, UUID salaId) {
        return repository.existsByUsuarioIdAndSalaId(usuarioId, salaId);
    }
}
