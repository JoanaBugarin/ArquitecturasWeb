package com.tp.integrador3.service.dto.carrera.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraConCantidadInscriptosDTO {
    private String nombre;
    private long cantidadInscriptos;


}
