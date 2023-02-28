package ar.edu.unju.edm.service;

import java.util.List;



import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
//la interface dice que hace
@Service
public interface IUsuarioService {
//service guarda usuario
	public void guardarusuario (Usuario usuari);
	public List <Usuario> mostrarusuarios();
	public void eliminarusuario (Integer dni) throws Exception;
	public void modificarusuario(Usuario usuario);
	public Usuario buscarusuario(Integer id) throws Exception;

}
