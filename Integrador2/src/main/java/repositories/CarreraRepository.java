package main.java.repositories;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.java.DTO.CarreraConCantidadInscriptosDTO;
import main.java.DTO.CarreraDTO;
import main.java.DTO.EstudianteDTO;
import main.java.entities.Carrera;
import main.java.entities.Estudiante;
import main.java.factories.EMFactory;

import java.io.FileReader;
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
}
