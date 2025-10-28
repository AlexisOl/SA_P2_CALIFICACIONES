package com.sa.calificacion.compartido.eventos.puertos.salida;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface VerificarPeliculaOutputPort {

    CompletableFuture<Boolean> verificarPelicula(UUID idPelicula);
}
