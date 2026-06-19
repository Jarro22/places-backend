package io.zhc1.realworld.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "privilegios")
@Data
public class Privilegio {

    @EmbeddedId
    private PrivilegioId id;

    @Embeddable
    @Data
    public static class PrivilegioId implements Serializable {
        @Column(name = "ir_rol")
        private Integer idRol;
        @Column(name = "id_funcionalidades")
        private Integer idFuncionalidades;
    }
}