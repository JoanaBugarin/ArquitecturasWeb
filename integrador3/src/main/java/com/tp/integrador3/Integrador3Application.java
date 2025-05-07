package com.tp.integrador3;

import com.opencsv.exceptions.CsvValidationException;
import com.tp.integrador3.utils.CargaDeDatos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class Integrador3Application {

	@Autowired
	CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {

		SpringApplication.run(Integrador3Application.class, args);
	}

	@PostConstruct
	public void init() throws IOException, CsvValidationException {
		cargaDeDatos.cargarDatosDesdeCSV();
	}

}
