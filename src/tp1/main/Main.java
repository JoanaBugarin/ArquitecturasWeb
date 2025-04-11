package tp1.main;

import java.util.List;

import tp1.dao.ClienteDAO;
import tp1.dao.FacturaDAO;
import tp1.dao.FacturaProductoDAO;
import tp1.dao.ProductoDAO;
import tp1.entities.Cliente;
import tp1.entities.Producto;
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
        
        System.out.println("Producto que más recaudó: ");
        Producto productoXrecaudacion = producto.find();
        System.out.println(productoXrecaudacion);
        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");
        System.out.println("Clientes ordenados por mayor facturación: ");
//        List<Direccion> listadoDirecciones = direccion.selectList();
//        System.out.println(listadoDirecciones);
        List<Cliente> listadoClientes = cliente.selectList();
        for (Cliente cli : listadoClientes) {
            System.out.println(cli);
        }
	}
}
