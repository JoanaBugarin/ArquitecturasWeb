package tp1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp1.DTO.ClienteDTO;
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

    public List<ClienteDTO> selectList() {
        String query = "SELECT c.idcliente AS idCliente, c.nombre AS cliente, c.email AS email, " +
                       "SUM(fp.cantidad * p.valor) AS total_facturado " +
                       "FROM cliente c " +
                       "JOIN factura f ON c.idcliente = f.idcliente " +
                       "JOIN factura_producto fp ON f.idfactura = fp.idfactura " +
                       "JOIN producto p ON fp.idproducto = p.idproducto " +
                       "GROUP BY c.idcliente, c.nombre, c.email " +
                       "ORDER BY total_facturado DESC;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClienteDTO> listado = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) { // Recorrer el ResultSet
                String nombre = rs.getString("cliente");
                String email = rs.getString("email");
                int cant_vendida = rs.getInt("total_facturado");

                ClienteDTO cliente = new ClienteDTO(nombre, email, cant_vendida);
                listado.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }

}
