package io.zhc1.realworld.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fotos")
@Data
public class Foto {
    @Id
    @Column(name = "id_fotos")
    private Integer idFotos;
    private String url;
    private String descripcion;
    @Column(name = "id_comentarios")
    private Integer idComentarios;
    @Column(name = "id_lugares")
    private Integer idLugares;
}