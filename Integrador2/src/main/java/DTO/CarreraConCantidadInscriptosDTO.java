package main.java.DTO;

public class CarreraConCantidadInscriptosDTO {
    private String nombre;
    private long cantidadInscriptos;

    public CarreraConCantidadInscriptosDTO(){}

    public CarreraConCantidadInscriptosDTO(String nombre, long cantidad){
        this.nombre = nombre;
        this.cantidadInscriptos = cantidad;
    }

    @Override
    public String toString() {
        return "Nombre de la carrera ='" + nombre + '\'' +
               ", cantidad de inscriptos=" + cantidadInscriptos +
               '}';
    }
}
