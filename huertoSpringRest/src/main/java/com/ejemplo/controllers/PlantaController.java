package com.ejemplo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.entities.Planta;
import com.ejemplo.services.interfaces.PlantasService;

@RestController
@RequestMapping("/planta")
public class PlantaController {
	Logger log = LoggerFactory.getLogger(PlantaController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	PlantasService service;	
	
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	protected List<Planta> verPlantas(){
		log.debug("verPlantas");

		List<Planta> plantas = new ArrayList<Planta>();			

		try {
			
			plantas = service.getPlantas();
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			gException.gestionExcepciones(e);
		}

		
		
		return plantas;
		
	}
    


	@PostMapping
	protected void crearPlanta(@RequestBody Planta planta) {
		log.debug("crearPlanta");

		try {
			service.generatePlanta(planta);

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}
	}

	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	protected Planta verPlanta(@PathVariable("id") Long id) {
		log.debug("verPlanta");
		log.debug("id:" + id);

		Planta planta=null;

		try {

			 planta = service.getPlanta(id);

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}		

		return planta;
	}

	@PutMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	protected Planta modificarPlanta(@RequestBody Planta planta) {
		log.debug("modificarPlanta");

		try {			

			service.modifyPlanta(planta);
			planta=service.getPlanta(planta.getId());

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}

		return planta;
	}

	@DeleteMapping("/{id}")
	protected void eliminarPlanta( @PathVariable("id") Long id) {
		log.debug("eliminarPlanta");
		log.debug("id:" + id);

		try {

			service.removePlanta(id);

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}
		
	}

}
