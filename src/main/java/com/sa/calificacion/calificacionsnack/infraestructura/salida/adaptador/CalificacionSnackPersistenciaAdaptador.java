package com.sa.calificacion.calificacionsnack.infraestructura.salida.adaptador;

import com.sa.calificacion.calificacionsnack.aplicacion.dto.FiltroCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.aplicacion.puertos.salida.CalificacionSnackOutputPort;
import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;
import com.sa.calificacion.calificacionsnack.infraestructura.salida.mapper.CalificacionSnackMapper;
import com.sa.calificacion.calificacionsnack.infraestructura.salida.repositorio.CalificacionSnackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CalificacionSnackPersistenciaAdaptador implements CalificacionSnackOutputPort {

    private final CalificacionSnackRepository repository;
    private final CalificacionSnackMapper mapper;

    @Override
    @Transactional
    public CalificacionSnack guardarCalificacion(CalificacionSnack calificacion) {
        return mapper.toDomain(
                repository.save(mapper.toEntity(calificacion))
        );
    }

    @Override
    public CalificacionSnack obtenerCalificacionPorId(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<CalificacionSnack> listarCalificaciones(FiltroCalificacionSnackDTO filtro) {
        return mapper.toDomainList(
                repository.buscarConFiltros(
                        filtro.getUsuarioId(),
                        filtro.getSnackId(),
                        filtro.getPuntuacionMinima(),
                        filtro.getPuntuacionMaxima(),
                        filtro.getFechaInicio(),
                        filtro.getFechaFin()
                )
        );
    }

    @Override
    public Double obtenerPromedioCalificacion(UUID snackId) {
        Double promedio = repository.obtenerPromedioCalificacion(snackId);
        return promedio != null ? promedio : 0.0;
    }

    @Override
    public boolean existeCalificacion(UUID usuarioId, UUID snackId) {
        return repository.existsByUsuarioIdAndSnackId(usuarioId, snackId);
    }
}
