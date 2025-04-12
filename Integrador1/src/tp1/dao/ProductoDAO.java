package tp1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public Producto find() {
    	String query = "SELECT p.idproducto AS idProducto, p.nombre AS producto, p.valor AS valor, " +
                "SUM(fp.cantidad * p.valor) AS recaudacion " +
                "FROM producto p " +
                "JOIN factura_producto fp ON p.idproducto = fp.idproducto " +
                "GROUP BY p.idproducto, p.nombre, p.valor " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1;";
    	Producto prodXrecaudacion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) { //
                Integer idProducto = rs.getInt("idProducto"); 
                String nombre = rs.getString("producto");
                Float valor = rs.getFloat("valor");

                // Crear una nueva instancia de Producto con los datos recuperados
                prodXrecaudacion = new Producto(idProducto, nombre, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return prodXrecaudacion;
    }


    public boolean update(Producto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
