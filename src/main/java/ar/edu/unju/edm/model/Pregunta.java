package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="pregunta")
public class Pregunta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name= "codPregunta")
	private Long codPregunta;
	@NotEmpty
	private String enunciado;
	@NotNull
	private Integer nivel;
	@NotEmpty
	private String opcion1;
	@NotEmpty
	private String opcion2;
	@NotEmpty
	private String opcion3;
	@NotEmpty
	private String opcion4;
	@NotEmpty
	private String opcionCorrecta;
	@NotEmpty
	private int puntaje;
	private Boolean estadoPregunta;
	
	
	public Pregunta() {
	}

	public Pregunta(Long codPregunta, @NotEmpty String enunciado, @NotNull Integer nivel, @NotEmpty String opcion1,
			@NotEmpty String opcion2, @NotEmpty String opcion3, @NotEmpty String opcion4,
			@NotEmpty String opcionCorrecta, @NotEmpty int puntaje, Boolean estadoPregunta) {
		this.codPregunta = codPregunta;
		this.enunciado = enunciado;
		this.nivel = nivel;
		this.opcion1 = opcion1;
		this.opcion2 = opcion2;
		this.opcion3 = opcion3;
		this.opcion4 = opcion4;
		this.opcionCorrecta = opcionCorrecta;
		this.puntaje = puntaje;
		this.estadoPregunta = estadoPregunta;
	}




	public Long getCodPregunta() {
		return codPregunta;
	}


	public void setCodPregunta(Long codPregunta) {
		this.codPregunta = codPregunta;
	}


	public String getEnunciado() {
		return enunciado;
	}


	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Integer getNivel() {
		return nivel;
	}


	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}


	public String getOpcion1() {
		return opcion1;
	}


	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}


	public String getOpcion2() {
		return opcion2;
	}


	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}


	public String getOpcion3() {
		return opcion3;
	}


	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}


	public String getOpcion4() {
		return opcion4;
	}


	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}


	public String getOpcionCorrecta() {
		return opcionCorrecta;
	}


	public void setOpcionCorrecta(String opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}


	public int getPuntaje() {
		return puntaje;
	}


	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}


	public Boolean getEstadoPregunta() {
		return estadoPregunta;
	}


	public void setEstadoPregunta(Boolean estadoPregunta) {
		this.estadoPregunta = estadoPregunta;
	}
	
	

	
}
