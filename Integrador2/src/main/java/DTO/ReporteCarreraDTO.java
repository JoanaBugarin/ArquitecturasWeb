package main.java.DTO;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private long cantidadInscriptos;
    private long cantidadEgresados;
    private int anio;

    public ReporteCarreraDTO(int anio, String nombreCarrera, long cantidadInscriptos, long cantidadEgresados) {
        this.anio = anio;
        this.nombreCarrera = nombreCarrera;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }

    @Override
    public String toString() {
        return "Reporte de carreras {" +
                "Carrera =" + nombreCarrera +
                ", Anio ='" + anio + '\'' +
                ", Cantidad de inscriptos ='" + cantidadInscriptos + '\'' +
                ", Cantidad de egresados =" + cantidadEgresados +
                '}';
    }


}
