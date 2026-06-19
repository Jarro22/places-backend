package io.zhc1.realworld.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lugares")
@Data
public class Lugar {
    @Id
    @Column(name = "id_lugares")
    private Integer idLugares;
    private String nombre;
    private String descripcion;
    private String provincia;
    private String direccion;
    private java.math.BigDecimal longitud;
    private java.math.BigDecimal latitud;
    private String municipio;
    private String departamento;
    private String url;
}