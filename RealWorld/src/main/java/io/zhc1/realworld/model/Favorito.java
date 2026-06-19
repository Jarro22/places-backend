package io.zhc1.realworld.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favoritos")
@Data
public class Favorito {
    @EmbeddedId
    private FavoritoId id;

    @Embeddable
    @Data
    public static class FavoritoId implements java.io.Serializable {
        @Column(name = "id_personas")
        private Integer idPersonas;
        @Column(name = "id_lugares")
        private Integer idLugares;
    }
}