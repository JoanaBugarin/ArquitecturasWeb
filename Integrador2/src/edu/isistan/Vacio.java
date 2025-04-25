package edu.isistan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Vacio {

	public static void main(String[] args) {
		//CREACIÓN DE LA BD CON JDBC
		String url = "jdbc:mysql://localhost:3306";
        String usuario = "root";
        String password = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            Statement stmt = conexion.createStatement();
            
            // Crear la base de datos si no existe
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS integrador2");
            System.out.println("Base de datos creada o ya existente: integrador2");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear la base de datos.");
        }
        
        //PROBAR CONEXION
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
