package tp1.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import tp1.entities.Cliente;
import tp1.entities.Factura;
import tp1.entities.FacturaProducto;
import tp1.entities.Producto;

public class HelperPostgreSQL {
    private Connection conn = null;

    public HelperPostgreSQL() {//Constructor
        String driver = "org.postgresql.Driver";
        String uri = "jdbc:postgresql://localhost:5432/integrador1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "postgres", "7518");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dropTables() throws SQLException {
        String dropCliente = "DROP TABLE IF EXISTS Cliente CASCADE";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();

        String dropFactura = "DROP TABLE IF EXISTS Factura CASCADE";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();
        
        String dropProducto = "DROP TABLE IF EXISTS Producto CASCADE";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();
        
        String dropFacProd = "DROP TABLE IF EXISTS Factura_Producto CASCADE";
        this.conn.prepareStatement(dropFacProd).execute();
        this.conn.commit();
    }

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
                "idCliente SERIAL PRIMARY KEY, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150));";
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();

        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura SERIAL PRIMARY KEY, " +
                "idCliente INT NOT NULL, " +
                "CONSTRAINT FK_idCliente FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente));";
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();
        
            String tableProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
                    "idProducto SERIAL PRIMARY KEY, " +
                    "nombre VARCHAR(45), " +
                    "valor FLOAT);";
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();
        
        String tableFacProd = "CREATE TABLE IF NOT EXISTS Factura_Producto(" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "PRIMARY KEY (idFactura, idProducto), " +
                "CONSTRAINT FK_idFactura FOREIGN KEY (idFactura)  REFERENCES Factura(idFactura), " +
                "CONSTRAINT FK_idProducto FOREIGN KEY (idProducto) REFERENCES Producto (idProducto));";
        this.conn.prepareStatement(tableFacProd).execute();
        this.conn.commit();
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {
            System.out.println("Populating DB...");
            for(CSVRecord row : getData("clientes.csv")) {
                if(row.size() >= 3) { // Verificar que hay al menos 3 campos en el CSVRecord
                    String idCliente = row.get(0);
                    String nombre = row.get(1);
                    String email = row.get(2);
                    if(!idCliente.isEmpty() && !nombre.isEmpty() && !email.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idCliente);
                            Cliente cliente = new Cliente(id, nombre, email);
                            insertCliente(cliente, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de Cliente: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Clientes insertados");

            for (CSVRecord row : getData("facturas.csv")) {
                if (row.size() >= 2) { // Verificar que hay al menos 2 campos en el CSVRecord
                    String idFactura = row.get(0);
                    String idCliente = row.get(1);

                    if (!idFactura.isEmpty() && !idCliente.isEmpty()) {
                        try {
                            int idFac = Integer.parseInt(idFactura);
                            int idCli = Integer.parseInt(idCliente);

                            Factura factura = new Factura(idFac, idCli);
                            insertFactura(factura, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("Facturas insertadas");
            
            for (CSVRecord row : getData("productos.csv")) {
                if (row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                    String idProducto = row.get(0);
                    String nombre = row.get(1);
                    String valor = row.get(2);

                    if (!idProducto.isEmpty() && !nombre.isEmpty() && !valor.isEmpty()) {
                        try {
                            int idProd = Integer.parseInt(idProducto);
                            float val = Float.parseFloat(valor);

                            Producto producto = new Producto(idProd, nombre, val);
                            insertProducto(producto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de producto: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("Productos insertados");
            
            for (CSVRecord row : getData("facturas-productos.csv")) {
                if (row.size() >= 3) { // Verificar que hay al menos 3 campos en el CSVRecord
                    String idFactura = row.get(0);
                    String idProducto = row.get(1);
                    String cantidad = row.get(2);

                    if (!idFactura.isEmpty() && !idProducto.isEmpty() && !cantidad.isEmpty()) {
                        try {
                            int idFac = Integer.parseInt(idFactura);
                            int idProd = Integer.parseInt(idProducto);
                            int cant = Integer.parseInt(cantidad);

                            FacturaProducto facProd = new FacturaProducto(idFac, idProd, cant);
                            insertFacProd(facProd, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura-producto: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("facturas-productos insertados");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertFacProd(FacturaProducto facProd, Connection conn2) throws Exception {
    	String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, facProd.getIdFactura());
            ps.setInt(2, facProd.getIdProducto());
            ps.setInt(3, facProd.getCantidad());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
	}

	private int insertProducto(Producto producto, Connection conn2)  throws Exception {
    	 String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (DEFAULT, ?, ?)";
         PreparedStatement ps = null;
         try {
             ps = conn.prepareStatement(insert);
             ps.setString(1, producto.getNombre());
             ps.setFloat(2, producto.getValor());
             if (ps.executeUpdate() == 0) {
                 throw new Exception("No se pudo insertar");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             closePsAndCommit(conn, ps);
         }
         return 0;
	}

	private int insertCliente (Cliente cliente, Connection conn) throws Exception{
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (DEFAULT, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    private int insertFactura(Factura factura, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (DEFAULT, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, factura.getIdCliente());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}

