package com.ejemplo.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.MacetaItem;
import com.ejemplo.services.MacetasImpl;
import com.ejemplo.services.interfaces.MacetasService;

@WebServlet("/macetas")
public class MacetasController extends BaseController {
	private static final long serialVersionUID = 1L;

	MacetasService service;

	private static final Logger log = Logger.getLogger(MacetasController.class);

	public MacetasController() {
		super();
		service = MacetasImpl.init();
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/macetas.jsp";			

		try {
			List<MacetaItem> macetas = service.getListadoMacetas();
			request.setAttribute("macetas", macetas);
			
			HashMap<Long,String> huertos = service.getHuertos();
			request.setAttribute("huertos", huertos);
			
			request.setAttribute("tamanios", TamanioMaceta.values());
			request.setAttribute("materiales", MaterialMaceta.values());
			
			
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
