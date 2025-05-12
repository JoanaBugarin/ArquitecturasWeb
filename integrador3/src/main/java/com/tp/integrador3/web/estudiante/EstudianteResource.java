package com.tp.integrador3.web.estudiante;

import com.tp.integrador3.service.EstudianteService;
import com.tp.integrador3.service.dto.estudiante.request.EstudianteRequestDTO;
import com.tp.integrador3.service.dto.estudiante.response.EstudianteResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estudiantes")
@RequiredArgsConstructor
@Validated

public class EstudianteResource {
    @Autowired
    private final EstudianteService estudianteService;

    //a) da de alta un estudiante
    @PostMapping("")
    public EstudianteResponseDTO createEstudiante(@Valid @RequestBody EstudianteRequestDTO estudianteDTO) {
        return estudianteService.createEstudiante(estudianteDTO);
    }

    //c) recupera todos los estudiantes, y especifica el criterio de ordenamiento simple: nombre.
    @GetMapping("")
    public List<EstudianteResponseDTO> findAllSimpleSort(){
        return this.estudianteService.findAllSimpleSort();
    }

    //d) recupera un estudiante, en base a su número de libreta universitaria.
    @GetMapping("/lu/{lu}")
    public EstudianteResponseDTO findByLu( @PathVariable int lu ){
        return this.estudianteService.findByLu( lu );
    }

    //e) recupera todos los estudiantes, en base a su género.
    @GetMapping("/genero/{genero}")
    public List<EstudianteResponseDTO> findByGenero(@PathVariable String genero) {
        return estudianteService.findByGenero(genero);
    }

    //g) recupera los estudiantes de una determinada carrera, filtrando por ciudad de residencia.
    @GetMapping("/carrera/{carreraId}/ciudad/{ciudad}")
    public List<EstudianteResponseDTO> findByCarreraAndCiudad(@PathVariable Integer carreraId, @PathVariable String ciudad) {
        return estudianteService.findByCarreraAndCiudad(carreraId, ciudad);
    }


}
