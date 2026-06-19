package io.zhc1.realworld.controller.api.v1;

import io.zhc1.realworld.model.Usuario;
import io.zhc1.realworld.repository.UsuarioRepository;
import io.zhc1.realworld.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
// Configuración CORS para permitir peticiones desde tu dominio web
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.OPTIONS})
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credenciales.get("usuario"),
                            credenciales.get("contrasena")
                    )
            );

            // Buscamos el usuario para obtener su ID real
            Usuario usuario = usuarioRepository.findByUsuario(auth.getName())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            String token = JwtUtil.generarToken(auth.getName());

            // Usamos HashMap para evitar errores de tipo en la respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Login exitoso");
            response.put("token", token);
            response.put("idUsuario", usuario.getIdUsuario());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Usuario o contraseña incorrectos"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuarioConPersona) {
        if (usuarioConPersona == null || usuarioConPersona.getPersona() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Datos incompletos"));
        }

        String passwordCifrada = passwordEncoder.encode(usuarioConPersona.getContrasena());
        usuarioConPersona.setContrasena(passwordCifrada);
        usuarioConPersona.setRol("USER");

        try {
            usuarioRepository.save(usuarioConPersona);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al registrar: " + e.getMessage()));
        }

        return ResponseEntity.ok(Map.of("mensaje", "Registrado exitosamente"));
    }

    @PatchMapping("/cambiar-contrasena")
    public ResponseEntity<?> cambiarContrasena(@RequestBody Map<String, String> credenciales, Authentication auth) {
        String username = auth.getName();
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(credenciales.get("passwordActual"), usuario.getContrasena())) {
            return ResponseEntity.status(401).body(Map.of("error", "Contraseña actual incorrecta"));
        }

        usuario.setContrasena(passwordEncoder.encode(credenciales.get("nuevaPassword")));
        usuarioRepository.save(usuario);

        return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada exitosamente"));
    }
}