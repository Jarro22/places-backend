package io.zhc1.realworld.controller.api.v1;

import io.zhc1.realworld.model.Favorito;
import io.zhc1.realworld.repository.FavoritoRepository;
import io.zhc1.realworld.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Toggle: POST /api/v1/favoritos {"idLugares": 1}
    @PostMapping
    public ResponseEntity<String> toggleFavorito(@RequestBody Map<String, Integer> request, Principal principal) {
        Integer idLugar = request.get("idLugares");
        Integer idPersona = usuarioRepository.findByUsuario(principal.getName()).get().getPersona().getIdPersonas();

        Favorito.FavoritoId id = new Favorito.FavoritoId();
        id.setIdPersonas(idPersona);
        id.setIdLugares(idLugar);

        if (favoritoRepository.existsById(id)) {
            favoritoRepository.deleteById(id);
            return ResponseEntity.ok("Eliminado");
        } else {
            Favorito nuevo = new Favorito();
            nuevo.setId(id);
            favoritoRepository.save(nuevo);
            return ResponseEntity.ok("Agregado");
        }
    }

    // Verificar estado: GET /api/v1/favoritos/verificar/{idLugar}
    @GetMapping("/verificar/{idLugar}")
    public ResponseEntity<Boolean> verificarLike(@PathVariable Integer idLugar, Principal principal) {
        Integer idPersona = usuarioRepository.findByUsuario(principal.getName()).get().getPersona().getIdPersonas();
        Favorito.FavoritoId id = new Favorito.FavoritoId();
        id.setIdPersonas(idPersona);
        id.setIdLugares(idLugar);
        return ResponseEntity.ok(favoritoRepository.existsById(id));
    }
}