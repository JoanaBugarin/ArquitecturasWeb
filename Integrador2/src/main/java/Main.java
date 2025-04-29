package main.java;

import main.java.DTO.CarreraConCantidadInscriptosDTO;
import main.java.DTO.EstudianteDTO;
import main.java.DTO.ReporteCarreraDTO;
import main.java.entities.Carrera;
import main.java.entities.Estudiante;
import main.java.entities.EstudianteCarrera;
import main.java.factories.EMFactory;
import main.java.repositories.CarreraRepository;
import main.java.repositories.EstudianteCarreraRepository;
import main.java.repositories.EstudianteRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EstudianteRepository er = new EstudianteRepository();
        er.insertarDesdeCSV("src/main/resources/estudiantes.csv");
        CarreraRepository carreraRepository  = new CarreraRepository();
        carreraRepository.insertarDesdeCSV("src/main/resources/carreras.csv");
        EstudianteCarreraRepository ecr = new EstudianteCarreraRepository();
        ecr.insertarDesdeCSV("src/main/resources/estudianteCarrera.csv");

        // 2 a) dar de alta un estudiante
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" 2 a) dar de alta un estudiante");
        Estudiante estudiante = new Estudiante(20158741, "Mario", "Perez", 35, "Male", "Necochea", 254879);
        Estudiante estudiante2 = new Estudiante(35987456,"Fernanda", "Ramos",21,"Female","Necochea",365987);
        Estudiante estudiante3 = new Estudiante(35987001,"Cristian", "Medina",25,"Male","Necochea",365005);

        try{
            er.insertarEstudiante(estudiante);
            System.out.println("Estudiante insertado "+estudiante.getDni());
            er.insertarEstudiante(estudiante2);
            System.out.println("Estudiante insertado "+estudiante2.getDni());
            er.insertarEstudiante(estudiante3);
            System.out.println("Estudiante insertado "+estudiante3.getDni());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // 2 b) matricular un estudiante en una carrera
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" 2 b) matricular un estudiante en una carrera");
        Carrera carrera = new Carrera(1,"TUDAI",2);
        try {
            EstudianteCarrera ematriculado = ecr.matricularEstudiante(estudiante, carrera);
            System.out.println("Estudiante matriculado: "+ estudiante.getDni() + ematriculado);
            ematriculado = ecr.matricularEstudiante(estudiante2, carrera);
            System.out.println("Estudiante matriculado: "+ estudiante.getDni() + ematriculado);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // 2 c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" 2 c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple");
        String criterio = "apellido";
        List<EstudianteDTO> estudiantesOrdenados = er.getEstudiantesOrdenados(criterio);
        System.out.println("Lista de estudiantes ordenados por "+criterio);
        for (EstudianteDTO estudianteDTO : estudiantesOrdenados){
            System.out.println(estudianteDTO);
        }

        // 2 d) recuperar un estudiante, en base a su número de libreta universitaria.
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" 2 d) recuperar un estudiante, en base a su número de libreta universitaria.");
        int lu = 28886;
        EstudianteDTO porLU = er.getEstudiantePorLu(lu);
        System.out.println("Estudiante registrado con la LU " + lu);
        System.out.println(porLU);

        // 2 e) recuperar todos los estudiantes, en base a su género.
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" 2 e) recuperar todos los estudiantes, en base a su género.");
        String genero = "Polygender";
        List<EstudianteDTO> listaPorGenero = er.getEstudiantesPorGenero(genero);
        System.out.println("Lista de estudiantes por genero " + genero);
        for (EstudianteDTO estudianteDTO : listaPorGenero){
            System.out.println(estudianteDTO);
        }

        // 2 f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" 2 f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.");
        List<CarreraConCantidadInscriptosDTO> carrerasConInscriptos = carreraRepository.getCarrerasPorCantidadInscriptos();
        System.out.println("Lista de carreras con alumnos inscriptos, ordenada por cantidad de inscriptos ");
        for (CarreraConCantidadInscriptosDTO carreraConInscriptosDTO: carrerasConInscriptos){
            System.out.println(carreraConInscriptosDTO);
        }

        // 2 g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" 2 g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.");
        String ciudadResidencia = "Necochea";
        List<EstudianteDTO> estudiantesPorCarrera = er.getEstudiantesPorCarrera(carrera, ciudadResidencia);
        System.out.println("Lista de estudiantes en la carrera " + carrera.getCarrera() + " y cuya ciudad de residencia es "+ciudadResidencia);
        for (EstudianteDTO estudianteDTO : estudiantesPorCarrera){
            System.out.println(estudianteDTO);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" PUNTO 3");
        List<ReporteCarreraDTO> reporteCarreras = carreraRepository.obtenerReporteCarreras();
        System.out.println("Lista de reporte de carreras");
        for (ReporteCarreraDTO reporteCarreraDTO : reporteCarreras){
            System.out.println(reporteCarreraDTO);
        }




    }
}
