package com.ejemplo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.common.exceptions.UtilsException;
import com.ejemplo.dao.interfaces.HuertoDAO;
import com.ejemplo.dao.interfaces.MacetaDAO;
import com.ejemplo.dao.interfaces.PlantaDAO;
import com.ejemplo.entities.Maceta;
import com.ejemplo.entities.Planta;
import com.ejemplo.services.interfaces.PlantasService;

@Service
public class PlantasImpl implements PlantasService {

       Logger log = LoggerFactory.getLogger(PlantasImpl.class);
    
	@Autowired
	HuertoDAO huertodao;
	@Autowired
	MacetaDAO macetadao;
	@Autowired
	PlantaDAO dao;


	@Override
	public List<Planta> getPlantas() throws ServiceException {
		log.debug("getPlantas");
		List<Planta> plantas = new ArrayList<Planta>();
		try {
			plantas = dao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
		return plantas;
	}
	
	@Override
	public Planta getPlanta(Long id) throws ServiceException {
		log.debug("getPlanta");
		Planta planta=null;
		
		try {
			planta = dao.findById(id).get();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
		return planta;
	}

	@Override
	public void generatePlanta(Long idMaceta, String nombre) throws ServiceException {
		log.debug("generatePlanta");
		try {
			Planta planta = new Planta();
			planta.setNombre(nombre);
			if (idMaceta != null) {
				Maceta maceta = macetadao.findById(idMaceta).get();
				planta.setMaceta(maceta);
			}
			dao.save(planta);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
	}

	@Override
	public void generatePlanta(Planta planta) throws ServiceException {
		log.debug("generatePlanta");
		try {
			
			if (planta.getMaceta() != null && planta.getMaceta().getId()!= 0) {
				Maceta maceta = macetadao.findById(planta.getMaceta().getId()).get();
				planta.setMaceta(maceta);
			}
			dao.save(planta);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
		
	}

	@Override
	public void modifyPlanta(Long idPlanta, Long idMaceta, String nombre) throws ServiceException {
		log.debug("modifyPlanta");
		try {
			Planta planta = new Planta();
			planta.setId(idPlanta);
			planta.setNombre(nombre);
			Maceta maceta = macetadao.findById(idMaceta).get();
			planta.setMaceta(maceta);
			dao.save(planta);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
	}

	@Override
	public void modifyPlanta(Planta planta) throws ServiceException {
		log.debug("modifyPlanta");
		try {
			
			Maceta maceta = macetadao.findById(planta.getMaceta().getId()).get();
			planta.setMaceta(maceta);
			dao.save(planta);

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
		}catch (Exception e) {
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
			
			dao.deleteById(idPlanta);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
	}

}
