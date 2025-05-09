package com.tp.integrador3.service.dto.estudiantecarrera.response;

import com.tp.integrador3.domain.Carrera;
import com.tp.integrador3.domain.Estudiante;
import com.tp.integrador3.domain.EstudianteCarrera;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EstudianteCarreraResponseDTO {

    private final Integer id;
    private final String estudianteNombre;
    private final String estudianteApellido;
    private final String carreraNombre;

    private Integer inscripcion;

    private Integer graduacion;

    private Integer antiguedad;

    public EstudianteCarreraResponseDTO(EstudianteCarrera estC) {
        this.id = estC.getId();
        this.estudianteNombre = estC.getEstudiante().getNombre();
        this.estudianteApellido = estC.getEstudiante().getApellido();
        this.carreraNombre = estC.getCarrera().getCarrera();
        this.inscripcion = estC.getInscripcion();
        this.graduacion = estC.getGraduacion();
        this.antiguedad = estC.getAntiguedad();
    }
}
