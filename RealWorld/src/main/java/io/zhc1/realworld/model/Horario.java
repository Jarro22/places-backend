package io.zhc1.realworld.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "horarios")
@Data
public class Horario {
    @Id
    @Column(name = "id_horario")
    private Integer idHorario;
    private String dia;
    private java.time.LocalTime inicio;
    private java.time.LocalTime fin;
    @Column(name = "id_lugares")
    private Integer idLugares;
}