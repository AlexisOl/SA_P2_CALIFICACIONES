package com.sa.calificacion.compartido.eventos.puertos.salida;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface VerificarSalaOutputPort {

    CompletableFuture<Boolean> verificarSala(UUID idSala);
    CompletableFuture<Boolean> verificarSalaPermiteCalificaciones(UUID idSala);
}
