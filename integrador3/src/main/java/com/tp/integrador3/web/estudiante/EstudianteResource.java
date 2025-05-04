package com.tp.integrador3.web.estudiante;

import com.tp.integrador3.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/estudiantes")
@RequiredArgsConstructor
public class EstudianteResource {

    private final EstudianteService estudianteService;
}
