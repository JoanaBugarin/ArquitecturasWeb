package main.java.repositories;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import main.java.entities.Carrera;
import main.java.entities.Estudiante;
import main.java.entities.EstudianteCarrera;
import main.java.exception.EstudianteYaMatriculadoException;
import main.java.factories.EMFactory;

import java.io.FileReader;
import java.time.LocalDate;

public class EstudianteCarreraRepository {


    public EstudianteCarreraRepository(){
    }

    public void insertarDesdeCSV(String rutaArchivo) {
        EntityManager em = EMFactory.getEntityManager();
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext(); // salta cabecer
            em.getTransaction().begin();
            while ((linea = reader.readNext()) != null) {
                EstudianteCarrera estCar= new EstudianteCarrera();
                Estudiante est = em.find(Estudiante.class, Integer.parseInt(linea[1]));
                if (est == null){
                    System.out.println("No se encontro el estudiante "+ Integer.parseInt(linea[1]));
                    continue;
                }
                estCar.setEstudiante(est);
                Carrera carr=em.find(Carrera.class,Integer.parseInt(linea[2]));
                if (carr == null){
                    System.out.println("No se encontro la carrera "+ Integer.parseInt(linea[2]));
                    continue;
                }
                estCar.setCarrera(carr);
                estCar.setInscripcion(Integer.parseInt(linea[3]));
                estCar.setGraduacion(Integer.parseInt(linea[4]));
                estCar.setAntiguedad(Integer.parseInt(linea[5]));

                em.persist(estCar);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static boolean estaInscripto(EntityManager em, Estudiante estudiante, Carrera carrera) {
        TypedQuery<EstudianteCarrera> tq = em.createQuery("SELECT ec FROM EstudianteCarrera ec WHERE ec.estudiante = :estudiante AND ec.carrera = :carrera", EstudianteCarrera.class);
        tq.setParameter("estudiante", estudiante);
        tq.setParameter("carrera", carrera);
        try{
            EstudianteCarrera ec = tq.getSingleResult();
            return ec != null;
        }catch (NoResultException e){
            return false;
        }
    }
    /**
     * Permite matricular un Estudiante en una Carrera dada. Para que un Estudiante pueda ser matriculado en un Carrera ambos deben estar registrados en la base de datos y no debe existir la incripcion de dicho estudiante en esa carrera
     * @param estudiante
     * @param carrera
     * @return
     * @throws IllegalArgumentException
     */
    public EstudianteCarrera matricularEstudiante(Estudiante estudiante, Carrera carrera) throws IllegalArgumentException{
        EntityManager em = EMFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            if (estudiante == null) {
                throw new IllegalArgumentException("El estudiante no puede ser nulo");
            }
            if (carrera == null) {
                throw new IllegalArgumentException("La carrera no puede ser nulo");
            }
            //Verificar que la carrera ingresada exista
            if (!CarreraRepository.verificarCarreraExiste(em, carrera)) {
                throw new IllegalArgumentException("El carrera ingresada no se encuentra en la base de datos");
            }
            if (!EstudianteRepository.verificarEstudianteExiste(em, estudiante)) {
                throw new IllegalArgumentException("El estudiante no se encuentra en la base de datos");
            }
            if (estaInscripto(em,estudiante,carrera)) {
                throw new EstudianteYaMatriculadoException();
            }
            LocalDate fecha = LocalDate.now();
            int anioActual = fecha.getYear();
            EstudianteCarrera ec = new EstudianteCarrera(estudiante, carrera, anioActual, 0, 0);
            em.persist(ec);
            em.getTransaction().commit();
            return ec;
        }catch (Exception e) {
            throw e;
        }finally {
            em.close();
        }
    }
}
