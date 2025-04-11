package tp1.dao;

import java.sql.Connection;

import tp1.entities.Producto;

public class ProductoDAO {
	private Connection conn;

	public ProductoDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public int insert(Producto dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }


    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    public Producto find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }


    public boolean update(Producto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
