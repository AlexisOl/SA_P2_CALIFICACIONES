package com.sa.calificacion.calificacionsala.infraestructura.entrada.rest.mapper;

import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;
import com.sa.calificacion.calificacionsala.infraestructura.entrada.rest.dto.ResponseCalificacionSalaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CalificacionSalaRestMapper {

    ResponseCalificacionSalaDTO toResponseDto(CalificacionSala calificacion);
    List<ResponseCalificacionSalaDTO> toResponseDtoList(List<CalificacionSala> calificaciones);
}
