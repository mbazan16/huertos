package com.ejemplo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.services.interfaces.HuertosService;

@Controller
@RequestMapping("")
public class InicioController {
	Logger log = LoggerFactory.getLogger(InicioController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	HuertosService service;	
	
    @GetMapping
	protected String inicio(Model model,HttpSession session){
		log.debug("inicio");

		String view = "home";			

		try {			
		
			 List<HuertoItem> huertos = service.getListadoHuertos();
			 model.addAttribute("huertos", huertos);	 
			 
			 
			
			 if(session.getAttribute("locale")==null)
				 session.setAttribute("locale", "es");
			
			
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

}
