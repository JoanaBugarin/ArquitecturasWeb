package com.tp.integrador3.service;

import com.tp.integrador3.domain.Carrera;
import com.tp.integrador3.domain.Estudiante;
import com.tp.integrador3.domain.EstudianteCarrera;
import com.tp.integrador3.repository.CarreraRepository;
import com.tp.integrador3.repository.EstudianteCarreraRepository;
import com.tp.integrador3.repository.EstudianteRepository;
import com.tp.integrador3.service.dto.estudiantecarrera.request.EstudianteCarreraRequestDTO;
import com.tp.integrador3.service.dto.estudiantecarrera.response.EstudianteCarreraResponseDTO;
import com.tp.integrador3.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstudianteCarreraService {

    private final EstudianteCarreraRepository estudianteCarreraRepository;
    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;

    @Transactional
    public EstudianteCarreraResponseDTO matricularEstudiante(EstudianteCarreraRequestDTO matriculaDTO) {
        Estudiante estudiante = estudianteRepository.findById(Long.valueOf(matriculaDTO.getEstudianteId()))
                .orElseThrow(() -> new NotFoundException("Estudiante", Long.valueOf(matriculaDTO.getEstudianteId())));

        Carrera carrera = carreraRepository.findById(Long.valueOf(matriculaDTO.getCarreraId()))
                .orElseThrow(() -> new NotFoundException("Carrera", Long.valueOf(matriculaDTO.getCarreraId())));

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(matriculaDTO.getId(), estudiante, carrera, matriculaDTO.getInscripcion(), matriculaDTO.getGraduacion(), matriculaDTO.getAntiguedad());
        estudianteCarreraRepository.save(estudianteCarrera);

        return new EstudianteCarreraResponseDTO(estudianteCarrera);
    }

}
