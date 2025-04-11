package tp1.main;

import tp1.dao.ClienteDAO;
import tp1.dao.FacturaDAO;
import tp1.dao.FacturaProductoDAO;
import tp1.dao.ProductoDAO;
import tp1.factory.AbstractFactory;
import tp1.utils.HelperPostgreSQL;

public class Main {

	public static void main(String[] args) throws Exception {
		HelperPostgreSQL pgSQL = new HelperPostgreSQL();
		pgSQL.dropTables();
		pgSQL.createTables();
		pgSQL.populateDB();
		pgSQL.closeConnection();

		AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        System.out.println();
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println();
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        FacturaProductoDAO facProd = chosenFactory.getFacturaProductoDAO();
	}
}
