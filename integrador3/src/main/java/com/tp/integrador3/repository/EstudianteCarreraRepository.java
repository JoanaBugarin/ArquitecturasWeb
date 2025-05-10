package com.tp.integrador3.repository;

import com.tp.integrador3.domain.EstudianteCarrera;
import com.tp.integrador3.service.dto.estudiantecarrera.response.ReporteCarreraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Long>  {

    @Query("SELECT new com.tp.integrador3.service.dto.estudiantecarrera.response.ReporteCarreraDTO(ec.carrera.carrera, COUNT(ec), 0L, ec.inscripcion) FROM EstudianteCarrera ec WHERE ec.inscripcion != 0 GROUP BY ec.carrera.carrera, ec.inscripcion")
    List<ReporteCarreraDTO> getInscriptos();

    @Query("SELECT new com.tp.integrador3.service.dto.estudiantecarrera.response.ReporteCarreraDTO(ec.carrera.carrera, 0L, COUNT(ec), ec.graduacion) FROM EstudianteCarrera ec WHERE ec.graduacion != 0 GROUP BY ec.carrera.carrera, ec.graduacion")
    List<ReporteCarreraDTO> getGraduaciones();
}
