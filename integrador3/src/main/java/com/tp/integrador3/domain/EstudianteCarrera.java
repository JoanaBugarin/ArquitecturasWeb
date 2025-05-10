package com.tp.integrador3.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Data
@NoArgsConstructor
public class EstudianteCarrera {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "dni", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    @Column(nullable = false)
    private Integer inscripcion;

    @Column(nullable = false)
    private Integer graduacion;

    @Column(nullable = false)
    private Integer antiguedad;

    public EstudianteCarrera(Integer id, Estudiante estudiante, Carrera carrera, Integer inscripcion,
                             Integer graduacion, Integer antiguedad) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

    public EstudianteCarrera(Estudiante estudiante, Carrera carrera, Integer inscripcion,
                             Integer graduacion, Integer antiguedad) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }
}
