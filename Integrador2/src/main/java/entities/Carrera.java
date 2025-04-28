package main.java.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Carrera {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_carrera;

    @Column(nullable=false)
    private String carrera;

    @Column(nullable=false)
    private Integer duracion;


    public Carrera() {

    }

    public Carrera(Integer id, String carrera, Integer duracion) {
        this.id_carrera = id;
        this.carrera = carrera;
        this.duracion = duracion;
    }

    public Integer getId() {
        return id_carrera;
    }

    public void setId(Integer id) {
        this.id_carrera = id;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }


    @Override
    public String toString() {
        return "Carrera [id=" + id_carrera + ", carrera=" + carrera + ", duracion=" + duracion +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carrera carrera1 = (Carrera) o;
        return this.getCarrera().equals(carrera1.getCarrera());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(carrera);
    }
}

