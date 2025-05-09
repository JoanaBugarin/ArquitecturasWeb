package com.tp.integrador3.web.estudiante;

import com.tp.integrador3.service.EstudianteService;
import com.tp.integrador3.service.dto.estudiante.response.EstudianteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/estudiantes")
@RequiredArgsConstructor
public class EstudianteResource {

    private final EstudianteService estudianteService;

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
