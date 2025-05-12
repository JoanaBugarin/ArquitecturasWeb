package com.tp.integrador3.service;

import com.tp.integrador3.domain.Carrera;
import com.tp.integrador3.domain.Estudiante;
import com.tp.integrador3.domain.EstudianteCarrera;
import com.tp.integrador3.repository.CarreraRepository;
import com.tp.integrador3.repository.EstudianteCarreraRepository;
import com.tp.integrador3.repository.EstudianteRepository;
import com.tp.integrador3.service.dto.estudiantecarrera.response.ReporteCarreraDTO;
import com.tp.integrador3.service.dto.estudiantecarrera.request.EstudianteCarreraRequestDTO;
import com.tp.integrador3.service.dto.estudiantecarrera.response.EstudianteCarreraResponseDTO;
import com.tp.integrador3.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EstudianteCarreraService {
    @Autowired
    private final EstudianteCarreraRepository estudianteCarreraRepository;
    @Autowired
    private final EstudianteRepository estudianteRepository;
    @Autowired
    private final CarreraRepository carreraRepository;

    @Transactional
    public EstudianteCarreraResponseDTO matricularEstudiante(EstudianteCarreraRequestDTO matriculaDTO) {
        Estudiante estudiante = estudianteRepository.findById(Integer.valueOf(matriculaDTO.getDni()))
                .orElseThrow(() -> new NotFoundException("Estudiante", Long.valueOf(matriculaDTO.getDni())));

        Carrera carrera = carreraRepository.findById(Long.valueOf(matriculaDTO.getCarreraId()))
                .orElseThrow(() -> new NotFoundException("Carrera", Long.valueOf(matriculaDTO.getCarreraId())));

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, carrera, matriculaDTO.getInscripcion(),
                matriculaDTO.getGraduacion(), matriculaDTO.getAntiguedad());
        estudianteCarreraRepository.save(estudianteCarrera);

        return new EstudianteCarreraResponseDTO(estudianteCarrera);
    }

    @Transactional(readOnly = true)
    public List<ReporteCarreraDTO> getReporteCarreras() {
        List<ReporteCarreraDTO> inscriptos = estudianteCarreraRepository.getInscriptos();
        List<ReporteCarreraDTO> egresados = estudianteCarreraRepository.getGraduaciones();

        // Unir ambas listas
        Map<String, ReporteCarreraDTO> mapa = new HashMap<>();

        for (ReporteCarreraDTO dto : inscriptos) {
            String clave = dto.getNombreCarrera() + "_" + dto.getAnio();
            mapa.put(clave, dto);
        }

        for (ReporteCarreraDTO dto : egresados) {
            String clave = dto.getNombreCarrera() + "_" + dto.getAnio();
            if (mapa.containsKey(clave)) {
                ReporteCarreraDTO existente = mapa.get(clave);
                existente.setCantidadEgresados(dto.getCantidadEgresados());
            } else {
                mapa.put(clave, dto);
            }
        }

        // Ordenar y devolver
        List<ReporteCarreraDTO> resultado = new ArrayList<>(mapa.values());
        resultado.sort(Comparator.comparing(ReporteCarreraDTO::getNombreCarrera)
                .thenComparing(ReporteCarreraDTO::getAnio));
        return resultado;
    }
}
