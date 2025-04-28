package main.java.exception;

public class EstudianteYaRegistradoException extends RuntimeException{
    public EstudianteYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}
