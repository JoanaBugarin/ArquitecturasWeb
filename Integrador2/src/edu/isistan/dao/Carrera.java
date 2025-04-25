package edu.isistan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	private String carrera;
	
	@Column(nullable=false)
	private Integer duracion;
	
	@OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EstudianteCarrera> estudiantes;
	
	public Carrera() {
		super();
	}

	public Carrera(Integer id, String carrera, Integer duracion) {
		super();
		this.id = id;
		this.carrera = carrera;
		this.duracion = duracion;
		this.estudiantes = new ArrayList<EstudianteCarrera>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public List<EstudianteCarrera> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<EstudianteCarrera> estudiantes) {
		this.estudiantes = estudiantes;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", carrera=" + carrera + ", duracion=" + duracion + ", estudiantes=" + estudiantes
				+ "]";
	}
}
