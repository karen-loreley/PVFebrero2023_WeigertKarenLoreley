package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.until.ListaUsuario;
import ar.edu.unju.edm.repository.UsuarioRepository;

@Service
public class IUsuarioServiceImp implements IUsuarioService{
	//private static final Log KAREN = LogFactory.getLog(UsuarioController.class);
	
	
	@Autowired
	ListaUsuario lista;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void guardarusuario(Usuario usuarioparaguardar) {
		// TODO Auto-generated method stub
		usuarioparaguardar.setEstado(true);
		//lista.getListado().add(usuarioparaguardar);
		String pw=usuarioparaguardar.getContrasena();
		BCryptPasswordEncoder coder = new BCryptPasswordEncoder(4);
		
		usuarioparaguardar.setContrasena(coder.encode(pw));
		usuarioRepository.save(usuarioparaguardar);
		
	}
	
	
	
	@Override
	public List<Usuario> mostrarusuarios() {
		// TODO Auto-generated method stub
	 List<Usuario> auxiliar =new ArrayList<>();
	 List<Usuario> auxiliar2 = new ArrayList<>();
	 
	 /*
		for(int i=0;i<lista.getListado().size();i++) {
			if(lista.getListado().get(i).getEstado()==true){
				
				auxiliar.add(lista.getListado().get(i));
			}
		};
		return auxiliar;
		*/
	auxiliar=(List<Usuario>) usuarioRepository.findAll();
	for(int i = 0 ;i<auxiliar.size();i++) {
		
		if (auxiliar.get(i).getEstado()==true) {
			auxiliar2.add(auxiliar.get(i));
		}
	}	
	return auxiliar2;
}
	
	@Override
	public void eliminarusuario(Integer dni) throws Exception  {
		// TODO Auto-generated method stub
		Usuario auxiliar = new Usuario();
		auxiliar = buscarusuario(dni);
		auxiliar.setEstado(false);
	
		usuarioRepository.save(auxiliar);
  
	}


	@Override
	public void modificarusuario(Usuario usuario) {
		System.out.println("ingresando al metodo modificar usuario"+usuario.getEmail());
		
		usuarioRepository.save(usuario);

		System.out.println("saliendo del metodo modificar usuario");
	}

	
	
	
	@Override
	public Usuario buscarusuario(Integer dni) throws Exception {
		
		Usuario auxiliar = new Usuario();
		
		
		auxiliar=usuarioRepository.findById(dni).orElseThrow(()->new Exception("usuario no encontrado"));
		return auxiliar;
	}
	
	
}
