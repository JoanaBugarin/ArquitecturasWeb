package edu.isistan.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EstudianteCarrera {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_estudiante", nullable = false)
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "id_carrera", nullable = false)
	private Carrera carrera;

	@Column(nullable = false)
	private Integer inscripcion;

	@Column(nullable = false)
	private Integer graduacion;

	@Column(nullable = false)
	private Integer antiguedad;

	public EstudianteCarrera() {}

	public EstudianteCarrera(Integer id, Estudiante estudiante, Carrera carrera, Integer inscripcion,
	                         Integer graduacion, Integer antiguedad) {
	     super();
	     this.id = id;
	     this.estudiante = estudiante;
	     this.carrera = carrera;
	     this.inscripcion = inscripcion;
	     this.graduacion = graduacion;
	     this.antiguedad = antiguedad;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Integer getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Integer inscripcion) {
		this.inscripcion = inscripcion;
	}

	public Integer getGraduacion() {
		return graduacion;
	}

	public void setGraduacion(Integer graduacion) {
		this.graduacion = graduacion;
	}

	public Integer getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}

	@Override
	public String toString() {
		return "EstudianteCarrera [id=" + id + ", estudiante=" + estudiante + ", carrera=" + carrera + ", inscripcion="
				+ inscripcion + ", graduacion=" + graduacion + ", antiguedad=" + antiguedad + "]";
	}

}
