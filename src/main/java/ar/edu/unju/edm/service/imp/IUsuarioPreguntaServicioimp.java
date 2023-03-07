package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.UsuarioPregunta;
import ar.edu.unju.edm.repository.UsuarioPreguntasRepository;
import ar.edu.unju.edm.service.IUsuarioPreguntasService;

@Service
public class IUsuarioPreguntaServicioimp implements IUsuarioPreguntasService{
	
	@Autowired
	UsuarioPreguntasRepository repository;

	@Override
	public List<UsuarioPregunta> buscarUsuario(Long id, Integer nivel) {

		List<UsuarioPregunta> auxiliar = new ArrayList<>();
		List<UsuarioPregunta> auxiliar2 = new ArrayList<>();
		auxiliar=(List<UsuarioPregunta>) repository.findAll();
		for(int i=0; i < auxiliar.size();i++) {
			
			if(auxiliar.get(i).getUsuario().getDni()==id && auxiliar.get(i).getNivel().equals(nivel)) {
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	}

	@Override
	public Integer SumarPuntaje(List<UsuarioPregunta> puntaje) {
		Integer auxiliar=0;
		for(int i =0; i<puntaje.size(); i++) {
			auxiliar=auxiliar+puntaje.get(i).getTotal();
		}
		return auxiliar;
	}
	

	@Override
	public void guardarPregunta(UsuarioPregunta puntaje) {
		
		repository.save(puntaje);
		
	}
	
	 @Override
	  public void reinicioPuntaje(Long id, Integer nivel) {
	    List<UsuarioPregunta> auxiliar = new ArrayList<>();
	    auxiliar=(List<UsuarioPregunta>) repository.findAll();
	    for (int i = 0; i < auxiliar.size(); i++) {
	      if(auxiliar.get(i).getUsuario().getDni()==id && auxiliar.get(i).getNivel().equals(nivel)){
	        auxiliar.get(i).setTotal(0);;
	        repository.save(auxiliar.get(i));
	      }
	    }
	  }

}
