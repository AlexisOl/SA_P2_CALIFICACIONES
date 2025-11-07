package com.sa.calificacion.calificacionpelicula.aplicaion.casouso;

import com.sa.calificacion.calificacionpelicula.aplicaion.dto.CrearCalificacionPeliculaDTO;
import com.sa.calificacion.calificacionpelicula.aplicaion.puerto.entrada.CrearCalificacionPeliculaInputPort;
import com.sa.calificacion.calificacionpelicula.aplicaion.puerto.salida.CalificacionPeliculaOutputPort;
import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;
import com.sa.calificacion.compartido.eventos.puertos.salida.NotificarCalificacionOutputPort;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarPeliculaOutputPort;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarUsuarioOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class CrearCalificacionPeliculaCasoUso implements CrearCalificacionPeliculaInputPort {

    private final CalificacionPeliculaOutputPort calificacionPeliculaOutputPort;
    private final VerificarUsuarioOutputPort verificarUsuarioOutputPort;
    private final VerificarPeliculaOutputPort verificarPeliculaOutputPort;
    private final NotificarCalificacionOutputPort notificarCalificacionOutputPort;

    public CrearCalificacionPeliculaCasoUso(
            CalificacionPeliculaOutputPort calificacionPeliculaOutputPort,
            VerificarUsuarioOutputPort verificarUsuarioOutputPort,
            VerificarPeliculaOutputPort verificarPeliculaOutputPort,
            NotificarCalificacionOutputPort notificarCalificacionOutputPort) {
        this.calificacionPeliculaOutputPort = calificacionPeliculaOutputPort;
        this.verificarUsuarioOutputPort = verificarUsuarioOutputPort;
        this.verificarPeliculaOutputPort = verificarPeliculaOutputPort;
        this.notificarCalificacionOutputPort = notificarCalificacionOutputPort;
    }

    @Override
    @Transactional
    public CalificacionPelicula crearCalificacion(CrearCalificacionPeliculaDTO dto) {

        // Verificar que el usuario no haya calificado ya esta película
        if (calificacionPeliculaOutputPort.existeCalificacion(dto.getUsuarioId(), dto.getPeliculaId())) {
            throw new IllegalStateException("Ya has calificado esta película");
        }

        // Crear la calificación
        CalificacionPelicula calificacion = new CalificacionPelicula(
                UUID.randomUUID(),
                dto.getUsuarioId(),
                dto.getPeliculaId(),
                dto.getPuntuacion(),
                dto.getComentario(),
                dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now()
        );

        CalificacionPelicula calificacionGuardada = calificacionPeliculaOutputPort.guardarCalificacion(calificacion);


        return calificacionGuardada;
    }

    private boolean verificarUsuarioExistente(UUID idUsuario) {
        try {
            return verificarUsuarioOutputPort.verificarUsuario(idUsuario).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al verificar usuario", e);
        }
    }

    private boolean verificarPeliculaExistente(UUID idPelicula) {
        try {
            return verificarPeliculaOutputPort.verificarPelicula(idPelicula).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al verificar película", e);
        }
    }
}
