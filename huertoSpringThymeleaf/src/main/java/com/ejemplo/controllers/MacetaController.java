package com.ejemplo.controllers;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.MacetaItem;
import com.ejemplo.entities.Maceta;
import com.ejemplo.services.interfaces.MacetasService;

@Controller
@RequestMapping("/maceta")
public class MacetaController {
	Logger log = LoggerFactory.getLogger(MacetaController.class);

	@Autowired
	GestionExcepciones gException;

	@Autowired
	MacetasService service;

	@GetMapping
	protected String verMacetas(Model model) {
		log.debug("verMacetas");

		String view = "macetas";

		try {

			List<MacetaItem> macetas = service.getListadoMacetas();
			model.addAttribute("macetas", macetas);

			HashMap<Long, String> huertos = service.getHuertos();
			model.addAttribute("huertos", huertos);

			model.addAttribute("tamanios", TamanioMaceta.values());
			model.addAttribute("materiales", MaterialMaceta.values());

		} catch (ServiceException se) {
			log.error("Servicexception");
			view = gException.gestionExcepciones(se, model, view);

		} catch (Exception e) {
			log.error("Exception", e);
			view = gException.gestionExcepciones(e, model, view);
		}

		log.debug("view:" + view);

		return view;

	}

	@PostMapping
	protected String crearMaceta(Model model, @ModelAttribute Maceta maceta,
			@RequestParam(defaultValue = "/maceta") String forward, RedirectAttributes attribute) {
		log.debug("crearMaceta");
		log.debug("Forward:" + forward);

		try {

			service.generateMaceta(maceta);

			attribute.addFlashAttribute("message", "maceta.new");
			attribute.addFlashAttribute("parametro", maceta.getCodigo());

		} catch (ServiceException se) {
			log.error("Servicexception");
			forward = gException.gestionExcepciones(se, attribute, forward);

		} catch (Exception e) {
			log.error("Exception", e);
			forward = gException.gestionExcepciones(e, attribute, forward);
		}

		return "redirect:" + forward;
	}

	@GetMapping("/{id}")
	protected String verMaceta(Model model, @PathVariable("id") Long id) {
		log.debug("verMaceta");
		log.debug("id:" + id);

		String view = "maceta";

		try {

			Maceta maceta = service.getMaceta(id);

			model.addAttribute("maceta", maceta);
			model.addAttribute("plantas", maceta.getPlantas());

			model.addAttribute("tamanios", TamanioMaceta.values());
			model.addAttribute("materiales", MaterialMaceta.values());

		} catch (ServiceException se) {
			log.error("Servicexception");
			view = gException.gestionExcepciones(se, model, view);

		} catch (Exception e) {
			log.error("Exception", e);
			view = gException.gestionExcepciones(e, model, view);
		}

		log.debug("view:" + view);

		return view;
	}

	@PostMapping("/{id}")
	protected String modificarMaceta(Model model, @ModelAttribute Maceta maceta, RedirectAttributes attribute) {
		log.debug("modificarMaceta");
		String forward = null;

		try {
			forward = "/maceta/" + maceta.getId();

			service.modifyMaceta(maceta);

			attribute.addFlashAttribute("message", "maceta.modify");
			attribute.addFlashAttribute("parametro", maceta.getCodigo());

		} catch (ServiceException se) {
			log.error("Servicexception");
			forward = gException.gestionExcepciones(se, attribute, forward);

		} catch (Exception e) {
			log.error("Exception", e);
			forward = gException.gestionExcepciones(e, attribute, forward);
		}

		return "redirect:" + forward;
	}

	@GetMapping("/r/{id}")
	protected String eliminarMaceta(Model model, @PathVariable("id") Long id,
			@RequestParam(defaultValue = "/maceta") String forward, RedirectAttributes attribute) {
		log.debug("eliminarMaceta");
		log.debug("id:" + id);
		log.debug("Forward:" + forward);

		try {

			service.removeMaceta(id);

			attribute.addFlashAttribute("message", "maceta.delete");

		} catch (ServiceException se) {
			log.error("Servicexception");
			forward = gException.gestionExcepciones(se, attribute, forward);

		} catch (Exception e) {
			log.error("Exception", e);
			forward = gException.gestionExcepciones(e, attribute, forward);
		}

		return "redirect:" + forward;
	}

}
