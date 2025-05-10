package com.tp.integrador3.web.estudiantecarrera;

import com.tp.integrador3.service.EstudianteCarreraService;
import com.tp.integrador3.service.dto.estudiantecarrera.request.EstudianteCarreraRequestDTO;
import com.tp.integrador3.service.dto.estudiantecarrera.response.EstudianteCarreraResponseDTO;
import com.tp.integrador3.service.dto.estudiantecarrera.response.ReporteCarreraDTO;
import com.tp.integrador3.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estudiantes_carreras")
@RequiredArgsConstructor
public class EstudianteCarreraResource {
    @Autowired
    private final EstudianteCarreraService estudianteCarreraService;

    //b) matricula un estudiante en una carrera
    @PostMapping("")
    public EstudianteCarreraResponseDTO matricularEstudiante(@RequestBody EstudianteCarreraRequestDTO matriculaDTO) {
        try {
            return estudianteCarreraService.matricularEstudiante(matriculaDTO);
        }catch (NotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
    h)  generar un reporte de las carreras, que para cada carrera incluya información de los
        inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
        presentar los años de manera cronológica.
     */
    @GetMapping("/reporte")
    public List<ReporteCarreraDTO> getReportePorCarrera(){
        return estudianteCarreraService.getReporteCarreras();
    }
}
