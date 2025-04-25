package tp2.integrador;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tp2.integrador.entities.Carrera;
import tp2.integrador.entities.Estudiante;

public class Select {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		//c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
		List<Estudiante> estudiantes = em.createQuery(
	            "SELECT e FROM Estudiante e ORDER BY e.apellido ASC", Estudiante.class
	        ).getResultList();
	        
	        // Mostrar los resultados
	        for (Estudiante estudiante : estudiantes) {
	            System.out.println(estudiante.getApellido() + ", " + estudiante.getNombre());
	        } 
	        
	        System.out.println("************************************************");        

	        //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
	        @SuppressWarnings("unchecked")
			List<Object[]> resultado = em.createQuery(
	        	    "SELECT c, COUNT(e) AS cantidadEstudiantes " +
	        	    "FROM Carrera c JOIN c.estudiantes e " +
	        	    "GROUP BY c " +
	        	    "ORDER BY cantidadEstudiantes DESC"
	        	).getResultList();
	        
	        System.out.println("Carreras con estudiantes inscriptos, ordenadas por cantidad:");
	        for (Object[] fila : resultado) {
	            Carrera carrera = (Carrera) fila[0];
	            Long cantidad = (Long) fila[1];

	            System.out.println("Carrera: " + carrera.getCarrera() + " | Inscriptos: " + cantidad);
	        }
	                

//		carreras.forEach(e -> System.out.println(e));
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
