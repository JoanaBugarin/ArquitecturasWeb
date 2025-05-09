package com.tp.integrador3.web.estudiantecarrera;

import com.tp.integrador3.service.EstudianteCarreraService;
import com.tp.integrador3.service.dto.estudiantecarrera.request.EstudianteCarreraRequestDTO;
import com.tp.integrador3.service.dto.estudiantecarrera.response.EstudianteCarreraResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/estudiantes_carreras")
@RequiredArgsConstructor
public class EstudianteCarreraResource {

    private final EstudianteCarreraService estudianteCarreraService;

    //b) matricula un estudiante en una carrera
    @PostMapping("")
    public EstudianteCarreraResponseDTO matricularEstudiante(@RequestBody EstudianteCarreraRequestDTO matriculaDTO) {
        return estudianteCarreraService.matricularEstudiante(matriculaDTO);
    }

}
