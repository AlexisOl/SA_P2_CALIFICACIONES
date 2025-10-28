package com.sa.calificacion.calificacionsnack.infraestructura.entrada.rest;

import com.sa.calificacion.calificacionsnack.aplicacion.dto.CrearCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.aplicacion.dto.FiltroCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.aplicacion.puertos.entrada.CrearCalificacionSnackInputPort;
import com.sa.calificacion.calificacionsnack.aplicacion.puertos.entrada.ListarCalificacionesSnackInputPort;
import com.sa.calificacion.calificacionsnack.infraestructura.entrada.rest.dto.ResponseCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.infraestructura.entrada.rest.mapper.CalificacionSnackRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Calificaciones de Snacks", description = "API para gestión de calificaciones de snacks")
@RestController
@AllArgsConstructor
@RequestMapping("/snacks")
public class CalificacionSnackRestAdapter {

    private final CrearCalificacionSnackInputPort crearCalificacionInputPort;
    private final ListarCalificacionesSnackInputPort listarCalificacionesInputPort;
    private final CalificacionSnackRestMapper mapper;

    @Operation(summary = "Crear calificación de snack", description = "Crea una nueva calificación para un snack")
    @PostMapping
    public ResponseEntity<ResponseCalificacionSnackDTO> crearCalificacion(
            @Valid @RequestBody CrearCalificacionSnackDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toResponseDto(
                        crearCalificacionInputPort.crearCalificacion(dto)
                ));
    }

    @Operation(summary = "Listar calificaciones", description = "Lista calificaciones con filtros opcionales")
    @GetMapping
    public ResponseEntity<List<ResponseCalificacionSnackDTO>> listarCalificaciones(
            @RequestParam(required = false) UUID usuarioId,
            @RequestParam(required = false) UUID snackId,
            @RequestParam(required = false) Integer puntuacionMinima,
            @RequestParam(required = false) Integer puntuacionMaxima,
            @RequestParam(required = false) LocalDateTime fechaInicio,
            @RequestParam(required = false) LocalDateTime fechaFin) {

        FiltroCalificacionSnackDTO filtro = new FiltroCalificacionSnackDTO(
                usuarioId, snackId, puntuacionMinima, puntuacionMaxima, fechaInicio, fechaFin
        );

        return ResponseEntity.ok(
                mapper.toResponseDtoList(
                        listarCalificacionesInputPort.listarCalificaciones(filtro)
                )
        );
    }

    @Operation(summary = "Obtener calificación por ID", description = "Obtiene una calificación específica")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCalificacionSnackDTO> obtenerCalificacion(@PathVariable UUID id) {
        return ResponseEntity.ok(
                mapper.toResponseDto(
                        listarCalificacionesInputPort.obtenerCalificacionPorId(id)
                )
        );
    }

    @Operation(summary = "Obtener promedio de calificación", description = "Obtiene el promedio de calificaciones de un snack")
    @GetMapping("/promedio/{snackId}")
    public ResponseEntity<Map<String, Object>> obtenerPromedio(@PathVariable UUID snackId) {
        Double promedio = listarCalificacionesInputPort.obtenerPromedioCalificacion(snackId);
        Map<String, Object> response = new HashMap<>();
        response.put("snackId", snackId);
        response.put("promedio", promedio);
        return ResponseEntity.ok(response);
    }
}
