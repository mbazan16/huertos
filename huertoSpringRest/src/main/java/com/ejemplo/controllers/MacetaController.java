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

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.MacetaItem;
import com.ejemplo.entities.Maceta;
import com.ejemplo.services.interfaces.MacetasService;

@RestController
@RequestMapping("/maceta")
public class MacetaController {
	Logger log = LoggerFactory.getLogger(MacetaController.class);

	@Autowired
	GestionExcepciones gException;

	@Autowired
	MacetasService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	protected List<MacetaItem> verMacetas() {
		log.debug("verMacetas");

		List<MacetaItem> macetas= new ArrayList<MacetaItem>();

		try {

			 macetas = service.getListadoMacetas();
			 
		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}	

		return macetas;

	}

	@PostMapping
	protected void crearMaceta(@RequestBody Maceta maceta) {
		log.debug("crearMaceta");
		
		try {

			service.generateMaceta(maceta);
			

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}
	}

	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	protected Maceta verMaceta(@PathVariable("id") Long id) {
		log.debug("verMaceta");
		log.debug("id:" + id);

		Maceta maceta=null;

		try {

			 maceta = service.getMaceta(id);

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}		

		return maceta;
	}

	@PutMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	protected Maceta modificarMaceta(@RequestBody Maceta maceta) {
		log.debug("modificarMaceta");
		
		try {
			
			service.modifyMaceta(maceta);
			maceta=service.getMaceta(maceta.getId());

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}

		return maceta;
	}

	@DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	protected void eliminarMaceta(@PathVariable("id") Long id) {
		log.debug("eliminarMaceta");
		log.debug("id:" + id);

		try {

			service.removeMaceta(id);

		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}
	}
	
	@GetMapping(value="/tamanios",produces = MediaType.APPLICATION_JSON_VALUE)
	protected TamanioMaceta[] verTamanios() {
		log.debug("verTamanios");

		try {
			
			 return TamanioMaceta.values();

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}	

		return null;

	}
	
	@GetMapping(value="/materiales",produces = MediaType.APPLICATION_JSON_VALUE)
	protected MaterialMaceta[] verMateriales() {
		log.debug("verMateriales");		

		try {
			
			 return MaterialMaceta.values();

		} catch (Exception e) {
			log.error("Exception", e);
			gException.gestionExcepciones(e);
		}	

		return null;

	}

}
