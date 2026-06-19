package io.zhc1.realworld.controller.api.v1;

import io.zhc1.realworld.model.Lugar;
import io.zhc1.realworld.repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lugares")
public class LugarController {

    @Autowired
    private LugarRepository lugarRepository;

    // Endpoint para listar todos los lugares
    @GetMapping
    public List<Lugar> listarLugares() {
        return lugarRepository.findAll();
    }

    // Endpoint para buscar un lugar por su ID (útil para detalles)
    @GetMapping("/{id}")
    public Lugar obtenerLugarPorId(@PathVariable Integer id) {
        return lugarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado con id: " + id));
    }

    // Endpoint para la función de búsqueda (la lupa)
    @GetMapping("/buscar")
    public List<Lugar> buscarLugares(@RequestParam String nombre) {
        return lugarRepository.findByNombreContainingIgnoreCase(nombre);
    }
}