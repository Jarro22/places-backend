package io.zhc1.realworld.controller.api.v1;

import io.zhc1.realworld.model.Comentario;
import io.zhc1.realworld.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // --- LISTAR PÚBLICO: Obtiene todos los comentarios ---
    @GetMapping
    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    // --- LISTAR PÚBLICO POR LUGAR ---
    // El SecurityConfig lo permite sin token gracias al GET
    @GetMapping("/lugar/{idLugar}")
    public List<Comentario> listarPorLugar(@PathVariable Integer idLugar) {
        return comentarioRepository.findByIdLugares(idLugar);
    }

    // --- GUARDAR COMENTARIO (PRIVADO) ---
    // SecurityConfig lo protege automáticamente al ser POST
    @PostMapping
    public Comentario guardarComentario(@RequestBody Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
}