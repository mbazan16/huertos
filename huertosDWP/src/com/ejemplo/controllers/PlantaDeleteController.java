package com.ejemplo.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.services.PlantasImpl;
import com.ejemplo.services.interfaces.PlantasService;

@WebServlet("/planta/r")
public class PlantaDeleteController extends BaseController {
	private static final long serialVersionUID = 1L;

	PlantasService service;

	private static final Logger log = Logger.getLogger(PlantaDeleteController.class);

	public PlantaDeleteController() {
		super();
		service = PlantasImpl.init();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/plantas";

		try {
			Long id = Long.valueOf(request.getParameter("id"));

			if (request.getParameter("forward") != null)
				forward = request.getParameter("forward");

			service.removePlanta(id);

		} catch (ServiceException se) {
			log.error("Servicexception");
			forward = gestionExcepciones(se, request, forward);

		} catch (Exception e) {
			log.error("Exception", e);
			forward = gestionExcepciones(e, request, forward);
		}

		log.debug("forward:" + forward);
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
