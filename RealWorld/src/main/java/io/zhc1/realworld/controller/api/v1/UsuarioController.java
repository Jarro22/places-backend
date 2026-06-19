package io.zhc1.realworld.controller.api.v1;

import io.zhc1.realworld.model.Usuario;
import io.zhc1.realworld.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- RUTA PARA CREAR ---
    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario nuevoUsuario) {
        return usuarioRepository.save(nuevoUsuario);
    }

    // --- ENDPOINT PRIVADO 1: Ver perfil (PROTEGIDO) ---
    @GetMapping("/perfil")
    public ResponseEntity<?> verPerfil(Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseEntity.ok(Map.of(
                "usuario", usuario.getUsuario(),
                "persona", usuario.getPersona()
        ));
    }

    // --- ENDPOINT PRIVADO 2: Configuración (PROTEGIDO) ---
    @PostMapping("/configuracion")
    public ResponseEntity<?> cambiarConfiguracion(@RequestBody Map<String, String> datos, Principal principal) {
        String username = principal.getName();

        return ResponseEntity.ok(Map.of(
                "mensaje", "Configuración actualizada para: " + username,
                "datos_recibidos", datos
        ));
    }
}