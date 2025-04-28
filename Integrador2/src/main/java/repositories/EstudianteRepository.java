package main.java.repositories;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.java.DTO.EstudianteDTO;
import main.java.entities.Carrera;
import main.java.entities.Estudiante;
import main.java.exception.EstudianteYaRegistradoException;
import main.java.factories.EMFactory;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EstudianteRepository {
    //Conjunto de valores aceptables como criterios para las query sobre Estudiante
    private static final Set<String> CRITERIOS_VALIDOS = Set.of(
            "dni", "nombre", "apellido", "edad", "genero", "ciudad", "lu"
    );

    public EstudianteRepository(){
    }

    public void insertarDesdeCSV(String rutaArchivo) {
        EntityManager em = EMFactory.getEntityManager();
        
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Estudiante estudiante=new Estudiante();
                estudiante.setDni(Integer.parseInt(linea[0]));
                estudiante.setNombre(linea[1]);
                estudiante.setApellido(linea[2]);
                estudiante.setEdad(Integer.parseInt(linea[3]));
                estudiante.setGenero(linea[4]);
                estudiante.setCiudad(linea[5]);
                estudiante.setLu(Integer.parseInt(linea[6]));
                Estudiante estaRegistrado = em.find(Estudiante.class, estudiante.getDni());
                if (estaRegistrado != null)
                    continue; //Ignora los estudiantes que ya se encuentran registrados
                em.persist(estudiante);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Verifica si un estudiante ya se encuentra registrado en la base de datos.
     * @param em EntityManager con el que se hace la consulta
     * @param estudiante Estudiante que se quiere verificar si se encuentra registrado
     * @return True si el estudiante ya se encuentra registrado en la base de datos. Caso contrario, retorna False
     */
    public static boolean verificarEstudianteExiste(EntityManager em, Estudiante estudiante) {
        Estudiante aux = em.find(Estudiante.class, estudiante.getDni());
        if (aux != null){
            return true;
        }
        return false;
    }

    /**
     * Inserta un nuevo Estudiante en la base de datos.
     *
     * @param estudiante Estudiante que se quiere insertar
     * @throws IllegalArgumentException Si se le pasa a la funcion un Estudiante que es null
     * @throws RuntimeException Se lanza en caso que exista un error al momento de insertar el Estudiante.
     *                          Esto puede darse porque el Estudiante ya existe en la base de datos, o porque hubo un error al intentar insertarlo
     */
    public void insertarEstudiante(Estudiante estudiante) {
        EntityManager em = EMFactory.getEntityManager();
        try{
            if (estudiante == null) {
                throw new IllegalArgumentException("El estudiante es requerido");
            }
            if (verificarEstudianteExiste(em, estudiante))
                throw new EstudianteYaRegistradoException("El estudiante ya se encuentra registrado en la base de datos");
            em.getTransaction().begin();
            em.persist(estudiante);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
        finally {
            em.close();
        }

    }

    /**
     * Retorna una lista con los estudiantes. La lista se retorna ordenada por el criterio indicado
     * @param criterio Criterio por el cual se quiere ordenar la lista de estudiantes obtenida
     * @return List<EstudianteDTO> Lista de estudiantes recuperada de la base de datos
     * @throws IllegalArgumentException Se lanza cuando el criterio ingresado para realizar el ordenamiento de datos no esta dentro de los criterios validos
     */
    public List<EstudianteDTO> getEstudiantesOrdenados(String criterio){
        if (!this.isCriterioValido(criterio))
            throw new IllegalArgumentException("El criterio ingresado no es valido");
        EntityManager em = EMFactory.getEntityManager();
        em.getTransaction().begin();
        List<EstudianteDTO> estudiantesOrdenados = em.createQuery("SELECT new main.java.DTO.EstudianteDTO(e.dni,e.nombre,e.apellido,e.edad, e.genero, e.ciudad, e.lu) FROM Estudiante e ORDER BY e." + criterio + " ASC", EstudianteDTO.class
        ).getResultList();
        return estudiantesOrdenados;
    }

    /**
     * Verifica que un criterio sea valido. Un criterio se concidera valido si no esta vacio, no es null y es alguno de los atributos de la clase Estudiante
     * @param criterio Criterio que se quiere aplicar a la query
     * @return boolean. True si el criterio es aceptado. Caso contrario, false
     */
    private boolean isCriterioValido(String criterio) {
        if (criterio == "" || criterio == null)
            return false;
        return CRITERIOS_VALIDOS.contains(criterio);
    }

    /**
     * Retorna el estudiante a quien le corresponde el numero de libreta universitaria. Si no existe, retorna null
     * @param lu Número de libreta universitaria que se quiere buscar
     * @return EstudianteDTO | null. Retorna el Estudiante con dicho numero de libreta universitaria o null si no lo encuentra
     */
    public EstudianteDTO getEstudiantePorLu(int lu){
        if (lu <= 0){
            throw new IllegalArgumentException("El número de libreta universitaria no es valido");
        }
        EntityManager em = EMFactory.getEntityManager();
        em.getTransaction().begin();
        TypedQuery<EstudianteDTO> tq = em.createQuery("SELECT new main.java.DTO.EstudianteDTO(e.dni,e.nombre,e.apellido,e.edad, e.genero, e.ciudad, e.lu) FROM Estudiante e WHERE e.lu = :libreta", EstudianteDTO.class);
        tq.setParameter("libreta", lu);
        return tq.getSingleResult();
    }

    /**
     * Retorna todos los estudiantes que contengan el genero igual al solicitado
     * @param genero Genero por el cual se quiere realizar la busqueda
     * @return List<EstudianteDTO> Lista con los estudiantes que cumplen con la condicion
     */
    public List<EstudianteDTO> getEstudiantesPorGenero(String genero){
        EntityManager em = EMFactory.getEntityManager();
        em.getTransaction().begin();
        TypedQuery<EstudianteDTO> tq = em.createQuery("SELECT new main.java.DTO.EstudianteDTO(e.dni,e.nombre,e.apellido,e.edad, e.genero, e.ciudad, e.lu) FROM Estudiante e WHERE e.genero = :genero", EstudianteDTO.class);
        tq.setParameter("genero", genero);
        return tq.getResultList();
    }

    /**
     * Retorna todos los estudiantes que se encuentran en la Carrera indicada y pertenecen a una ciudad de residencia dada
     * @param carrera Carrera por la cual se quiere buscar lo Estudiantes inscriptos
     * @param filtroCiudad Ciudad de residencia por la cual se quiere filtrar la lista de estudiantes
     * @return List<EstudianteDTO> Lista de estudiantes que cumplen con la condicion.
     */
    public List<EstudianteDTO> getEstudiantesPorCarrera(Carrera carrera, String filtroCiudad){
        EntityManager em = EMFactory.getEntityManager();
        em.getTransaction().begin();
        //Verificar que la carrera exista. La verificacion la realiza el CarreraRepository
        if (!CarreraRepository.verificarCarreraExiste(em, carrera))
            throw new IllegalArgumentException("La carrera ingresada no existe en la base de datos");

        TypedQuery<EstudianteDTO> tq = em.createQuery("SELECT new main.java.DTO.EstudianteDTO(e.dni,e.nombre,e.apellido, e.edad,e.genero,e.ciudad,e.lu) FROM Estudiante e JOIN EstudianteCarrera ec ON ec.estudiante = e WHERE ec.carrera = :carrera AND e.ciudad = :ciudad", EstudianteDTO.class);
        tq.setParameter("carrera", carrera);
        tq.setParameter("ciudad", filtroCiudad);
        return tq.getResultList();
    }
}
