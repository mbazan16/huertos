package com.ejemplo.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class IdiomaController  {
Logger log = LoggerFactory.getLogger(HuertoController.class);
	
	@Autowired
	GestionExcepciones gException;

	@RequestMapping("/idioma/{language}")
	protected String cambiarIdioma(Model model,@PathVariable("language") String language,
			HttpSession session) {
		log.debug("cambiarIdioma");
		log.debug("language:"+language);
		
		String forward="/";

		try {	
			
			session.removeAttribute("locale");
			
			if(Locale.ENGLISH.getLanguage().equalsIgnoreCase(language)) {
				log.debug("cmabiamos locale:"+Locale.ENGLISH.getLanguage());
				session.setAttribute("locale", Locale.ENGLISH.getLanguage());
			}else {
				session.setAttribute("locale", "es");
			}	
			
			log.debug("locale despues:"+session.getAttribute("locale"));
			
			
		} catch(Exception e) {
			log.error("Exception",e);
			forward=gException.gestionExcepciones(e, model,forward);
		}

		return "redirect:"+forward;		
		
	}
	

}
