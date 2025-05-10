package com.tp.integrador3.web.carrera;

import com.tp.integrador3.service.CarreraService;
import com.tp.integrador3.service.dto.carrera.response.CarreraConCantidadInscriptosDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/carreras")
@RequiredArgsConstructor
public class CarreraResource {
    @Autowired
    private final CarreraService carreraService;

    //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    @GetMapping("/inscriptos")
    public List<CarreraConCantidadInscriptosDTO> getCarrerasInscriptos() {
        return carreraService.getCarrerasConCantidadInscriptos();
    }
}
