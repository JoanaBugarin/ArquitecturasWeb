package main.java.DTO;

import jakarta.persistence.Column;
import main.java.entities.Estudiante;

public class EstudianteDTO {
    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private int lu;

    public EstudianteDTO() {}

    public EstudianteDTO(int dni, String nombre, String apellido, int edad, String genero, String ciudad, int lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }

    @Override
    public String toString() {
        return "Estudiante {" +
               "dni=" + dni +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", edad=" + edad +
               ", genero='" + genero + '\'' +
               ", ciudad='" + ciudad + '\'' +
               ", lu=" + lu +
               '}';
    }
}
