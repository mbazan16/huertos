package com.ejemplo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.services.HuertosImpl;
import com.ejemplo.services.interfaces.HuertosService;

@WebServlet("/idioma")
public class IdiomaController extends BaseController {
	private static final long serialVersionUID = 1L;

	
	private static final Logger log = Logger.getLogger(IdiomaController.class);

	public IdiomaController() {
		super();
		
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/";			

		try {
			String locale=request.getParameter("locale");
			
			log.debug("locale:"+locale);
			log.debug("Locale.ENGLISH.getLanguage():"+Locale.ENGLISH.getLanguage());
			
			if(Locale.ENGLISH.getLanguage().equalsIgnoreCase(locale)) {
				    locale=Locale.ENGLISH.getLanguage();
			}else {
				locale= "es";
			}
			
			if(request.getSession().getAttribute("locale")!=null) 
				request.getSession().removeAttribute(locale);
			
			log.debug("Introducimos en sesion:"+locale);
			request.getSession().setAttribute("locale", locale);
			
		} catch(Exception e) {
			log.error("Exception",e);
			forward=gestionExcepciones(e, request,forward);
		}

		log.debug("forward:"+forward);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forward);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPost");
		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPut");
		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doDelete");
		doGet(request, response);
	}

	

}
