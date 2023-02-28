package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Entity
@Component
public class Usuario {
	
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	private String email;
	@NotEmpty //vacio string
	private String contrasena;
	private Boolean estado;
	
	@Id
	@NotNull//para numeros
	@Min(value=1000000,message="el DNI debe ser mayor a un millon")
	@Max(value=99999999,message="el DNI debe ser menor a un 99999999")
	private Integer dni;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaNac;
	
	@NotNull
	private String tipo;
	
	public Usuario(){
		
	}
	
	
	
	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public Usuario(String email, String contrasena, String nombre, String apellido) {
		super();
		this.email = email;
		this.contrasena= contrasena;
		this.nombre= nombre;
		this.apellido = apellido;
		
		
	}

}
