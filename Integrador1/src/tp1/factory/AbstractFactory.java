package tp1.factory;

import tp1.dao.ClienteDAO;
import tp1.dao.FacturaDAO;
import tp1.dao.FacturaProductoDAO;
import tp1.dao.ProductoDAO;

public abstract class AbstractFactory {

	//public static final int MYSQL_JDBC = 2;
    public static final int POSTGRES_JDBC = 1;
    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract FacturaProductoDAO getFacturaProductoDAO();
    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case POSTGRES_JDBC : {
                return PostgresDAOFactory.getInstance();
            }
            //case MYSQL_JDBC: return null;
            default: return null;
        }
    }
}
