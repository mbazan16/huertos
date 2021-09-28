package com.ejemplo.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.common.exceptions.UtilsException;

@SuppressWarnings("serial")
public class BaseController extends HttpServlet {
	
	protected String gestionExcepciones(Exception e, HttpServletRequest request,String forward) {
		 String message=null;  
		
		  if(e instanceof ServiceException) {
			  message = UtilsException.classify((ServiceException)e);			 
		  }
		  if(message==null) {
			  message="error.general";
			  forward="/error.jsp";
		  }
		  request.setAttribute("error",message);
		  return forward;
		  
		
		
	}

}
