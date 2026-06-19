package io.zhc1.realworld.repository;

import io.zhc1.realworld.model.Funcionalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionalidadRepository extends JpaRepository<Funcionalidad, Integer> {
}