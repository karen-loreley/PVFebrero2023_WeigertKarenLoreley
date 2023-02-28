package ar.edu.unju.edm.controller;


import javax.validation.Valid;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.service.IPreguntaService;

@Controller
public class PreguntaController {
	private static final Log KAREN=LogFactory.getLog(UsuarioController.class);
	

	@Autowired
	Pregunta nueva;
	
	@Autowired
	IPreguntaService preguntaService;
	
	
	@GetMapping("/otraPregunta")
	public ModelAndView addQuestion() {
		KAREN.info("ingresando al metodo: aaaaaaa");
		ModelAndView vista = new ModelAndView("cargarPregunta");
		vista.addObject("pregunta", nueva);
		vista.addObject("editMode", false);
		
		return vista;
	}
	
	@PostMapping(value="/guardarPregunta", consumes = "multipart/form-data")
	public String saveQuestion(@Valid @ModelAttribute("pregunta")Pregunta preguntaparaguardar, BindingResult resultado, @RequestParam("file") MultipartFile file, Model model) {
		System.out.println(resultado.getAllErrors());
		if(resultado.hasErrors()) {
			KAREN.fatal("Error en el metodo GUARDAR PREGUNTA");
			model.addAttribute("editMode", false);
			model.addAttribute("pregunta", preguntaparaguardar);
			
			return "cargarPregunta";
		}
		
		try {
			preguntaService.guardarPregunta(preguntaparaguardar);
		}catch(Exception error) {
			model.addAttribute("formPreguntaErrorMessage", error.getMessage());
			model.addAttribute("pregunta", preguntaparaguardar);
			KAREN.error("No se pudo guardar la pregunta");
			model.addAttribute("editMode", false);
			return "cargarPregunta";
		}
		
		model.addAttribute("formPreguntaErrorMessage", "Pregunta Guardada Correctamente");
		model.addAttribute("pregunta", nueva);
		model.addAttribute("editMode", false);
		return "cargarPregunta";
	}
		
	@GetMapping("/listadoPregunta")
	public ModelAndView listarPregunta() {
		ModelAndView vista = new ModelAndView("listadoPregunta");
		vista.addObject("listaPregunta", preguntaService.listadoPregunta());
		return vista;
	}
	
	@RequestMapping("/editPregunta/{codPregunta}")
	public ModelAndView editarPregunta(Model model,@PathVariable (name="codPregunta") Long codPregunta)throws Exception {	
		Pregunta encontrada = new Pregunta();
		encontrada = preguntaService.buscarPregunta(codPregunta);		
		ModelAndView modelView = new ModelAndView("cargarPregunta");
		modelView.addObject("pregunta", encontrada);
		 KAREN.info("saliendo del metodo: editPregunta "+ encontrada.getCodPregunta());
		modelView.addObject("editMode", true);
		return modelView;
	}
	
	@PostMapping(value= "/editarPregunta", consumes = "multipart/form-data")
	public ModelAndView saveEditQuestion(@Valid @ModelAttribute ("pregunta") Pregunta preguntaparamodificar, BindingResult result,  @RequestParam("file") MultipartFile file) {  
		if(result.hasErrors()) {
			KAREN.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("cargarPregunta");
			vista.addObject("pregunta", preguntaparamodificar);
			vista.addObject("editMode",true);
			return vista;
		}
		try {
			preguntaService.modificarPregunta(preguntaparamodificar);
		}catch(Exception error){
			ModelAndView vista = new ModelAndView("cargarPregunta");
			vista.addObject("formPeliculaErrorMessage", error.getMessage());
			vista.addObject("pregunta", preguntaparamodificar);
			vista.addObject("editMode",true);
			return vista;
		}
			ModelAndView vista = new ModelAndView("listadoPregunta");
			vista.addObject("listaPregunta", preguntaService.listadoPregunta());	
			vista.addObject("formPreguntaErrorMessage","Pregunta modificada Correctamente");
		return vista;
	}
		
	@RequestMapping("/eliminarPregunta/{codPregunta}")
			public String eliminarPregunta(@PathVariable(name="codPregunta") Long codPregunta, Model model) {
			
				try {
					
					preguntaService.eliminarPregunta(codPregunta);
				}catch(Exception error){
					KAREN.error("encontrando: eliminarpregunta");
					model.addAttribute("formPreguntaErrorMessage", error.getMessage());
					return "redirect:/otraPregunta";
				}
				
			    return "redirect:/listadoPregunta";
			}

	}