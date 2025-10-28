package com.sa.calificacion.calificacionpelicula.infraestructura.salida.adaptador;

import com.sa.calificacion.calificacionpelicula.aplicaion.dto.FiltroCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.aplicaion.puerto.salida.CalificacionPeliculaOutputPort;
import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;
import com.sa.calificacion.calificacionpelicula.infraestructura.salida.mapper.CalificacionPeliculaMapper;
import com.sa.calificacion.calificacionpelicula.infraestructura.salida.repositorio.CalificacionPeliculaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CalificacionPeliculaPersistenciaAdaptador implements CalificacionPeliculaOutputPort {

    private final CalificacionPeliculaRepository repository;
    private final CalificacionPeliculaMapper mapper;

    @Override
    @Transactional
    public CalificacionPelicula guardarCalificacion(CalificacionPelicula calificacion) {
        return mapper.toDomain(
                repository.save(mapper.toEntity(calificacion))
        );
    }

    @Override
    public CalificacionPelicula obtenerCalificacionPorId(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<CalificacionPelicula> listarCalificaciones(FiltroCalificacionPeliculaDTO filtro) {
        return mapper.toDomainList(
                repository.buscarConFiltros(
                        filtro.getUsuarioId(),
                        filtro.getPeliculaId(),
                        filtro.getPuntuacionMinima(),
                        filtro.getPuntuacionMaxima(),
                        filtro.getFechaInicio(),
                        filtro.getFechaFin()
                )
        );
    }

    @Override
    public Double obtenerPromedioCalificacion(UUID peliculaId) {
        Double promedio = repository.obtenerPromedioCalificacion(peliculaId);
        return promedio != null ? promedio : 0.0;
    }

    @Override
    public boolean existeCalificacion(UUID usuarioId, UUID peliculaId) {
        return repository.existsByUsuarioIdAndPeliculaId(usuarioId, peliculaId);
    }
}
