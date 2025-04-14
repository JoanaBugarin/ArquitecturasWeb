package tp1.DTO;

public class ProductoDTO {
    private String nombre;
    private float valor;
    private int cantidad_vendida;

    public ProductoDTO(String nombre, float valor, int cantidad) {
        this.nombre = nombre;
        this.valor = valor;
        this.cantidad_vendida = cantidad;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
               ", valor=" + valor +
               ", cantidad vendida=" + cantidad_vendida +
               ", Total recaudado =" + cantidad_vendida * valor;
    }
}
