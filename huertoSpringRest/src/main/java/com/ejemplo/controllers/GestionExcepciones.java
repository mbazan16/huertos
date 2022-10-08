package com.ejemplo.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.common.exceptions.UtilsException;

@Component
public class GestionExcepciones  {
	
	protected String gestionExcepciones(Exception e) {
		 String message=null; 
		 String forward=null;
		
		  if(e instanceof ServiceException) {
			  message = UtilsException.classify((ServiceException)e);			 
		  }
		  if(message==null) {
			  message="error.general";
			  forward="/error";
		  }
		  return forward;
		
	}
	
	protected String gestionExcepciones(Exception e, RedirectAttributes atributes,String forward) {
		 String message=null;  
		
		  if(e instanceof ServiceException) {
			  message = UtilsException.classify((ServiceException)e);			 
		  }
		  if(message==null) {
			  message="error.general";
			  forward="/error";
		  }
		  atributes.addFlashAttribute("error",message);
		  return forward;
		
	}

}
