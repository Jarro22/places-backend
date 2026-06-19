package io.zhc1.realworld.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Rol {
    @Id
    @Column(name = "ir_rol")
    private Integer idRol;

    @Column(name = "nombre", length = 100)
    private String nombre;
}