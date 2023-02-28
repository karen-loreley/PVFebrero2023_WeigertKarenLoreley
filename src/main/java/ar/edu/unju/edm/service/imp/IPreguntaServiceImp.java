package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.repository.PreguntaRepository;
import ar.edu.unju.edm.service.IPreguntaService;
import ar.edu.unju.edm.until.ListaPregunta;

@Service
public class IPreguntaServiceImp implements IPreguntaService{
	
	
	@Autowired
	ListaPregunta lista;
	@Autowired
	PreguntaRepository preguntaRepository;
	

	@Override
	public void guardarPregunta(Pregunta preguntaparaguardar) {
		
		preguntaparaguardar.setEstadoPregunta(true);
		preguntaRepository.save(preguntaparaguardar);
	}

	@Override
	public void modificarPregunta(Pregunta pregunta) {

		pregunta.setEstadoPregunta(true);
		preguntaRepository.save(pregunta);
		
	}
	
	@Override
	public void eliminarPregunta(Long codPregunta) throws Exception {
		
		Pregunta aux =new Pregunta();
		aux =buscarPregunta(codPregunta);
		aux.setEstadoPregunta(false);
		preguntaRepository.save(aux);
		
	}

	@Override
	public Pregunta buscarPregunta(Long codPregunta) throws Exception {
		
		Pregunta encontrada = new Pregunta();
		encontrada=preguntaRepository.findById(codPregunta).orElseThrow(()->new Exception("Pregunta no encontrada"));
		return encontrada;
	}

	@Override
	public List<Pregunta> listadoPregunta() {
		// TODO Auto-generated method stub
		
		List<Pregunta> aux = new ArrayList<>();
		List<Pregunta> aux2 = new ArrayList<>();
		
		aux=(List<Pregunta>) preguntaRepository.findAll();
		for(int i=0;i<aux.size();i++) {
			if(aux.get(i).getEstadoPregunta()==true) {
				aux2.add(aux.get(i));
			}
		}
		
		return aux2;
	}
	

}
