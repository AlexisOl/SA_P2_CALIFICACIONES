package com.sa.calificacion.compartido.eventos.puertos.salida;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface VerificarUsuarioOutputPort {

    CompletableFuture<Boolean> verificarUsuario(UUID idUsuario);
}
