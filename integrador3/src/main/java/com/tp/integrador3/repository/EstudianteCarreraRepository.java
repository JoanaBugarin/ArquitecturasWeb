package com.tp.integrador3.repository;

import com.tp.integrador3.domain.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Long>  {
}
