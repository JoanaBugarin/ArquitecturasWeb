package tp1.dao;

import java.sql.Connection;

import tp1.entities.Factura;

public class FacturaDAO {
	private Connection conn;

	public FacturaDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public int insert(Factura dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }


    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    public Factura find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }


    public boolean update(Factura dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
	
	
}
