package com.ejemplo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.entities.Maceta;
import com.ejemplo.entities.Planta;
import com.ejemplo.services.interfaces.PlantasService;

@Controller
@RequestMapping("/planta")
public class PlantaController {
	Logger log = LoggerFactory.getLogger(PlantaController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	PlantasService service;	
	
    @GetMapping
	protected String verPlantas(Model model){
		log.debug("verPlantas");

		String view = "plantas";			

		try {
			
			List<Planta> plantas = service.getPlantas();
			model.addAttribute("plantas", plantas);	
			
			List<Maceta> macetas = service.getMacetas();
			model.addAttribute("macetas", macetas);
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			view=gException.gestionExcepciones(se, model,view);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			view=gException.gestionExcepciones(e, model,view);
		}

		log.debug("view:"+view);
		
		return view;
		
	}
    


	@PostMapping
	protected String crearPlanta(Model model, @ModelAttribute Planta planta,
			@RequestParam(defaultValue = "/planta") String forward, RedirectAttributes attribute) {
		log.debug("crearPlanta");
		log.debug("Forward:" + forward);

		try {

			service.generatePlanta(planta);

			attribute.addFlashAttribute("message", "planta.new");
			attribute.addFlashAttribute("parametro", planta.getNombre());

		} catch (ServiceException se) {
			log.error("Servicexception");
			forward = gException.gestionExcepciones(se, attribute, forward);

		} catch (Exception e) {
			log.error("Exception", e);
			forward = gException.gestionExcepciones(e, attribute, forward);
		}

		return "redirect:" + forward;
	}

	@GetMapping("/{id}")
	protected String verPlanta(Model model, @PathVariable("id") Long id) {
		log.debug("verPlanta");
		log.debug("id:" + id);

		String view = "planta";

		try {

			Planta planta = service.getPlanta(id);

			model.addAttribute("planta", planta);

		} catch (ServiceException se) {
			log.error("Servicexception");
			view = gException.gestionExcepciones(se, model, view);

		} catch (Exception e) {
			log.error("Exception", e);
			view = gException.gestionExcepciones(e, model, view);
		}

		log.debug("view:" + view);

		return view;
	}

	@PostMapping("/{id}")
	protected String modificarPlanta(Model model, @ModelAttribute Planta planta, RedirectAttributes attribute) {
		log.debug("modificarPlanta");
		String forward = null;

		try {
			forward = "/planta/" + planta.getId();

			service.modifyPlanta(planta);

			attribute.addFlashAttribute("message", "planta.modify");
			attribute.addFlashAttribute("parametro", planta.getNombre());

		} catch (ServiceException se) {
			log.error("Servicexception");
			forward = gException.gestionExcepciones(se, attribute, forward);

		} catch (Exception e) {
			log.error("Exception", e);
			forward = gException.gestionExcepciones(e, attribute, forward);
		}

		return "redirect:" + forward;
	}

	@GetMapping("/r/{id}")
	protected String eliminarPlanta(Model model, @PathVariable("id") Long id,
			@RequestParam(defaultValue = "/planta") String forward, RedirectAttributes attribute) {
		log.debug("eliminarPlanta");
		log.debug("id:" + id);
		log.debug("Forward:" + forward);

		try {

			service.removePlanta(id);

			attribute.addFlashAttribute("message", "planta.delete");

		} catch (ServiceException se) {
			log.error("Servicexception");
			forward = gException.gestionExcepciones(se, attribute, forward);

		} catch (Exception e) {
			log.error("Exception", e);
			forward = gException.gestionExcepciones(e, attribute, forward);
		}

		return "redirect:" + forward;
	}

	

}
