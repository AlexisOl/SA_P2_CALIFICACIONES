package com.sa.calificacion.compartido.eventos.adaptador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarPeliculaOutputPort;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarSalaOutputPort;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarSnackOutputPort;
import com.sa.calificacion.compartido.eventos.puertos.salida.VerificarUsuarioOutputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import com.example.comun.DTO.eventos.VerificarDTO;
import com.example.comun.DTO.eventos.VerificarRespuestaDTO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventosKafkaAdaptador implements
        VerificarUsuarioOutputPort,
        VerificarPeliculaOutputPort,
        VerificarSalaOutputPort,
        VerificarSnackOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<String, CompletableFuture<Boolean>> verificacionFutures = new ConcurrentHashMap<>();

    public EventosKafkaAdaptador(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    // Listeners para respuestas
    @KafkaListener(topics = "respuesta-verificar-usuario", groupId = "calificaciones-group")
    public void handleUsuarioVerificationResponse(
            @Payload String mensaje,
            @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId) throws Exception {
        if (correlationId == null) return;

        VerificarRespuestaDTO respuesta = objectMapper.readValue(mensaje, VerificarRespuestaDTO.class);
        CompletableFuture<Boolean> future = verificacionFutures.remove(correlationId);
        if (future != null) {
            future.complete(respuesta.isExiste());
        }
    }

    @KafkaListener(topics = "respuesta-verificar-pelicula", groupId = "calificaciones-group")
    public void handlePeliculaVerificationResponse(
            @Payload String mensaje,
            @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId) throws Exception {
        if (correlationId == null) return;

        VerificarRespuestaDTO respuesta = objectMapper.readValue(mensaje, VerificarRespuestaDTO.class);
        CompletableFuture<Boolean> future = verificacionFutures.remove(correlationId);
        if (future != null) {
            future.complete(respuesta.isExiste());
        }
    }

    @KafkaListener(topics = "respuesta-verificar-sala", groupId = "calificaciones-group")
    public void handleSalaVerificationResponse(
            @Payload String mensaje,
            @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId) throws Exception {
        if (correlationId == null) return;

        VerificarRespuestaDTO respuesta = objectMapper.readValue(mensaje, VerificarRespuestaDTO.class);
        CompletableFuture<Boolean> future = verificacionFutures.remove(correlationId);
        if (future != null) {
            future.complete(respuesta.isExiste());
        }
    }

    @KafkaListener(topics = "respuesta-verificar-snack", groupId = "calificaciones-group")
    public void handleSnackVerificationResponse(
            @Payload String mensaje,
            @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId) throws Exception {
        if (correlationId == null) return;

        VerificarRespuestaDTO respuesta = objectMapper.readValue(mensaje, VerificarRespuestaDTO.class);
        CompletableFuture<Boolean> future = verificacionFutures.remove(correlationId);
        if (future != null) {
            future.complete(respuesta.isExiste());
        }
    }

    @KafkaListener(topics = "respuesta-sala-permite-calificaciones", groupId = "calificaciones-group")
    public void handleSalaPermiteCalificacionesResponse(
            @Payload String mensaje,
            @Header(value = KafkaHeaders.CORRELATION_ID, required = false) String correlationId) throws Exception {
        if (correlationId == null) return;

        VerificarRespuestaDTO respuesta = objectMapper.readValue(mensaje, VerificarRespuestaDTO.class);
        CompletableFuture<Boolean> future = verificacionFutures.remove(correlationId);
        if (future != null) {
            future.complete(respuesta.isExiste());
        }
    }

    @Override
    public CompletableFuture<Boolean> verificarUsuario(UUID idUsuario) {
        return enviarVerificacion(idUsuario, "verificar-usuario");
    }

    @Override
    public CompletableFuture<Boolean> verificarPelicula(UUID idPelicula) {
        return enviarVerificacion(idPelicula, "verificar-pelicula");
    }

    @Override
    public CompletableFuture<Boolean> verificarSala(UUID idSala) {
        return enviarVerificacion(idSala, "verificar-sala");
    }

    @Override
    public CompletableFuture<Boolean> verificarSalaPermiteCalificaciones(UUID idSala) {
        return enviarVerificacion(idSala, "verificar-sala-permite-calificaciones");
    }

    @Override
    public CompletableFuture<Boolean> verificarSnack(UUID idSnack) {
        return enviarVerificacion(idSnack, "verificar-snack");
    }

    private CompletableFuture<Boolean> enviarVerificacion(UUID id, String topic) {
        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        verificacionFutures.put(correlationId, future);

        try {
            String mensaje = objectMapper.writeValueAsString(new VerificarDTO(id, correlationId));
            Message<String> kafkaMessage = MessageBuilder
                    .withPayload(mensaje)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();
            kafkaTemplate.send(kafkaMessage);
        } catch (Exception e) {
            verificacionFutures.remove(correlationId);
            future.completeExceptionally(e);
        }

        return future;
    }
}
