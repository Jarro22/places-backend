package io.zhc1.realworld.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "funcionalidades")
@Data
public class Funcionalidad {
    @Id
    @Column(name = "id_funcionalidades")
    private Integer idFuncionalidades;
    private String nombre;
}