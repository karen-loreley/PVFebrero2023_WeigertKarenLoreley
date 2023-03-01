package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pregunta;

@Service
public interface IPreguntaService {

	public void guardarPregunta(Pregunta preguntaparaguardar);
	public Pregunta actulizarPregunta(Pregunta pregunta);
	public Pregunta obtenerPregunta(Long id);
	public Pregunta buscarPregunta(Integer nivel,int i);
	public List<Pregunta> listadoPregunta(Integer nivel); 
	

}
