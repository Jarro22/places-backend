package io.zhc1.realworld.repository;

import io.zhc1.realworld.model.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Integer> {
    // Dentro de LugarRepository.java
    List<Lugar> findByNombreContainingIgnoreCase(String nombre);
}