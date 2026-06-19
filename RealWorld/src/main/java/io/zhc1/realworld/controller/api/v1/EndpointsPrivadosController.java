package io.zhc1.realworld.controller.api.v1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/privado")
public class EndpointsPrivadosController {

    // 1. Endpoint para ver datos sensibles
    @GetMapping("/perfil")
    public String verPerfil(@RequestHeader("Authorization") String token) {
        // En un caso real, aquí validarías el token (JWT)
        return "Acceso concedido a datos protegidos.";
    }

    // 2. Endpoint para realizar una acción administrativa
    @PostMapping("/actualizar-configuracion")
    public String configurar(@RequestHeader("Authorization") String token) {
        return "Configuración actualizada correctamente.";
    }
}