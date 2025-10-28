package com.sa.calificacion.calificacionpelicula.infraestructura.salida.entrada.rest;

import com.sa.calificacion.calificacionpelicula.aplicaion.dto.CrearCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.aplicaion.dto.FiltroCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.aplicaion.puerto.entrada.CrearCalificacionPeliculaInputPort;
import com.sa.calificacion.calificacionpelicula.aplicaion.puerto.entrada.ListarCalificacionesPeliculaInputPort;
import com.sa.calificacion.calificacionpelicula.infraestructura.salida.entrada.rest.dto.ResponseCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.infraestructura.salida.entrada.rest.mapper.CalificacionPeliculaRestMapper;
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

@Tag(name = "Calificaciones de Películas", description = "API para gestión de calificaciones de películas")
@RestController
@AllArgsConstructor
@RequestMapping("/peliculas")
public class CalificacionPeliculaRestAdapter {

    private final CrearCalificacionPeliculaInputPort crearCalificacionInputPort;
    private final ListarCalificacionesPeliculaInputPort listarCalificacionesInputPort;
    private final CalificacionPeliculaRestMapper mapper;

    @Operation(summary = "Crear calificación de película", description = "Crea una nueva calificación para una película")
    @PostMapping
    public ResponseEntity<ResponseCalificacionPeliculaDTO> crearCalificacion(
            @Valid @RequestBody CrearCalificacionPeliculaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toResponseDto(
                        crearCalificacionInputPort.crearCalificacion(dto)
                ));
    }

    @Operation(summary = "Listar calificaciones", description = "Lista calificaciones con filtros opcionales")
    @GetMapping
    public ResponseEntity<List<ResponseCalificacionPeliculaDTO>> listarCalificaciones(
            @RequestParam(required = false) UUID usuarioId,
            @RequestParam(required = false) UUID peliculaId,
            @RequestParam(required = false) Integer puntuacionMinima,
            @RequestParam(required = false) Integer puntuacionMaxima,
            @RequestParam(required = false) LocalDateTime fechaInicio,
            @RequestParam(required = false) LocalDateTime fechaFin) {

        FiltroCalificacionPeliculaDTO filtro = new FiltroCalificacionPeliculaDTO(
                usuarioId, peliculaId, puntuacionMinima, puntuacionMaxima, fechaInicio, fechaFin
        );

        return ResponseEntity.ok(
                mapper.toResponseDtoList(
                        listarCalificacionesInputPort.listarCalificaciones(filtro)
                )
        );
    }

    @Operation(summary = "Obtener calificación por ID", description = "Obtiene una calificación específica")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCalificacionPeliculaDTO> obtenerCalificacion(@PathVariable UUID id) {
        return ResponseEntity.ok(
                mapper.toResponseDto(
                        listarCalificacionesInputPort.obtenerCalificacionPorId(id)
                )
        );
    }

    @Operation(summary = "Obtener promedio de calificación", description = "Obtiene el promedio de calificaciones de una película")
    @GetMapping("/promedio/{peliculaId}")
    public ResponseEntity<Map<String, Object>> obtenerPromedio(@PathVariable UUID peliculaId) {
        Double promedio = listarCalificacionesInputPort.obtenerPromedioCalificacion(peliculaId);
        Map<String, Object> response = new HashMap<>();
        response.put("peliculaId", peliculaId);
        response.put("promedio", promedio);
        return ResponseEntity.ok(response);
    }
}
