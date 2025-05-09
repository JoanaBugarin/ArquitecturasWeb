package com.tp.integrador3.service;

import com.tp.integrador3.domain.Estudiante;
import com.tp.integrador3.repository.EstudianteRepository;
import com.tp.integrador3.service.dto.estudiante.request.EstudianteRequestDTO;
import com.tp.integrador3.service.dto.estudiante.response.EstudianteResponseDTO;
import com.tp.integrador3.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> findAll() {
        return this.estudianteRepository.findAll().stream().map(EstudianteResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> findAllSimpleSort() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");

        return estudianteRepository.findAll(sort)
                .stream()
                .map(EstudianteResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public EstudianteResponseDTO findByLu(int lu) {
        return estudianteRepository.findByLu(lu)
                .map(EstudianteResponseDTO::new)
                .orElseThrow(() -> new NotFoundException("Estudiante", (long) lu));
    }

    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> findByGenero(String genero) {
        return estudianteRepository.findByGenero(genero)
                .stream()
                .map(EstudianteResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> findByCarreraAndCiudad(Integer carreraId, String ciudad) {
        return estudianteRepository.findByCarreraAndCiudad(carreraId, ciudad)
                .stream()
                .map(EstudianteResponseDTO::new) // Convertir cada estudiante a DTO
                .toList();
    }

    @Transactional
    public EstudianteResponseDTO createEstudiante(EstudianteRequestDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante(
                estudianteDTO.getDni(),
                estudianteDTO.getNombre(),
                estudianteDTO.getApellido(),
                estudianteDTO.getEdad(),
                estudianteDTO.getGenero(),
                estudianteDTO.getCiudad(),
                estudianteDTO.getLu()
        );

        estudianteRepository.save(estudiante);
        return new EstudianteResponseDTO(estudiante);
    }


}
