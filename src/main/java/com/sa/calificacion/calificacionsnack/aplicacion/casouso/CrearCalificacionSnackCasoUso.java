package com.sa.calificacion.calificacionsnack.aplicacion.casouso;

import com.sa.calificacion.calificacionsnack.aplicacion.dto.CrearCalificacionSnackDTO;
import com.sa.calificacion.calificacionsnack.aplicacion.puertos.entrada.CrearCalificacionSnackInputPort;
import com.sa.calificacion.calificacionsnack.aplicacion.puertos.salida.CalificacionSnackOutputPort;
import com.sa.calificacion.calificacionsnack.dominio.CalificacionSnack;
import com.sa.calificacion.compartido.eventos.puertos.salida.NotificarCalificacionOutputPort;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarSnackOutputPort;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarUsuarioOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class CrearCalificacionSnackCasoUso implements CrearCalificacionSnackInputPort {

    private final CalificacionSnackOutputPort calificacionSnackOutputPort;
    private final VerificarUsuarioOutputPort verificarUsuarioOutputPort;
    private final VerificarSnackOutputPort verificarSnackOutputPort;
    private final NotificarCalificacionOutputPort notificarCalificacionOutputPort;

    public CrearCalificacionSnackCasoUso(
            CalificacionSnackOutputPort calificacionSnackOutputPort,
            VerificarUsuarioOutputPort verificarUsuarioOutputPort,
            VerificarSnackOutputPort verificarSnackOutputPort,
            NotificarCalificacionOutputPort notificarCalificacionOutputPort) {
        this.calificacionSnackOutputPort = calificacionSnackOutputPort;
        this.verificarUsuarioOutputPort = verificarUsuarioOutputPort;
        this.verificarSnackOutputPort = verificarSnackOutputPort;
        this.notificarCalificacionOutputPort = notificarCalificacionOutputPort;
    }

    @Override
    @Transactional
    public CalificacionSnack crearCalificacion(CrearCalificacionSnackDTO dto) {
        // Verificar que el usuario existe
//        boolean usuarioExiste = verificarUsuarioExistente(dto.getUsuarioId());
//        if (!usuarioExiste) {
//            throw new IllegalArgumentException("El usuario no existe");
//        }
//
//        // Verificar que el snack existe
//        boolean snackExiste = verificarSnackExistente(dto.getSnackId());
//        if (!snackExiste) {
//            throw new IllegalArgumentException("El snack no existe");
//        }

        // Verificar que el usuario no haya calificado ya este snack
        if (calificacionSnackOutputPort.existeCalificacion(dto.getUsuarioId(), dto.getSnackId())) {
            throw new IllegalStateException("Ya has calificado este snack");
        }

        // Crear la calificación
        CalificacionSnack calificacion = new CalificacionSnack(
                UUID.randomUUID(),
                dto.getUsuarioId(),
                dto.getSnackId(),
                dto.getPuntuacion(),
                dto.getComentario(),
                dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now()
        );

        CalificacionSnack calificacionGuardada = calificacionSnackOutputPort.guardarCalificacion(calificacion);

        // Notificar que se creó una calificación
        //notificarCalificacionOutputPort.notificarCalificacionSnackCreada(calificacionGuardada);

        return calificacionGuardada;
    }

    private boolean verificarUsuarioExistente(UUID idUsuario) {
        try {
            return verificarUsuarioOutputPort.verificarUsuario(idUsuario).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al verificar usuario", e);
        }
    }

    private boolean verificarSnackExistente(UUID idSnack) {
        try {
            return verificarSnackOutputPort.verificarSnack(idSnack).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al verificar snack", e);
        }
    }
}
