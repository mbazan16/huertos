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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.Messages;
import com.ejemplo.common.TamanioMaceta;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.entities.Huerto;
import com.ejemplo.services.interfaces.HuertosService;

@Controller
@RequestMapping("/huerto")
public class HuertoController {
	Logger log = LoggerFactory.getLogger(HuertoController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	HuertosService service;	
	
    @GetMapping
	protected String verHuertos(Model model){
		log.debug("verHuertos");
		log.debug("locale:"+ model.getAttribute("locale"));

		String view = "huertos";			

		try {
						
			List<HuertoItem> huertos = service.getListadoHuertos();
			 model.addAttribute("huertos", huertos);
			
			
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
   	protected String crearHuerto(Model model,@RequestParam String nombre,@RequestParam(defaultValue="/") String forward, RedirectAttributes attribute){
   		log.debug("crearHuerto");
   		log.debug("Nombre:"+nombre);
   		log.debug("Forward:"+forward);			
   		
   		try {
   			   			
   			service.generateHuerto(nombre);
   			
   			attribute.addFlashAttribute("message",Messages.NUEVO_HUERTO);
   			attribute.addFlashAttribute("parametro", nombre);
   			
   			
   		} catch (ServiceException se) {
   			log.error("Servicexception");
   			forward=gException.gestionExcepciones(se, attribute,forward);			
   			
   		}catch(Exception e) {
   			log.error("Exception",e);
   			forward=gException.gestionExcepciones(e, attribute,forward);
   		}		
   		
   		return "redirect:"+forward;		
   	}
    
    @GetMapping("/{id}")
	protected String verHuerto(Model model,@PathVariable("id") Long id){
		log.debug("verHuerto");
		log.debug("id:"+id);

		String view = "huerto";			

		try {			
			 
			 Huerto huerto =service.getHuerto(id);
				
			 model.addAttribute("huerto", huerto);
			 model.addAttribute("macetas", huerto.getMacetas());
				
			 model.addAttribute("tamanios", TamanioMaceta.values());
			 model.addAttribute("materiales", MaterialMaceta.values());
				
			
			
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
    
    @PostMapping("/{id}")
	protected String modificarHuerto(Model model, @ModelAttribute Huerto huerto, RedirectAttributes attribute){
		log.debug("modificarHuerto");
		String forward=null;
		
		try {				 
			forward="/huerto/"+huerto.getId();
			
			service.modifyHuerto(huerto);
			
			attribute.addFlashAttribute("message","huerto.modify");
			attribute.addFlashAttribute("parametro", huerto.getNombre());
			
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			forward=gException.gestionExcepciones(se, attribute,forward);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			forward=gException.gestionExcepciones(e, attribute,forward);
		}		
		
		return "redirect:"+forward;		
	}
    
    @GetMapping("/r/{id}")
	protected String eliminarHuerto(Model model,@PathVariable("id") Long id, @RequestParam(defaultValue="/") String forward,RedirectAttributes attribute){
		log.debug("eliminarHuerto");
		log.debug("id:"+id);
		log.debug("Forward:"+forward);			
		
		try {	
						
			service.removeHuerto(id);
			
			attribute.addFlashAttribute("message","huerto.delete");
			
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			forward=gException.gestionExcepciones(se, attribute,forward);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			forward=gException.gestionExcepciones(e, attribute,forward);
		}		
		
		return "redirect:"+forward;
	}  
	
	

}
