package com.tp.integrador3.service.dto.estudiantecarrera.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteCarreraDTO {
    private String nombreCarrera;
    private long cantidadInscriptos;
    private long cantidadEgresados;
    private int anio;
}
