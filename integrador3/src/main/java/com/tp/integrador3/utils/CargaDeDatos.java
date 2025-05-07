package com.tp.integrador3.utils;

import com.opencsv.exceptions.CsvValidationException;
import com.tp.integrador3.domain.Carrera;
import com.tp.integrador3.domain.Estudiante;
import com.tp.integrador3.domain.EstudianteCarrera;
import com.tp.integrador3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class CargaDeDatos {

    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;
    private final EstudianteCarreraRepository estudianteCarreraRepository;

    @Autowired
    public CargaDeDatos (EstudianteRepository estudianteRepository, CarreraRepository carreraRepository, EstudianteCarreraRepository estudianteCarreraRepository) {
        this.carreraRepository = carreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.estudianteCarreraRepository = estudianteCarreraRepository;
    }

    public void cargarDatosDesdeCSV() throws IOException, CsvValidationException {
        File carrerasCSV = ResourceUtils.getFile("src/main/java/com/tp/integrador3/csv/carreras.csv");
        File estudiantesCSV = ResourceUtils.getFile("src/main/java/com/tp/integrador3/csv/estudiantes.csv");
        File estudiantesCarrerasCSV = ResourceUtils.getFile("src/main/java/com/tp/integrador3/csv/estudianteCarrera.csv");

        //carga de carreras
        try (CSVReader reader = new CSVReader(new FileReader(carrerasCSV))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            while ((linea = reader.readNext()) != null) {
                Carrera carrera=new Carrera();
                carrera.setId(Integer.parseInt(linea[0]));
                carrera.setCarrera(linea[1]);
                carrera.setDuracion(Integer.parseInt(linea[2]));
                carreraRepository.save(carrera);
            }

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        //Carga de estudiantes
        try (CSVReader reader = new CSVReader(new FileReader(estudiantesCSV))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            while ((linea = reader.readNext()) != null) {
                Estudiante estudiante=new Estudiante();
                estudiante.setDni(Integer.parseInt(linea[0]));
                estudiante.setNombre(linea[1]);
                estudiante.setApellido(linea[2]);
                estudiante.setEdad(Integer.parseInt(linea[3]));
                estudiante.setGenero(linea[4]);
                estudiante.setCiudad(linea[5]);
                estudiante.setLu(Integer.parseInt(linea[6]));
                estudianteRepository.save(estudiante);
            }
        }


        //Carga de EstudiantesCarreras
        try (CSVReader reader = new CSVReader(new FileReader(estudiantesCarrerasCSV))) {
            String[] linea;
            reader.readNext(); // salta cabecera
            while ((linea = reader.readNext()) != null) {

                EstudianteCarrera estCar= new EstudianteCarrera();
                estCar.setId(Integer.parseInt(linea[0]));
                Integer idEstudiante = Integer.parseInt(linea[1]);
                Integer idCarrera = Integer.parseInt(linea[2]);

                Estudiante estudiante = new Estudiante();
                estudiante.setDni(idEstudiante);
                estCar.setEstudiante(estudiante);

                Carrera carrera = new Carrera();
                carrera.setId(idCarrera);
                estCar.setCarrera(carrera);

                estCar.setInscripcion(Integer.parseInt(linea[3]));
                estCar.setGraduacion(Integer.parseInt(linea[4]));
                estCar.setAntiguedad(Integer.parseInt(linea[5]));

                estudianteCarreraRepository.save(estCar);

            }
        }
    }

}
