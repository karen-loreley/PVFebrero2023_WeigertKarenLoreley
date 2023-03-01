package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="usuario")
public class Usuario {
	
	@NotEmpty
	@Column(name = "nombre")
	private String nombre;
	@NotEmpty
	@Column(name = "apellido")
	private String apellido;
	@NotEmpty
	private String email;
	@NotEmpty //vacio string
	@Column(name = "contrasena")
	private String contrasena;
	private Boolean estado;
	
	@Id
	@NotNull//para numeros
	@Min(value=1000000,message="el DNI debe ser mayor a un millon")
	@Max(value=99999999,message="el DNI debe ser menor a un 99999999")
	private Long dni;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "fecha_nac")
	private LocalDate fechaNac;
	
	@NotNull
	@Column(name = "tipo")
	private String tipo;

	private Integer puntaje1;
	private Integer puntaje2;
	
	
	public Usuario(){
		
	}


	public Usuario(@NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty String email,
			@NotEmpty String contrasena, Boolean estado,
			@NotNull @Min(value = 1000000, message = "el DNI debe ser mayor a un millon") @Max(value = 99999999, message = "el DNI debe ser menor a un 99999999") Long dni,
			LocalDate fechaNac, @NotNull String tipo, Integer puntaje1, Integer puntaje2) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.estado = estado;
		this.dni = dni;
		this.fechaNac = fechaNac;
		this.tipo = tipo;
		this.puntaje1 = puntaje1;
		this.puntaje2 = puntaje2;
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


	public Boolean getEstado() {
		return estado;
	}


	public void setEstado(Boolean estado) {
		this.estado = estado;
	}


	public Long getDni() {
		return dni;
	}


	public void setDni(Long dni) {
		this.dni = dni;
	}


	public LocalDate getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Integer getPuntaje1() {
		return puntaje1;
	}


	public void setPuntaje1(Integer puntaje1) {
		this.puntaje1 = puntaje1;
	}


	public Integer getPuntaje2() {
		return puntaje2;
	}


	public void setPuntaje2(Integer puntaje2) {
		this.puntaje2 = puntaje2;
	}

	
}