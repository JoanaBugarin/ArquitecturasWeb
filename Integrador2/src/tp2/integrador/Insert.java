package tp2.integrador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tp2.integrador.entities.Carrera;
import tp2.integrador.entities.Estudiante;
import tp2.integrador.entities.EstudianteCarrera;

public class Insert {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//a) dar de alta un estudiante
		Estudiante est = new Estudiante(36617518, "Joana", "Bugarin", 33, "Female", "Quequén", 999999999);
		em.persist(est);
		
		// Crear una carrera
        Carrera c = new Carrera(16, "Logistica", 4);
        em.persist(c);
        
        // Matricular al estudiante en la carrera
        EstudianteCarrera estCarrera = new EstudianteCarrera();
        estCarrera.setEstudiante(est);
        estCarrera.setCarrera(c);
        estCarrera.setInscripcion(2025);
        estCarrera.setGraduacion(0);
        estCarrera.setAntiguedad(0);
        em.persist(estCarrera);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
