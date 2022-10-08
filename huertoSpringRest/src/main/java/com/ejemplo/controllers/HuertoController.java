package com.ejemplo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.entities.Huerto;
import com.ejemplo.services.interfaces.HuertosService;

@RestController
@RequestMapping("/huerto")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class HuertoController {
	Logger log = LoggerFactory.getLogger(HuertoController.class);
	
	@Autowired
	GestionExcepciones gException;

	@Autowired
	HuertosService service;	
	
	 @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
		protected List<HuertoItem> verHuertos(){
			log.debug("verHuertos");

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
    
    @PostMapping
   	protected void crearHuerto(@RequestParam String nombre){
   		log.debug("crearHuerto");
   		log.debug("Nombre:"+nombre);		
   		
   		try {
   			   			
   			service.generateHuerto(nombre);
   			
   		} catch (ServiceException se) {
   			log.error("Servicexception");
   			gException.gestionExcepciones(se);			
   			
   		}catch(Exception e) {
   			log.error("Exception",e);
   			gException.gestionExcepciones(e);
   		}		
   		
   		
   	}
   
    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_XML_VALUE)
	protected Huerto verHuerto(@PathVariable("id") Long id){
		log.debug("verHuerto");
		log.debug("id:"+id);

		Huerto huerto=null;		

		try {			
			 
			 huerto =service.getHuerto(id);
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			gException.gestionExcepciones(e);
		}	
		
		return huerto;
	}
    
    @PutMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	protected Huerto modificarHuerto(@RequestBody Huerto huerto){
		log.debug("modificarHuerto");		
		
		try {	
			service.modifyHuerto(huerto);
			huerto= service.getHuerto(huerto.getId());
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			gException.gestionExcepciones(e);
		}		
		
		return huerto;		
	}
    
    @DeleteMapping("/{id}")
	protected void eliminarHuerto(@PathVariable("id") Long id){
		log.debug("eliminarHuerto");
		log.debug("id:"+id);		
		
		try {
						
			service.removeHuerto(id);			
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			gException.gestionExcepciones(se);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			gException.gestionExcepciones(e);
		}
	}  
	
	

}
