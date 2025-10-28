package com.sa.calificacion.compartido.eventos.puertos.salida;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface VerificarSnackOutputPort {

    CompletableFuture<Boolean> verificarSnack(UUID idSnack);
}
