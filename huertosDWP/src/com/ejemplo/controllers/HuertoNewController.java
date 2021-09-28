package com.ejemplo.controllers;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ejemplo.common.Messages;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.entities.Huerto;
import com.ejemplo.services.HuertosImpl;
import com.ejemplo.services.interfaces.HuertosService;

@WebServlet("/huerto/n")
public class HuertoNewController extends BaseController {
	private static final long serialVersionUID = 1L;

	HuertosService service;

	private static final Logger log = Logger.getLogger(HuertoNewController.class);

	public HuertoNewController() {
		super();
		service = HuertosImpl.init();
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/";			

		try {
			if(request.getParameter("forward")!=null) forward=request.getParameter("forward");
			String name= request.getParameter("nombre");
			log.debug("Nombre:"+name);
			service.generateHuerto(name);
			
			//OPCION 1.
			//Leemos el archivo de propiedades desde aqui y mandamos directamente el mensaje a mostrar
			ResourceBundle mensajes = ResourceBundle.getBundle("messages",Locale.ENGLISH);
			mensajes.getString(Messages.NUEVO_HUERTO);			
			
			request.setAttribute("mensajeControlador", mensajes.getString(Messages.NUEVO_HUERTO));
			
			//OPCION 2
			//Se va a leer el archivo de propiedades desde el jsp (con jstl fmt)
			//y fmt buscará el mensaje en el propiedades y lo mostrará
			request.setAttribute("message",Messages.NUEVO_HUERTO);
			request.setAttribute("parametro", name);
			
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			forward=gestionExcepciones(se, request,forward);			
			
		}catch(Exception e) {
			log.error("Exception",e);
			forward=gestionExcepciones(e, request,forward);
		}

		log.debug("forward::"+forward);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPost");
		doGet(request,response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doPut");
		doGet(request,response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doDelete");
		doGet(request,response);
	}
	

}
