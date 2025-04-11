package tp1.dao;

import java.sql.Connection;

import tp1.entities.Cliente;

public class ClienteDAO {
	private Connection conn;

	public ClienteDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public int insert(Cliente dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }


    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    public Cliente find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }


    public boolean update(Cliente dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
