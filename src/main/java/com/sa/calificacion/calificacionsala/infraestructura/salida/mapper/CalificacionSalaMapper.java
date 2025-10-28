package com.sa.calificacion.calificacionsala.infraestructura.salida.mapper;

import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;
import com.sa.calificacion.calificacionsala.infraestructura.salida.entidades.CalificacionSalaEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CalificacionSalaMapper {

    CalificacionSalaEntity toEntity(CalificacionSala calificacion);
    CalificacionSala toDomain(CalificacionSalaEntity entity);
    List<CalificacionSala> toDomainList(List<CalificacionSalaEntity> entities);
}
