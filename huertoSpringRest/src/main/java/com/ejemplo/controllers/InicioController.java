package com.ejemplo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.services.interfaces.HuertosService;

@RestController
@RequestMapping("")
@SessionAttributes("locale")
public class InicioController {
	Logger log = LoggerFactory.getLogger(InicioController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	HuertosService service;	
	
    @GetMapping
	protected List<HuertoItem> inicio(){
		log.debug("inicio");

		List<HuertoItem> huertos= new ArrayList<HuertoItem>();			

		try {			
		
			huertos = service.getListadoHuertos();
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			 gException.gestionExcepciones(se);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			 gException.gestionExcepciones(e);
		}
		return huertos;			
	}
    
    @ModelAttribute("locale")
    public String getModelAttribute() {
    	return "es";
    }
    
  
	
	
	

}
