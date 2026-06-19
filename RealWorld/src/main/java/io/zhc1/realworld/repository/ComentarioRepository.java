package io.zhc1.realworld.repository;

import io.zhc1.realworld.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    // Spring deriva esto automáticamente de "idLugares" en tu modelo
    List<Comentario> findByIdLugares(Integer idLugares);
}