package com.sa.calificacion.calificacionsnack.infraestructura.salida.mapper;

import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;
import com.sa.calificacion.calificacionsnack.infraestructura.salida.entidades.CalificacionSnackEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalificacionSnackMapper {

    CalificacionSnackEntity toEntity(CalificacionSnack calificacion);
    CalificacionSnack toDomain(CalificacionSnackEntity entity);
    List<CalificacionSnack> toDomainList(List<CalificacionSnackEntity> entities);
}
