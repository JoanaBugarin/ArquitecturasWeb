package com.tp.integrador3.service.dto.estudiante.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterEstudianteRequestDTO {
    private String nombre;
    private String apellido;
    private String ciudad;
    @Min(value = 0, message = "La pagina no puede ser negativa")
    private int page = 0;
    @Min(value = 1, message = "El limite no puede ser negativo")
    private int limit = 10;
    @Pattern(regexp = "dni|nombre|apellido|ciudad|lu", message = "El campo de ordenamiento debe ser dni, nombre, apellido, ciudad o lu")
    private String orderBy = "nombre";
    @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El metodo de ordenamiento debe ser ASC o DESC")
    private String sortDir = "ASC";
}
