package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pregunta;

@Service
public interface IPreguntaService {

	public void guardarPregunta(Pregunta preguntaparaguardar);
	public void modificarPregunta(Pregunta pregunta);
	public void eliminarPregunta(Long codPregunta) throws Exception;
	public Pregunta buscarPregunta(Long codPregunta) throws Exception;
	public List<Pregunta> listadoPregunta(); 
}
