package com.tp.integrador3.web.carrera;

import com.tp.integrador3.service.CarreraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/carreras")
@RequiredArgsConstructor
public class CarreraResource {

    private final CarreraService carreraService;
}
