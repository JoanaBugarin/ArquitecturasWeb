package main.java.DTO;

import main.java.entities.EstudianteCarrera;

public class EstudianteCarreraDTO {
    private CarreraDTO carrera;
    private EstudianteDTO estudiante;


    public EstudianteCarreraDTO(EstudianteDTO estudiante, CarreraDTO carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Estudiante " +estudiante+
               " en la carrera " + carrera +
               '}';
    }
}
