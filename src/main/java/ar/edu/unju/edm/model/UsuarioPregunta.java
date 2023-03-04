package ar.edu.unju.edm.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name="usuariopreguntas")
public class UsuarioPregunta {

	  @Id
	  @GeneratedValue
	  (strategy=GenerationType.IDENTITY)
	  private Long id;
	  private Integer total;
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinColumn(name = "dni")
	  private Usuario usuario;
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinColumn(name ="CodPregunta")
	  private Pregunta pregunta;
	  private Integer nivel;
	  
	  
	public UsuarioPregunta() {
		
	}


	public UsuarioPregunta(Long id, Integer total, Usuario usuario, Pregunta pregunta, Integer nivel) {
		this.id = id;
		this.total = total;
		this.usuario = usuario;
		this.pregunta = pregunta;
		this.nivel = nivel;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Pregunta getPregunta() {
		return pregunta;
	}


	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}


	public Integer getNivel() {
		return nivel;
	}


	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	  
	
	
	  
}
