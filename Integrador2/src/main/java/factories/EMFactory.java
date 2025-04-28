package main.java.factories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFactory {
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("Example");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
