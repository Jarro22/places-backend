package io.zhc1.realworld.repository;

import io.zhc1.realworld.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Favorito.FavoritoId> {

    // Spring ya te da métodos como:
    // save(), deleteById(), existsById(), findAll()

    // Puedes añadir métodos personalizados si los necesitas, por ejemplo:
    // List<Favorito> findByIdIdPersonas(Integer idPersonas);
}