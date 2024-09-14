package com.janmarkuslanger.animalshelterservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		servers = {@Server(url = "/api")}
)
public class AnimalShelterServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterServiceApplication.class, args);
	}
}
