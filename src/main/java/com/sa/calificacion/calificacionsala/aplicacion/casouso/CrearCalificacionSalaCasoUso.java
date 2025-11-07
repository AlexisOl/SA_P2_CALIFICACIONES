package com.sa.calificacion.calificacionsala.aplicacion.casouso;

import com.sa.calificacion.calificacionsala.aplicacion.dto.CrearCalificacionSalaDTO;
import com.sa.calificacion.calificacionsala.aplicacion.puertos.entrada.CrearCalificacionSalaInputPort;
import com.sa.calificacion.calificacionsala.aplicacion.puertos.salida.CalificacionSalaOutputPort;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;
import com.sa.calificacion.compartido.eventos.puertos.salida.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class CrearCalificacionSalaCasoUso implements CrearCalificacionSalaInputPort {

    private final CalificacionSalaOutputPort calificacionSalaOutputPort;
    private final VerificarUsuarioOutputPort verificarUsuarioOutputPort;
    private final VerificarSalaOutputPort verificarSalaOutputPort;
    private final NotificarCalificacionOutputPort notificarCalificacionOutputPort;

    public CrearCalificacionSalaCasoUso(
            CalificacionSalaOutputPort calificacionSalaOutputPort,
            VerificarUsuarioOutputPort verificarUsuarioOutputPort,
            VerificarSalaOutputPort verificarSalaOutputPort,
            NotificarCalificacionOutputPort notificarCalificacionOutputPort) {
        this.calificacionSalaOutputPort = calificacionSalaOutputPort;
        this.verificarUsuarioOutputPort = verificarUsuarioOutputPort;
        this.verificarSalaOutputPort = verificarSalaOutputPort;
        this.notificarCalificacionOutputPort = notificarCalificacionOutputPort;
    }

    @Override
    @Transactional
    public CalificacionSala crearCalificacion(CrearCalificacionSalaDTO dto) {
        boolean usuarioExiste = verificarUsuarioExistente(dto.getUsuarioId());
        if (!usuarioExiste) {
            throw new IllegalArgumentException("El usuario no existe");
        }

//        boolean salaExiste = verificarSalaExistente(dto.getSalaId());
//        if (!salaExiste) {
//            throw new IllegalArgumentException("La sala no existe");
//        }

//        boolean salaPermiteCalificaciones = verificarSalaPermiteCalificaciones(dto.getSalaId());
//        if (!salaPermiteCalificaciones) {
//            throw new IllegalStateException("Esta sala no permite calificaciones en este momento");
//        }

        if (calificacionSalaOutputPort.existeCalificacion(dto.getUsuarioId(), dto.getSalaId())) {
            throw new IllegalStateException("Ya has calificado esta sala");
        }

        CalificacionSala calificacion = new CalificacionSala(
                UUID.randomUUID(),
                dto.getUsuarioId(),
                dto.getSalaId(),
                dto.getPuntuacion(),
                dto.getComentario(),
                dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now()
        );

        CalificacionSala calificacionGuardada = calificacionSalaOutputPort.guardarCalificacion(calificacion);
        //notificarCalificacionOutputPort.notificarCalificacionSalaCreada(calificacionGuardada);

        return calificacionGuardada;
    }

    private boolean verificarUsuarioExistente(UUID idUsuario) {
        try {
            return verificarUsuarioOutputPort.verificarUsuario(idUsuario).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al verificar usuario", e);
        }
    }

    private boolean verificarSalaExistente(UUID idSala) {
        try {
            return verificarSalaOutputPort.verificarSala(idSala).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al verificar sala", e);
        }
    }

    private boolean verificarSalaPermiteCalificaciones(UUID idSala) {
        try {
            return verificarSalaOutputPort.verificarSalaPermiteCalificaciones(idSala).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error al verificar permisos de sala", e);
        }
    }
}
