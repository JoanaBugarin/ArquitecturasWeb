package com.tp.integrador3.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.tp.integrador3.service.exception.DuplicateEstudianteException;

/**
 * Manejador global de Exceptions para las exceptions lanzadas por la validacion
 * de los elementos DTO correspondiente a un elemento Request
 * Si la validacion falla, lanza un MethodArgumentNotValidException que es
 * capturado por esta clase, formateado y enviado como respuesta a la peticion
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        body.put("message", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        System.out.println("[GlobalExceptionHandler] DataIntegrityViolationException capturada: " + ex.getMessage());
        Map<String, Object> body = new HashMap<>();
        String msg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        if (msg != null && msg.contains("dni")) {
            body.put("message", "Ya existe un estudiante con ese DNI.");
        } else if (msg != null && msg.contains("lu")) {
            body.put("message", "Ya existe un estudiante con ese número de libreta universitaria (LU).");
        } else {
            body.put("message", "Ya existe un estudiante con un campo único duplicado.");
        }
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    // Maneja las exception lanzadas al intentar registrar un estudiante con un dni
    // o lu ya existente
    @ExceptionHandler(DuplicateEstudianteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDuplicateEstudianteException(DuplicateEstudianteException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
