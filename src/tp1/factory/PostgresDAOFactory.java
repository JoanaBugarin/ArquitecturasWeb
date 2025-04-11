package tp1.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tp1.dao.ClienteDAO;
import tp1.dao.FacturaDAO;
import tp1.dao.FacturaProductoDAO;
import tp1.dao.ProductoDAO;

public class PostgresDAOFactory extends AbstractFactory {

	private static PostgresDAOFactory instance = null;

    public static final String DRIVER = "org.postgresql.Driver";
    public static final String uri = "jdbc:postgresql://localhost:5432/postgres";
    public static Connection conn;

    private PostgresDAOFactory() {
    }

    public static synchronized PostgresDAOFactory getInstance() {
        if (instance == null) {
            instance = new PostgresDAOFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "postgres", "7518");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(createConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAO(createConnection());
    }
    
    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoDAO(createConnection());
    }
    
    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {
        return new FacturaProductoDAO(createConnection());
    }
}
