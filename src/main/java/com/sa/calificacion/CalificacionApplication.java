package com.sa.calificacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CalificacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalificacionApplication.class, args);
	}

}
