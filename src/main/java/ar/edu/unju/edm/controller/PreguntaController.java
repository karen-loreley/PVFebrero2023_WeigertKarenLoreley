package ar.edu.unju.edm.controller;



import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.UsuarioPregunta;
import ar.edu.unju.edm.repository.UsuarioPreguntasRepository;
import ar.edu.unju.edm.service.IPreguntaService;
import ar.edu.unju.edm.service.IUsuarioPreguntasService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class PreguntaController {
	private static final Log KAREN=LogFactory.getLog(UsuarioController.class);
	

	
	@Autowired
	IPreguntaService preguntaService;
	
	@Autowired
	IUsuarioService usuarioServicio;
	@Autowired
	IUsuarioPreguntasService usuarioPreguntaServicio;
	@Autowired
	UsuarioPregunta usuarioPregunta;
	@Autowired
	UsuarioPreguntasRepository usuarioPreguntaRepositorio;

  
	@GetMapping("/otraPregunta")
	public String CrearPregunta(Model modelo) {
		KAREN.info("ingresando al metodo: cargarPregunta");
		Pregunta pregunta=new Pregunta();
		modelo.addAttribute("pregunta", pregunta);
		return "crearpregunta";
	}
	
	@PostMapping("/guardarPregunta")
	public String saveQuestion(@ModelAttribute("pregunta")Pregunta preguntaparaguardar) {
		preguntaService.guardarPregunta(preguntaparaguardar);
		return "redirect:/listadoPreguntas";
	
	}
	   
		
	@GetMapping("/listadoPreguntas")
	public String listarPregunta(Model model) {
		model.addAttribute("preguntas1",preguntaService.listadoPregunta(1) );
	    model.addAttribute("preguntas2", preguntaService.listadoPregunta(2));
	    return "listadoPreguntas";
	  }
	
	@RequestMapping("/editPregunta/{codPregunta}")
	public String editarPregunta(Model modelo,@PathVariable Long codPregunta){	
		modelo.addAttribute("pregunta", preguntaService.obtenerPregunta(codPregunta));
		return "crearpreguntas"; 
	}
	
	@PostMapping("/editarPregunta/{id}")
	
		public String saveEditQuestion(@PathVariable Long id, @ModelAttribute("pregunta") Pregunta preguntaparamodificar, Model modelo) {
			preguntaService.actulizarPregunta(preguntaparamodificar);
			return "redirect:/listadoPreguntas";
		}
		
	@GetMapping("/elegirNivel")
	  public ModelAndView ElegirNivel() {
	    Authentication auth = SecurityContextHolder
	        .getContext()
	        .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    Usuario userEnSesion = usuarioServicio.buscarusuario(Long.parseLong(userDetail.getUsername()));
	    ModelAndView vista = new ModelAndView("index");
	    vista.addObject("sesion", userEnSesion);
	    return vista;
	  }

	  @GetMapping("/nivelfacil/{nv}")
	  public ModelAndView Nivel1(@PathVariable(name = "nv") Integer id) {
	    UsuarioPregunta aux = new UsuarioPregunta();
	    Authentication auth = SecurityContextHolder
	        .getContext()
	        .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    Usuario userEnSesion = usuarioServicio.buscarusuario(Long.parseLong(userDetail.getUsername()));
	    if(id==1){
	      usuarioPreguntaServicio.reinicioPuntaje(userEnSesion.getDni(), 1);
	    }
	    userEnSesion.setPuntaje1(0);
	    usuarioServicio.modificarusuario(userEnSesion);
	    aux.setNivel(1);
	    aux.setUsuario(userEnSesion);
	    aux.setPregunta(preguntaService.buscarPregunta(1, id));
	    ModelAndView vista = new ModelAndView("pregunta1");
	    vista.addObject("nro",id);
	    vista.addObject("pregunta", aux.getPregunta());
	    vista.addObject("puntaje", aux);
	    vista.addObject("nivel", true);
	    return vista;
	  }

	  @PostMapping("/subirpuntaje/{nv}")
	  public String subirnivel(@ModelAttribute("puntaje") UsuarioPregunta puntaje, @PathVariable(name = "nv") Integer id) {
	    if(id<=5){
	    usuarioPreguntaServicio.guardarPregunta(puntaje);
	      return "redirect:/nivel1/{nv}";
	    }else{
	      usuarioPreguntaServicio.guardarPregunta(puntaje);
	    return "redirect:/vernota1";
	    }
	  }

	  @GetMapping("/niveldificil/{nv}")
	  public ModelAndView Nivel2(@PathVariable(name = "nv") Integer id) {
	    UsuarioPregunta aux = new UsuarioPregunta();
	    Authentication auth = SecurityContextHolder
	        .getContext()
	        .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    Usuario userEnSesion = usuarioServicio.buscarusuario(Long.parseLong(userDetail.getUsername()));
	    if(id==1){
	      usuarioPreguntaServicio.reinicioPuntaje(userEnSesion.getDni(), 2);
	    }
	    userEnSesion.setPuntaje2(0);
	    usuarioServicio.modificarusuario(userEnSesion);
	    aux.setNivel(2);
	    aux.setUsuario(userEnSesion);
	    aux.setPregunta(preguntaService.buscarPregunta(2, id));
	    ModelAndView vista = new ModelAndView("pregunta1");
	    vista.addObject("nro",id);
	    vista.addObject("pregunta", aux.getPregunta());
	    vista.addObject("puntaje", aux);
	    vista.addObject("nivel", false);
	    return vista;
	  }

	  @PostMapping("/subirpuntaje2/{nv}")
	  public String subirnivel2(@ModelAttribute("puntaje") UsuarioPregunta puntaje, @PathVariable(name = "nv") Integer id) {
	    if(id<=5){
	    usuarioPreguntaServicio.guardarPregunta(puntaje);
	      return "redirect:/nivel2/{nv}";
	    }else{
	      usuarioPreguntaServicio.guardarPregunta(puntaje);
	    return "redirect:/vernota2";
	    }
	  }

	}