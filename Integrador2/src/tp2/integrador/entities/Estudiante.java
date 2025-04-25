package tp2.integrador.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Estudiante {
	
	@Column(nullable=false)
	private int dni;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	@Column(nullable=false)
	private int edad;
	@Column(nullable=false)
	private String genero;
	@Column(nullable=false)
	private String ciudad;
	@Id
	private int lu;
	
	
	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EstudianteCarrera> carreras;
	
	public Estudiante() {
		super();
	}	

	public Estudiante(int dni, String nombre, String apellido, int edad, String genero, String ciudad, int lu) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.ciudad = ciudad;
		this.lu = lu;
		this.carreras = new ArrayList<EstudianteCarrera>();
	}



	public int getDni() {
		return dni;
	}



	public void setDni(int dni) {
		this.dni = dni;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public int getEdad() {
		return edad;
	}



	public void setEdad(int edad) {
		this.edad = edad;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public String getCiudad() {
		return ciudad;
	}



	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public int getLu() {
		return lu;
	}



	public void setLu(int lu) {
		this.lu = lu;
	}



	public List<EstudianteCarrera> getCarreras() {
		return carreras;
	}



	public void setCarreras(ArrayList<EstudianteCarrera> carreras) {
		this.carreras = carreras;
	}



	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", ciudad=" + ciudad + ", lu=" + lu + ", carreras=" + carreras + "]";
	}
	

}
