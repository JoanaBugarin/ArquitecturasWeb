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
import main.java.entities.EstudianteCarrera;
import main.java.factories.EMFactory;

import java.io.FileReader;
import java.util.*;


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
    /**
     * Genera un reporte de carreras con información de inscriptos y egresados.
     *
     * Este método recupera y procesa los datos de estudiantes por carrera,
     * agrupando la información por año y nombre de carrera.
     *
     * @return Lista de ReporteCarreraDTO con datos de inscriptos y egresados

     *
     * Flujo de procesamiento:
     * 1. Consulta de inscriptos por carrera y año
     * 2. Consulta de egresados por carrera y año
     * 3. Combinación de resultados mediante un mapa de claves
     * 4. Ordenamiento de resultados por nombre de carrera y año
     *
     * Complejidad computacional: O(n log n) debido al ordenamiento
     * Complejidad de espacio: O(n) donde n es el número de registros únicos
     *
     * @see ReporteCarreraDTO Clase de transferencia de datos para el reporte
     * @see EstudianteCarrera Entidad de origen de los datos
     */

    public List<ReporteCarreraDTO> generarReporteCarreras() {
        EntityManager em = EMFactory.getEntityManager();
        em.getTransaction().begin();

        // Consulta de inscriptos
        List<ReporteCarreraDTO> inscriptos = em.createQuery(
                "SELECT new main.java.DTO.ReporteCarreraDTO(ec.inscripcion, ec.carrera.carrera, COUNT(ec), 0L) " +
                        "FROM EstudianteCarrera ec " +
                        "WHERE ec.inscripcion != 0 " +
                        "GROUP BY ec.carrera.carrera, ec.inscripcion",
                ReporteCarreraDTO.class
        ).getResultList();

        // Consulta de egresados
        List<ReporteCarreraDTO> egresados = em.createQuery(
                "SELECT new main.java.DTO.ReporteCarreraDTO(ec.graduacion, ec.carrera.carrera, 0L, COUNT(ec)) " +
                        "FROM EstudianteCarrera ec " +
                        "WHERE ec.graduacion != 0 " +
                        "GROUP BY ec.carrera.carrera, ec.graduacion",
                ReporteCarreraDTO.class
        ).getResultList();

        em.getTransaction().commit();
        em.close();

        // Unir ambas listas
        Map<String, ReporteCarreraDTO> mapa = new HashMap<>();

        for (ReporteCarreraDTO dto : inscriptos) {
            String clave = dto.getNombreCarrera() + "_" + dto.getAnio();
            mapa.put(clave, dto);
        }

        for (ReporteCarreraDTO dto : egresados) {
            String clave = dto.getNombreCarrera() + "_" + dto.getAnio();
            if (mapa.containsKey(clave)) {
                ReporteCarreraDTO existente = mapa.get(clave);
                existente.setCantidadEgresados(dto.getCantidadEgresados());
            } else {
                mapa.put(clave, dto);
            }
        }

        // Ordenar y devolver
        List<ReporteCarreraDTO> resultado = new ArrayList<>(mapa.values());
        resultado.sort(Comparator.comparing(ReporteCarreraDTO::getNombreCarrera)
                .thenComparing(ReporteCarreraDTO::getAnio));
        return resultado;
    }


}






