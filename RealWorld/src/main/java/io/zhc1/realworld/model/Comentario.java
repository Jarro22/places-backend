package io.zhc1.realworld.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "comentarios")
@Data
public class Comentario {

    @Id
    @Column(name = "id_comentarios")
    private Integer idComentarios;

    @Column(name = "comentario", nullable = false, length = 500)
    private String textoComentario;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "fecha_de_comentario", nullable = false)
    private LocalDate fechaComentario;

    @Column(name = "id_personas", nullable = false)
    private Integer idPersonas;

    @Column(name = "id_lugares", nullable = false)
    private Integer idLugares;

    // Auto-relación: un comentario puede tener un padre
    @ManyToOne
    @JoinColumn(name = "id_recomentarios")
    private Comentario comentarioPadre;
}