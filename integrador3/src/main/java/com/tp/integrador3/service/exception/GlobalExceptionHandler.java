package com.tp.integrador3.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manejador global de Exceptions para las exceptions lanzadas por la validacion de los elementos DTO correspondiente a un elemento Request
 * Si la validacion falla, lanza un MethodArgumentNotValidException que es capturado por esta clase, formateado y enviado como respuesta a la peticion
 */
@ControllerAdvice
public class GlobalExceptionHandler  {
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
}
