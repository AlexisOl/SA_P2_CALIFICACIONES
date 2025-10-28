package com.sa.calificacion.calificacionpelicula.infraestructura.salida.mapper;

import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;
import com.sa.calificacion.calificacionpelicula.infraestructura.salida.entidades.CalificacionPeliculaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalificacionPeliculaMapper {

    CalificacionPeliculaEntity toEntity(CalificacionPelicula calificacion);
    CalificacionPelicula toDomain(CalificacionPeliculaEntity entity);
    List<CalificacionPelicula> toDomainList(List<CalificacionPeliculaEntity> entities);
}
