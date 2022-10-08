package com.ejemplo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.services.interfaces.PlantasService;

@RestController
@RequestMapping(value="/error",produces = MediaType.APPLICATION_JSON_VALUE)
public class ErrorController {
	Logger log = LoggerFactory.getLogger(ErrorController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	PlantasService service;	
	
    @GetMapping
    @ResponseBody
	protected String verError(){
		log.debug("verError");
		
		return "error";
		
	}

}
