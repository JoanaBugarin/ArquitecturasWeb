package com.tp.integrador3.repository;

import com.tp.integrador3.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository <Estudiante, Long> {
}
