package main.java.exception;

public class EstudianteYaMatriculadoException extends RuntimeException{
    public EstudianteYaMatriculadoException() {
        super("El estudiante ya se encuentra matriculado en la carrera");
    }
}
