package com.tp.integrador3.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    public Estudiante(int dni, String nombre, String apellido, int edad, String genero, String ciudad, int lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }
}
