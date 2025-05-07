package com.tp.integrador3.service.dto.estudiante.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class EstudianteRequestDTO {

    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")
    private int dni;
    @NotNull( message = "El nombre es un campo obligatorio.")
    @NotEmpty( message = "El nombre es un campo obligatorio.")
    private String nombre;
    @NotNull( message = "El apellido es un campo obligatorio.")
    @NotEmpty( message = "El apellido es un campo obligatorio.")
    private String apellido;
    @NotNull( message = "La edad es un campo obligatorio.")
    @NotEmpty( message = "La edad es un campo obligatorio.")
    private int edad;
    @NotNull( message = "El genero es un campo obligatorio.")
    @NotEmpty( message = "El genero es un campo obligatorio.")
    private String genero;
    @NotNull( message = "La ciudad es un campo obligatorio.")
    @NotEmpty( message = "La ciudad es un campo obligatorio.")
    private String ciudad;
    @NotNull( message = "La lu es un campo obligatorio.")
    @NotEmpty( message = "La lu es un campo obligatorio.")
    private int lu;
}
