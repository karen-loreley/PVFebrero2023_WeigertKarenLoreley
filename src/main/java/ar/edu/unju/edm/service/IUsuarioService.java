package ar.edu.unju.edm.service;

import java.util.List;



import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
//la interface dice que hace
@Service
public interface IUsuarioService {
//service guarda usuario
	public void guardarusuario (Usuario usuari);
	public List <Usuario> mostrarUsuarios();
	public void eliminarusuario (Long dni);
	public Usuario modificarusuario(Usuario usuario);
	public Usuario buscarusuario(Long id);

}
