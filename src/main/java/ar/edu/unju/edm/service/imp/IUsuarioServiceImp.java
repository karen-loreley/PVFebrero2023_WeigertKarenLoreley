package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.repository.UsuarioRepository;

@Service
public class IUsuarioServiceImp implements IUsuarioService{
	
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void guardarusuario(Usuario usuarioparaguardar) {
		String pw=usuarioparaguardar.getContrasena();
		BCryptPasswordEncoder coder = new BCryptPasswordEncoder(4);
		
		usuarioparaguardar.setContrasena(coder.encode(pw));
		usuarioparaguardar.setEstado(true);
		usuarioRepository.save(usuarioparaguardar);
	}
	
	
	@Override
	public List<Usuario> mostrarUsuarios() {
		// TODO Auto-generated method stub
	 List<Usuario> auxiliar =new ArrayList<>();
	 List<Usuario> auxiliar2 = new ArrayList<>();
	
	auxiliar=(List<Usuario>) usuarioRepository.findAll();
	for(int i = 0 ;i<auxiliar.size();i++) {
		
		if (auxiliar.get(i).getEstado()==true) {
			auxiliar2.add(auxiliar.get(i));
		}
	}	
	return auxiliar2;
}
	
	@Override
	public void eliminarusuario(Long dni) {
		// TODO Auto-generated method stub
		Usuario auxiliar = new Usuario();
		auxiliar = buscarusuario(dni);
		auxiliar.setEstado(false);
	
		usuarioRepository.save(auxiliar);
  
	}

	@Override
	public Usuario modificarusuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscarusuario(Long dni) {
		
		
		return usuarioRepository.findById(dni).get();
	}
	
	
}
