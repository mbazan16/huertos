package com.ejemplo.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.services.HuertosImpl;
import com.ejemplo.services.MacetasImpl;
import com.ejemplo.services.interfaces.MacetasService;

@WebServlet("/maceta/m")
public class MacetaModifyController extends BaseController {
	private static final long serialVersionUID = 1L;

	MacetasService service;

	private static final Logger log = Logger.getLogger(MacetaModifyController.class);

	public MacetaModifyController() {
		super();
		service = MacetasImpl.init();
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/maceta/v";			

		try {
			if(request.getParameter("forward")!=null) forward=request.getParameter("forward");
			
			Long idHuerto = Long.valueOf(request.getParameter("idHuerto"));
			Long id = Long.valueOf(request.getParameter("id"));
			String codigo = request.getParameter("codigo");
			TamanioMaceta tamanio = Enum.valueOf (TamanioMaceta.class, request.getParameter ("tamanio"));
			MaterialMaceta material = Enum.valueOf (MaterialMaceta.class, request.getParameter ("material"));
			
			service.modifyMaceta(id, idHuerto, codigo, tamanio, material);
			
			
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
