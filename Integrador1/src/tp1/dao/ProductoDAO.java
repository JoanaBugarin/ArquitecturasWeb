package tp1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tp1.DTO.ProductoDTO;
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


    public ProductoDTO find() {
    	String query = "SELECT p.idproducto AS idProducto, p.nombre AS producto, p.valor AS valor, " +
                "SUM(fp.cantidad * p.valor) AS recaudacion, " +
                "SUM(fp.cantidad) AS cantidad_vendida " +
                "FROM producto p " +
                "JOIN factura_producto fp ON p.idproducto = fp.idproducto " +
                "GROUP BY p.idproducto, p.nombre, p.valor " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1;";
    	ProductoDTO prodXrecaudacion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) { //
                String nombre = rs.getString("producto");
                Float valor = rs.getFloat("valor");
                int cant_vendidas = rs.getInt("cantidad_vendida");

                // Crear una nueva instancia de Producto con los datos recuperados
                prodXrecaudacion = new ProductoDTO(nombre, valor, cant_vendidas);
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
