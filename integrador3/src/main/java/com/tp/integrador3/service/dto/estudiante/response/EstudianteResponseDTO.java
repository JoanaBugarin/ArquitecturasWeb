package com.tp.integrador3.service.dto.estudiante.response;

import com.tp.integrador3.domain.Estudiante;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EstudianteResponseDTO {

    private final int dni;
    private final String nombre;
    private final String apellido;
    private final int edad;
    private final String genero;
    private final String ciudad;
    private final int lu;

    public EstudianteResponseDTO(Estudiante e) {
        this.dni = e.getDni();
        this.nombre = e.getNombre();
        this.apellido = e.getApellido();
        this.edad = e.getEdad();
        this.genero = e.getGenero();
        this.ciudad = e.getCiudad();
        this.lu = e.getLu();
    }
}
