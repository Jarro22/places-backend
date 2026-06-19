package io.zhc1.realworld.repository;

import io.zhc1.realworld.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Por ahora, déjala vacía. Spring ya sabe qué hacer.
    // Dentro de tu interface UsuarioRepository
    Optional<Usuario> findByUsuario(String usuario);
}