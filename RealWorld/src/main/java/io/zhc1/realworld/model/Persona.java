package io.zhc1.realworld.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "personas")
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personas")
    private Integer idPersonas;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "primer_apellido", nullable = false, length = 100)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 100)
    private String segundoApellido;

    @Column(name = "ci", nullable = true)
    private String ci;

    @Column(name = "complemento", length = 2)
    private String complemento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "genero", nullable = false, length = 50)
    private String genero;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "telefono_fijo")
    private Integer telefonoFijo;

    @Column(name = "celular")
    private Integer celular;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // EL CAMBIO CLAVE:
    // Aquí 'mappedBy' debe coincidir exactamente con el nombre del campo
    // en la clase Usuario que tiene el @OneToOne.
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Usuario usuario;
}