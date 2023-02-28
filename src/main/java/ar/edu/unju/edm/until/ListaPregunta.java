package ar.edu.unju.edm.until;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Pregunta;

@Component
public class ListaPregunta {

	private List<Pregunta> listado = new ArrayList<>();
	
	
	public ListaPregunta() {
		
	}

	
	public List<Pregunta> getListado() {
		return listado;
	}

	public void setListado(List<Pregunta> listado) {
		this.listado = listado;
	}
	
}
