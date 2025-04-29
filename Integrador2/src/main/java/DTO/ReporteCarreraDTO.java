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

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public void setCantidadInscriptos(long cantidadInscriptos) {
        this.cantidadInscriptos = cantidadInscriptos;
    }

    public long getCantidadEgresados() {
        return cantidadEgresados;
    }

    public void setCantidadEgresados(long cantidadEgresados) {
        this.cantidadEgresados = cantidadEgresados;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
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
