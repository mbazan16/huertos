package com.ejemplo.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ejemplo.common.exceptions.DAOException;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.common.exceptions.UtilsException;
import com.ejemplo.dao.HuertoDAO;
import com.ejemplo.dao.MacetaDAO;
import com.ejemplo.dao.PlantaDAO;
import com.ejemplo.dao.interfaces.IDAO;
import com.ejemplo.entities.Huerto;
import com.ejemplo.entities.Maceta;
import com.ejemplo.entities.Planta;
import com.ejemplo.services.interfaces.PlantasService;

public class PlantasImpl implements PlantasService {

	private static final Logger log = Logger.getLogger(PlantasImpl.class);

	IDAO<Huerto> huertodao;
	IDAO<Maceta> macetadao;
	IDAO<Planta> dao;

	private PlantasImpl() {
	}

	public static PlantasImpl init() {
		log.debug("init");
		PlantasImpl servicio = new PlantasImpl();
		servicio.huertodao = new HuertoDAO();
		servicio.macetadao = new MacetaDAO();
		servicio.dao = new PlantaDAO();
		return servicio;
	}

	@Override
	public List<Planta> getPlantas() throws ServiceException {
		log.debug("getPlantas");
		List<Planta> plantas = new ArrayList<Planta>();
		try {
			plantas = dao.findAll();
		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
		return plantas;
	}

	@Override
	public void generatePlanta(Long idMaceta, String nombre) throws ServiceException {
		log.debug("generatePlanta");
		try {
			Planta planta = new Planta();
			planta.setNombre(nombre);
			if (idMaceta != null) {
				Maceta maceta = macetadao.find(idMaceta);
				planta.setMaceta(maceta);
			}
			dao.crear(planta);

		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
	}

	@Override
	public void modifyPlanta(Long idPlanta, Long idMaceta, String nombre) throws ServiceException {
		log.debug("generatePlanta");
		try {
			Planta planta = new Planta();
			planta.setId(idPlanta);
			planta.setNombre(nombre);
			Maceta maceta = macetadao.find(idMaceta);
			planta.setMaceta(maceta);
			dao.grabar(planta);

		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
	}

	@Override
	public List<Maceta> getMacetas() throws ServiceException {
		log.debug("getMacetas");
		List<Maceta> macetas = new ArrayList<Maceta>();
		try {
			macetas = macetadao.findAll();
		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
		return macetas;
	}

	@Override
	public void removePlanta(Long idPlanta) throws ServiceException {
		log.debug("removePlanta");
		
		try {
			
			dao.delete(idPlanta);
			
		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
	}

}
