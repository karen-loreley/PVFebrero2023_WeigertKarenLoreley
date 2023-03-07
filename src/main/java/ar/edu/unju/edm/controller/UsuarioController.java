package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioPreguntasService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {
	
	private static final Log KAREN=LogFactory.getLog(UsuarioController.class);
	@Autowired
	Usuario nuevoUsuario;

	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	IUsuarioPreguntasService usuarioPreguntaServicio;
	
	@Autowired
	IUsuarioService service;

@GetMapping("/nuevoUsuario")//entra
public ModelAndView addUser() {
	KAREN.info("ingresando al metodo: bbbbbbbbbbb");
	ModelAndView modelView =new ModelAndView("cargarusuario");
	//vista.addObject("nuevoUsuario");
	modelView.addObject("usuario", nuevoUsuario);
	modelView.addObject("editMode", false);
	
	return modelView;	
}

@PostMapping("/guardarusuario")//se recibe
public String saveUser(@Valid  @ModelAttribute ("usuario")Usuario usuarioparaguardar, BindingResult resultado, ModelMap model) {
	//KAREN.info("ingresando al metodo guardar usuario: "+usuarioparaguardar.getFechaNac());
	System.out.println(resultado.getAllErrors());
	if(resultado.hasErrors()) {
		KAREN.fatal("Error de validacion");
		model.addAttribute("usuario",usuarioparaguardar);
		model.addAttribute("editMode", false);
		return "cargarusuario";
	}
	try {//controla si algo se ejecuta bien- colocarlo cuando creemos que algo pueda fallar
		usuarioService.guardarusuario(usuarioparaguardar);
	}catch(Exception error){//si no sale
		model.addAttribute("formUsuarioErrorMessage", error.getMessage());
		model.addAttribute("usuario", usuarioparaguardar);
		model.addAttribute("editMode", false);
		KAREN.error("saliendo del metodo: guardar usuario");
	    return "cargarusuario";
	}
	
	//KAREN.error("tamaño del listado: " + lista.getListado().size());
	model.addAttribute("formUsuarioErrorMessage", "Usuario guardado correctamente");
	model.addAttribute("usuario", nuevoUsuario);
	model.addAttribute("editMode", false);
	return "cargarusuario";
}


@GetMapping("/listadoUsuario")//entra
public ModelAndView showUser() {
	ModelAndView vista = new ModelAndView("listadoUsuario");
	vista.addObject("listaUsuario", usuarioService.mostrarUsuarios());
	return vista;	
}

@RequestMapping("/eliminarusuario/{id}")//se recibe
public String eliminar(@PathVariable Long id, Model model) {
	try {
		usuarioService.eliminarusuario(id);
	}catch(Exception error) {
		KAREN.error("encontrando: en eliminar usuario");
	   model.addAttribute("formUsuarioErrorMessage", error.getMessage());
	  return "redirect:/listadoUsuario";
	}
	
	return "redirect:/listadoUsuario";
	
	}


@GetMapping("/editarusuario/{dni}")//se recibe
public ModelAndView ObtenerFormularioEditarUsuario(Model model, @PathVariable (name="dni") Long dni) throws Exception {
	Usuario usuarioEncontrado =new Usuario();
	usuarioEncontrado = usuarioService.buscarusuario(dni);	
	ModelAndView modelView = new ModelAndView("cargarusuario");
	modelView.addObject("usuario", usuarioEncontrado);
	KAREN.error("saliendo del metodo: editar"+ usuarioEncontrado.getDni());
	modelView.addObject("editMode",true);		
	return modelView;
}

@PostMapping("/editarusuario")
public ModelAndView postEditarUsuario(@ModelAttribute("usuario") Usuario usuariomodificar,BindingResult result) {		
			
		
	if(result.hasErrors()){
		KAREN.fatal("Error de validacion");
		ModelAndView vista = new ModelAndView("cargarusuario");
		vista.addObject("usuario", usuariomodificar);
		vista.addObject("editMode",true);
		return vista;
	}
	try{
		usuarioService.modificarusuario(usuariomodificar);
	}catch(Exception error){
		ModelAndView vista = new ModelAndView("cargarusuario");
		vista.addObject("formUsuarioErrorMessage", error.getMessage());
		vista.addObject("usuario", usuariomodificar);
		vista.addObject("editMode",true);
		KAREN.error("Usted esta saliendo del metodo: editar usuario");
		return vista;
	}
	
	 KAREN.info("DNI de usuarioparamod "+ usuariomodificar.getDni());
	 KAREN.info("Nombre de usuarioparamod "+ usuariomodificar.getNombre());
	ModelAndView vista1 = new ModelAndView("listadoUsuario");		
	vista1.addObject("listaUsuario", usuarioService.mostrarUsuarios());		
	vista1.addObject("formUsuarioErrorMessage","El Usuario fue modificado Correctamente");
	
	return vista1;
}

@GetMapping("/vernota1")
public String vernota(Model modelo) {
	Authentication auth = SecurityContextHolder
    .getContext()
    .getAuthentication();
UserDetails userDetail = (UserDetails) auth.getPrincipal();
	modelo.addAttribute("puntaje", usuarioPreguntaServicio.SumarPuntaje(usuarioPreguntaServicio.buscarUsuario(Long.parseLong(userDetail.getUsername()), 1)));
	return "resultados"; 
}

@GetMapping("/vernota2")
public String vernota2(Model modelo) {
	Authentication auth = SecurityContextHolder
    .getContext()
    .getAuthentication();
UserDetails userDetail = (UserDetails) auth.getPrincipal();
	modelo.addAttribute("puntaje", usuarioPreguntaServicio.SumarPuntaje(usuarioPreguntaServicio.buscarUsuario(Long.parseLong(userDetail.getUsername()), 2)));
	return "resultados"; 
}

@GetMapping("/vernota/{id}")
public String vernotadocente(Model modelo,@PathVariable Long id) {
	Usuario aux=new Usuario();
	aux=service.buscarusuario(id);
	modelo.addAttribute("puntaje", usuarioPreguntaServicio.SumarPuntaje(usuarioPreguntaServicio.buscarUsuario(aux.getDni(), 1)));
	return "resultado_docente"; 
}

@GetMapping("/vernota2/{id}")
public String vernota2docente(Model modelo,@PathVariable Long id) {
	Usuario aux=new Usuario();
	aux=service.buscarusuario(id);
	modelo.addAttribute("puntaje", usuarioPreguntaServicio.SumarPuntaje(usuarioPreguntaServicio.buscarUsuario(aux.getDni(), 2)));
	return "resultado_docente"; 
}


}
