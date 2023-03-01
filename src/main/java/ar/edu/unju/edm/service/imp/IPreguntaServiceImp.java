package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.repository.PreguntaRepository;
import ar.edu.unju.edm.service.IPreguntaService;

@Service
public class IPreguntaServiceImp implements IPreguntaService{
	
	
	@Autowired
	PreguntaRepository preguntaRepository;
	

	@Override
	public void guardarPregunta(Pregunta preguntaparaguardar) {
		
		preguntaparaguardar.setEstadoPregunta(true);
		preguntaRepository.save(preguntaparaguardar);
	}

	@Override
	public Pregunta actulizarPregunta(Pregunta pregunta) {

		return preguntaRepository.save(pregunta);
		
	}
	
	 @Override
		public Pregunta obtenerPregunta(Long id) {
			return preguntaRepository.findById(id).get();
		}

	@Override
	public Pregunta buscarPregunta(Integer nivel,int i) {
		List<Pregunta> preguntas=listadoPregunta(nivel);
		Pregunta encontrar=null;
		for(int j=0;j<i;j++) {
			encontrar=preguntas.get(j);
		}
		return encontrar;
	}
	

	@Override
	public List<Pregunta> listadoPregunta(Integer nivel) {
		// TODO Auto-generated method stub
		
		List<Pregunta> aux = new ArrayList<>();
		List<Pregunta> aux2 = new ArrayList<>();
		
		aux=(List<Pregunta>) preguntaRepository.findAll();
		for(int i=0;i<aux.size();i++) {
			if(aux.get(i).getNivel().equals(nivel)) {
				aux2.add(aux.get(i));
			}
		}
		
		return aux2;
	}

}
