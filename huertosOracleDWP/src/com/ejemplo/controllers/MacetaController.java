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
import com.ejemplo.entities.Maceta;
import com.ejemplo.services.MacetasImpl;
import com.ejemplo.services.interfaces.MacetasService;

@WebServlet("/maceta/v")
public class MacetaController extends BaseController {
	private static final long serialVersionUID = 1L;

	MacetasService service;

	private static final Logger log = Logger.getLogger(MacetaController.class);

	public MacetaController() {
		super();
		service = MacetasImpl.init();
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet");

		String forward = "/maceta.jsp";			

		try {
			Long id=null;
			try {
				//Si viene directo
			  id= Long.valueOf(request.getParameter("id"));
			}catch(Exception e) {
				//Si viene redireccionado desde otro controlador
				if(request.getParameter("idMaceta")!= null) id = Long.valueOf(request.getParameter("idMaceta"));
				if(id==null) id= (Long)request.getAttribute("idMaceta");				
			}
			log.debug("id:"+id);
			
			Maceta maceta =service.getMaceta(id);
			
			request.setAttribute("maceta", maceta);
			request.setAttribute("plantas", maceta.getPlantas());
			
			request.setAttribute("tamanios", TamanioMaceta.values());
			request.setAttribute("materiales", MaterialMaceta.values());
			
				
			
			
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
