package com.sa.calificacion.calificacionpelicula.infraestructura.salida.entrada.rest.mapper;

import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;
import com.sa.calificacion.calificacionpelicula.infraestructura.salida.entrada.rest.dto.ResponseCalificacionPeliculaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CalificacionPeliculaRestMapper {

    ResponseCalificacionPeliculaDTO toResponseDto(CalificacionPelicula calificacion);
    List<ResponseCalificacionPeliculaDTO> toResponseDtoList(List<CalificacionPelicula> calificaciones);

}
