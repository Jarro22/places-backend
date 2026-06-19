package io.zhc1.realworld;

import io.zhc1.realworld.security.SecurityConfig; // Importa tu configuración
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Import(SecurityConfig.class) // ESTO FUERZA A SPRING A CARGAR TU CONFIG
public class RealworldApplication {
	public static void main(String[] args) {
		SpringApplication.run(RealworldApplication.class, args);
	}
}