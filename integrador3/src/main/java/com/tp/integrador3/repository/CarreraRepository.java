package com.tp.integrador3.repository;

import com.tp.integrador3.domain.Carrera;
import com.tp.integrador3.service.dto.carrera.response.CarreraConCantidadInscriptosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query( "SELECT new com.tp.integrador3.service.dto.carrera.response.CarreraConCantidadInscriptosDTO( c.carrera, COUNT(e.id)) FROM Carrera c JOIN EstudianteCarrera e ON e.carrera = c GROUP BY c.carrera ORDER BY COUNT(e.id) DESC")
    List<CarreraConCantidadInscriptosDTO> getCarrerasPorCantidadInscriptos();

}
