package com.sa.calificacion.compartido.eventos.adaptador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.calificacion.calificacionpelicula.dominio.CalificacionPelicula;
import com.sa.calificacion.calificacionsala.dominio.CalificacionSala;
import com.sa.calificacion.compartido.eventos.puertos.salida.NotificarCalificacionOutputPort;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotificacionCalificacionAdaptador implements NotificarCalificacionOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public NotificacionCalificacionAdaptador(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void notificarCalificacionPeliculaCreada(CalificacionPelicula calificacion) {
        try {
            Map<String, Object> notificacion = new HashMap<>();
            notificacion.put("calificacionId", calificacion.getCalificacionId());
            notificacion.put("usuarioId", calificacion.getUsuarioId());
            notificacion.put("peliculaId", calificacion.getPeliculaId());
            notificacion.put("puntuacion", calificacion.getPuntuacion());
            notificacion.put("tipo", "PELICULA");

            String mensaje = objectMapper.writeValueAsString(notificacion);
            kafkaTemplate.send("calificacion-creada", mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar notificación de calificación de película: " + e.getMessage());
        }
    }

    @Override
    public void notificarCalificacionSalaCreada(CalificacionSala calificacion) {
        try {
            Map<String, Object> notificacion = new HashMap<>();
            notificacion.put("calificacionId", calificacion.getCalificacionId());
            notificacion.put("usuarioId", calificacion.getUsuarioId());
            notificacion.put("salaId", calificacion.getSalaId());
            notificacion.put("puntuacion", calificacion.getPuntuacion());
            notificacion.put("tipo", "SALA");

            String mensaje = objectMapper.writeValueAsString(notificacion);
            kafkaTemplate.send("calificacion-creada", mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar notificación de calificación de sala: " + e.getMessage());
        }
    }

    @Override
    public void notificarCalificacionSnackCreada(Object calificacion) {
        try {
            String mensaje = objectMapper.writeValueAsString(calificacion);
            kafkaTemplate.send("calificacion-creada", mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar notificación de calificación de snack: " + e.getMessage());
        }
    }
}
