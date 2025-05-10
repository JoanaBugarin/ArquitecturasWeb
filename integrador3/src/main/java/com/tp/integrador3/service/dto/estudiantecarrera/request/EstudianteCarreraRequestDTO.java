package com.tp.integrador3.service.dto.estudiantecarrera.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class EstudianteCarreraRequestDTO {

    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")
    private Integer id;
    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")
    private int dni;
    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")
    private Integer carreraId;
    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")
    private Integer inscripcion;
    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")
    private Integer graduacion;
    @NotNull( message = "El dni es un campo obligatorio.")
    @NotEmpty( message = "El dni es un campo obligatorio.")
    private Integer antiguedad;
}
