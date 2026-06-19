package io.zhc1.realworld.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cuentas")
@Data
public class Cuenta {
    @EmbeddedId
    private CuentaId id;

    @Embeddable
    @Data
    public static class CuentaId implements java.io.Serializable {
        @Column(name = "ir_rol")
        private Integer idRol;
        @Column(name = "id_personas")
        private Integer idPersonas;
    }
}