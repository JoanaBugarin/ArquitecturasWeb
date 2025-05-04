package com.tp.integrador3.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Carrera {
    @Id
    private Integer id_carrera;

    @Column(nullable=false)
    private String carrera;

    @Column(nullable=false)
    private Integer duracion;

    public Carrera(Integer id, String carrera, Integer duracion) {
        this.id_carrera = id;
        this.carrera = carrera;
        this.duracion = duracion;
    }

    public void setId (Integer id) {
        this.id_carrera = id;
    }
}
