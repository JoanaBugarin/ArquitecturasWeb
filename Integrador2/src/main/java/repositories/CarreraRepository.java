package main.java.repositories;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.java.DTO.CarreraConCantidadInscriptosDTO;
import main.java.DTO.CarreraDTO;
import main.java.DTO.EstudianteDTO;
import main.java.DTO.ReporteCarreraDTO;
import main.java.entities.Carrera;
import main.java.entities.Estudiante;
import main.java.factories.EMFactory;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class CarreraRepository {

    public CarreraRepository() {
    }

    public void insertarDesdeCSV(String rutaArchivo) {
        EntityManager em = EMFactory.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Carrera carrera=new Carrera();
                carrera.setId(Integer.parseInt(linea[0]));
                carrera.setCarrera(linea[1]);
                carrera.setDuracion(Integer.parseInt(linea[2]));
                Carrera estaRegistrada = em.find(Carrera.class, carrera.getId());
                if (estaRegistrada != null)
                    continue;
                em.persist(carrera);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Retorna la lista de carreras con alumnos incriptos, ordenada por cantidad de inscriptos
     * @return List<CarreraConCantidadInscriptosDTO> Lista de las carreras que tienen alumnos incriptos, ordenada por cantidad de inscriptos de forma descendente
     */
    public List<CarreraConCantidadInscriptosDTO> getCarrerasPorCantidadInscriptos(){
        EntityManager em = EMFactory.getEntityManager();
        em.getTransaction().begin();
        return em.createQuery("SELECT new main.java.DTO.CarreraConCantidadInscriptosDTO(c.carrera, COUNT(e.id)) FROM Carrera  c JOIN EstudianteCarrera e ON e.carrera = c GROUP BY c.carrera ORDER BY COUNT(e.id) DESC", CarreraConCantidadInscriptosDTO.class).getResultList();
    }

    /**
     * Verifica que la Carrera exista en la base de datos
     * @param em EntityManager para realizar la consulta
     * @param carrera Carrera que se quiere verificar si se encuentra registrada en la base de datos
     * @return true si la carrera se encuentra registrada en la base de datos. Caso contrario, retorna false
     */
    public static boolean verificarCarreraExiste(EntityManager em,Carrera carrera){
        Carrera aux = em.find(Carrera.class, carrera.getId());
        if (aux != null)
            return true;
        return false;
    }

    /** Retorna un reporte anual sobre las carreras, sus inscriptos y sus egresados  */


    public List<ReporteCarreraDTO> obtenerReporteCarreras() {
        EntityManager em = EMFactory.getEntityManager(); // Obtener el EntityManager
        try {
            // Crear la consulta JPQL
            return em.createQuery(
                    "SELECT new main.java.DTO.ReporteCarreraDTO( " +
                            "    ec.inscripcion AS anio, " +                           // Año de inscripción
                            "    ec.carrera.carrera AS nombreCarrera, " +              // Nombre de la carrera
                            "    COUNT(CASE WHEN ec.inscripcion IS NOT NULL THEN ec.id END) AS cantidadInscriptos, " + // Conteo de inscriptos
                            "    COUNT(CASE WHEN ec.graduacion IS NOT NULL THEN ec.id END) AS cantidadEgresados " +    // Conteo de egresados
                            ") " +
                            "FROM EstudianteCarrera ec " +
                            "WHERE ec.inscripcion > 0 OR ec.graduacion > 0 " +         // Filtrar años válidos para inscripciones y graduaciones
                            "GROUP BY ec.carrera.carrera, ec.inscripcion " +           // Agrupar por carrera y año
                            "ORDER BY ec.carrera.carrera ASC, ec.inscripcion ASC",     // Ordenar carreras alfabéticamente y años cronológicamente
                    ReporteCarreraDTO.class
            ).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Si hay errores, retorna lista vacía
        } finally {
            em.close(); // Cerrar el EntityManager siempre
        }
    }

}






