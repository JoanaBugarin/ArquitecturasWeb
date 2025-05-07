package com.tp.integrador3.repository;

import com.tp.integrador3.domain.Carrera;
import com.tp.integrador3.domain.Estudiante;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository <Estudiante, Long> {

    List<Estudiante> findAll(Sort sort);

    @Query( "SELECT e.dni,e.nombre,e.apellido,e.edad, e.genero, e.ciudad, e.lu FROM Estudiante e WHERE e.lu = :libreta")
    Estudiante getEstudiantePorLu(int libreta);

    @Query("SELECT e.dni,e.nombre,e.apellido,e.edad, e.genero, e.ciudad, e.lu FROM Estudiante e WHERE e.genero = :genero")
    List<Estudiante> getEstudiantesPorGenero(String genero);

    @Query("SELECT e.dni,e.nombre,e.apellido, e.edad,e.genero,e.ciudad,e.lu FROM Estudiante e JOIN EstudianteCarrera ec ON ec.estudiante = e WHERE ec.carrera = :carrera AND e.ciudad = :ciudad")
    List<Estudiante> getEstudiantesPorCarrera(Carrera carrera, String ciudad);
}
