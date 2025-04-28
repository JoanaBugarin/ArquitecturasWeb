package main.java.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class Estudiante {
    @Id
    @Column(nullable=false,unique = true)
    private int dni;
    @Column(nullable=false)
    private String nombre;
    @Column(nullable=false)
    private String apellido;
    @Column(nullable=false)
    private int edad;
    @Column(nullable=false)
    private String genero;
    @Column(nullable=false)
    private String ciudad;
    @Column(unique = true,nullable = false)
    private int lu;


    public Estudiante() {

    }

    public Estudiante(int dni, String nombre, String apellido, int edad, String genero, String ciudad, int lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }


    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getApellido() {
        return apellido;
    }



    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public int getEdad() {
        return edad;
    }



    public void setEdad(int edad) {
        this.edad = edad;
    }



    public String getGenero() {
        return genero;
    }



    public void setGenero(String genero) {
        this.genero = genero;
    }



    public String getCiudad() {
        return ciudad;
    }



    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }



    public int getLu() {
        return lu;
    }



    public void setLu(int lu) {
        this.lu = lu;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante est = (Estudiante) o;
        return this.getDni() == est.getDni();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }


    @Override
    public String toString() {
        return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
               + ", genero=" + genero + ", ciudad=" + ciudad + ", lu=" + lu + "]";
    }


}

