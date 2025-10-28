package com.sa.calificacion.compartido.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI calificacionesOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8091");
        localServer.setDescription("Servidor Local");

        Server prodServer = new Server();
        prodServer.setUrl("https://api-calificaciones.ejemplo.com");
        prodServer.setDescription("Servidor Producción");

        Contact contact = new Contact();
        contact.setEmail("soporte@ejemplo.com");
        contact.setName("Equipo de Desarrollo");
        contact.setUrl("https://www.ejemplo.com");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API de Calificaciones - Sistema de Cines")
                .version("1.0.0")
                .contact(contact)
                .description("API REST para gestión de calificaciones y comentarios de películas, salas y snacks")
                .termsOfService("https://www.ejemplo.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer, prodServer));
    }
}
