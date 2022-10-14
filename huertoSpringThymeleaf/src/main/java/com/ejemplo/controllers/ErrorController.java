package com.ejemplo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejemplo.services.interfaces.PlantasService;

@Controller
@RequestMapping("/error")
public class ErrorController {
	Logger log = LoggerFactory.getLogger(ErrorController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	PlantasService service;	
	
    @GetMapping
	protected String verError(Model model){
		log.debug("verError");
		
		return "error";
		
	}

}
