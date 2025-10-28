package com.sa.calificacion.calificacionsnack.infraestructura.entrada.rest.mapper;

import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;
import com.sa.calificacion.calificacionsnack.infraestructura.entrada.rest.dto.ResponseCalificacionSnackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CalificacionSnackRestMapper {

    ResponseCalificacionSnackDTO toResponseDto(CalificacionSnack calificacion);
    List<ResponseCalificacionSnackDTO> toResponseDtoList(List<CalificacionSnack> calificaciones);
}
