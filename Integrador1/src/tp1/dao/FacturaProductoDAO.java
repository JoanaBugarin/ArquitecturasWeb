package tp1.dao;

import java.sql.Connection;

import tp1.entities.FacturaProducto;

public class FacturaProductoDAO {
	private Connection conn;

	public FacturaProductoDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public int insert(FacturaProducto dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }


    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    public FacturaProducto find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }


    public boolean update(FacturaProducto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
