package com.ejemplo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

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

@WebServlet("")
public class InicioController extends BaseController {
	private static final long serialVersionUID = 1L;

	HuertosService service;

	private static final Logger log = Logger.getLogger(InicioController.class);

	public InicioController() {
		super();
		service = HuertosImpl.init();
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/home.jsp";			

		try {
			if(request.getSession().getAttribute("locale")==null)
			 request.getSession().setAttribute("locale","es");
			
			List<HuertoItem> huertos = service.getListadoHuertos();
			request.setAttribute("huertos", huertos);
			
			
		} catch (ServiceException se) {
			log.error("Servicexception");
			forward=gestionExcepciones(se, request,forward);			
			
		}catch(Exception e) {
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
