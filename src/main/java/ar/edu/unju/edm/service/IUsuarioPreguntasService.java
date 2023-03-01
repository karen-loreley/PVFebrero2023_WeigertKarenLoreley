package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.UsuarioPregunta;

@Service
public interface IUsuarioPreguntasService {

	  public List<UsuarioPregunta> buscarUsuario(Long id,Integer nivel);
	  public Integer SumarPuntaje(List<UsuarioPregunta> puntaje);
	  public void guardarPregunta(UsuarioPregunta puntaje);
	  public void reinicioPuntaje(Long id,Integer nivel);
}
