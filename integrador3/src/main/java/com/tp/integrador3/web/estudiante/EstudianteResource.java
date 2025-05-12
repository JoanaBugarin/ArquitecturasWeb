package com.tp.integrador3.web.estudiante;

import com.tp.integrador3.service.EstudianteService;
import com.tp.integrador3.service.dto.estudiante.request.EstudianteRequestDTO;
import com.tp.integrador3.service.dto.estudiante.request.FilterEstudianteRequestDTO;
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

    /**
     * a) da de alta un estudiante
     * Permite dar de alta un nuevo estudiante
     * Valid realiza la comprobación de los datos según las especificacion del
     * objeto EstudianteRequestDTO
     * Si la validación falla, se lanza una exception que captura la clase
     * GlobalExceptionHandler, quien retorna la respuesta con el error de validación
     * 
     * @param estudianteDTO Campos necesarios para crear un nuevo estudiante
     * @return Los datos del estudiante creado
     */
    @PostMapping("")
    public EstudianteResponseDTO createEstudiante(@Valid @RequestBody EstudianteRequestDTO estudianteDTO) {
        return estudianteService.createEstudiante(estudianteDTO);
    }

    /**
     * c) recupera todos los estudiantes, y especifica el criterio de ordenamiento
     * simple: nombre.
     * Permite recuperar la lista de estudiantes ordenados por un campo, paginados
     * (opcional) y con limite de registro(opcional)
     * Para ordenar los registros: api/estudiantes?orderBy=nombre&page=1&limit=10
     * Posibles campos de ordenamiento: dni, nombre, apellido, ciudad y lu
     * 
     * @param estudianteDTO Permite especificar metodos de ordenamiento, paginado y
     *                      limite de registros para la consulta
     * @return Lista de estudiantes según las especificaciones
     */
    @GetMapping("")
    public List<EstudianteResponseDTO> findAllSimpleSort(@Valid FilterEstudianteRequestDTO estudianteDTO) {
        return this.estudianteService.findAllSimpleSort(estudianteDTO);
    }

    // d) recupera un estudiante, en base a su número de libreta universitaria.
    @GetMapping("/lu/{lu}")
    public EstudianteResponseDTO findByLu(@PathVariable(required = true) int lu) {
        return this.estudianteService.findByLu(lu);
    }

    // e) recupera todos los estudiantes, en base a su género.
    @GetMapping("/genero/{genero}")
    public List<EstudianteResponseDTO> findByGenero(@PathVariable(required = true) String genero) {
        return estudianteService.findByGenero(genero);
    }

    // g) recupera los estudiantes de una determinada carrera, filtrando por ciudad
    // de residencia.
    @GetMapping("/carrera/{carreraId}/ciudad/{ciudad}")
    public List<EstudianteResponseDTO> findByCarreraAndCiudad(@PathVariable Integer carreraId,
            @PathVariable String ciudad) {
        return estudianteService.findByCarreraAndCiudad(carreraId, ciudad);
    }

}
