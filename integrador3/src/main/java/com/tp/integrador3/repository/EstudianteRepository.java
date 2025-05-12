package com.tp.integrador3.repository;

import com.tp.integrador3.domain.Carrera;
import com.tp.integrador3.domain.Estudiante;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    List<Estudiante> findAll(Sort sort);

    Optional<Estudiante> findByLu(Integer lu);

    List<Estudiante> findByGenero(String genero);

    @Query("SELECT e FROM Estudiante e JOIN EstudianteCarrera ec ON ec.estudiante = e WHERE ec.carrera.id = :carreraId AND e.ciudad = :ciudad")
    List<Estudiante> findByCarreraAndCiudad(@Param("carreraId") Integer carreraId, @Param("ciudad") String ciudad);

}
