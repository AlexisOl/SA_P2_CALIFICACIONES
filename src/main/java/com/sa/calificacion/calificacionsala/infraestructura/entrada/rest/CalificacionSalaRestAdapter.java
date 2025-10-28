package com.sa.calificacion.calificacionsala.infraestructura.entrada.rest;

import com.sa.calificacion.calificacionsala.aplicacion.dto.CrearCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.aplicacion.dto.FiltroCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.aplicacion.puertos.entrada.CrearCalificacionSalaInputPort;
import com.sa.calificacion.calificacionsala.aplicacion.puertos.entrada.ListarCalificacionesSalaInputPort;
import com.sa.calificacion.calificacionsala.infraestructura.entrada.rest.dto.ResponseCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.infraestructura.entrada.rest.mapper.CalificacionSalaRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@Tag(name = "Calificaciones de Salas", description = "API para gestión de calificaciones de salas de cine")
@RestController
@AllArgsConstructor
@RequestMapping("/salas")
public class CalificacionSalaRestAdapter {

    private final CrearCalificacionSalaInputPort crearCalificacionInputPort;
    private final ListarCalificacionesSalaInputPort listarCalificacionesInputPort;
    private final CalificacionSalaRestMapper mapper;

    @Operation(summary = "Crear calificación de sala")
    @PostMapping
    public ResponseEntity<ResponseCalificacionSalaDTO> crearCalificacion(
            @Valid @RequestBody CrearCalificacionSalaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toResponseDto(
                        crearCalificacionInputPort.crearCalificacion(dto)
                ));
    }

    @Operation(summary = "Listar calificaciones")
    @GetMapping
    public ResponseEntity<List<ResponseCalificacionSalaDTO>> listarCalificaciones(
            @RequestParam(required = false) UUID usuarioId,
            @RequestParam(required = false) UUID salaId,
            @RequestParam(required = false) Integer puntuacionMinima,
            @RequestParam(required = false) Integer puntuacionMaxima,
            @RequestParam(required = false) LocalDateTime fechaInicio,
            @RequestParam(required = false) LocalDateTime fechaFin) {

        FiltroCalificacionSalaDTO filtro = new FiltroCalificacionSalaDTO(
                usuarioId, salaId, puntuacionMinima, puntuacionMaxima, fechaInicio, fechaFin
        );

        return ResponseEntity.ok(
                mapper.toResponseDtoList(
                        listarCalificacionesInputPort.listarCalificaciones(filtro)
                )
        );
    }

    @Operation(summary = "Obtener calificación por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCalificacionSalaDTO> obtenerCalificacion(@PathVariable UUID id) {
        return ResponseEntity.ok(
                mapper.toResponseDto(
                        listarCalificacionesInputPort.obtenerCalificacionPorId(id)
                )
        );
    }

    @Operation(summary = "Obtener promedio de calificación")
    @GetMapping("/promedio/{salaId}")
    public ResponseEntity<Map<String, Object>> obtenerPromedio(@PathVariable UUID salaId) {
        Double promedio = listarCalificacionesInputPort.obtenerPromedioCalificacion(salaId);
        Map<String, Object> response = new HashMap<>();
        response.put("salaId", salaId);
        response.put("promedio", promedio);
        return ResponseEntity.ok(response);
    }
}
