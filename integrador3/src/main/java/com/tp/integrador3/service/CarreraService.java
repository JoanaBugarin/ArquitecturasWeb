package com.tp.integrador3.service;

import com.tp.integrador3.repository.CarreraRepository;
import com.tp.integrador3.service.dto.carrera.response.CarreraConCantidadInscriptosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Transactional(readOnly = true)
    public List<CarreraConCantidadInscriptosDTO> getCarrerasConCantidadInscriptos(){
        return carreraRepository.getCarrerasPorCantidadInscriptos();
    }


}
