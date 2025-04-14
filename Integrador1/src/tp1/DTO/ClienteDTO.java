package tp1.DTO;

public class ClienteDTO {
    private String nombre;
    private String email;
    private int total_facturado;

    public ClienteDTO(String nombre, String email, int cantidad) {
        this.nombre = nombre;
        this.email = email;
        this.total_facturado = cantidad;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
               ", email='" + email + '\'' +
               ", total facturado=" + total_facturado;
    }
}
