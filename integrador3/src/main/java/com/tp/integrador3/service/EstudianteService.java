package com.tp.integrador3.service;

import com.tp.integrador3.repository.EstudianteRepository;
import com.tp.integrador3.service.dto.estudiante.response.EstudianteResponseDTO;
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
    public List<EstudianteResponseDTO> getEstudiantesOrdenados(String criterio) {
        // Asegura que criterio no sea null o vacío, usa un valor predeterminado si es necesario
        Sort sort = Sort.by(Sort.Direction.ASC, criterio != null ? criterio : "nombre");

        return estudianteRepository.findAll(sort)
                .stream()
                .map(EstudianteResponseDTO::new)
                .toList();
    }
}
