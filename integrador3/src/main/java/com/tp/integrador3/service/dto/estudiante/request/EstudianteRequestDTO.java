package com.tp.integrador3.service.dto.estudiante.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class EstudianteRequestDTO {

    @NotNull( message = "El dni es un campo obligatorio.")
    private Integer dni;
    @NotNull( message = "El nombre es un campo obligatorio.")
    @NotBlank( message = "El nombre es un campo obligatorio.")
    private String nombre;
    @NotNull( message = "El apellido es un campo obligatorio.")
    @NotBlank( message = "El apellido es un campo obligatorio.")
    private String apellido;
    @NotNull( message = "La edad es un campo obligatorio.")
    @Min(value = 15, message = "La edad debe ser mayor a 15")
    private Integer edad;
    @NotNull( message = "El genero es un campo obligatorio.")
    @NotBlank( message = "El genero es un campo obligatorio.")
    private String genero;
    @NotNull( message = "La ciudad es un campo obligatorio.")
    @NotBlank( message = "La ciudad es un campo obligatorio.")
    private String ciudad;
    @NotNull( message = "La lu es un campo obligatorio.")
    @Min(value=1, message = "El numero de libreta no es valido")
    private Integer lu;
}
