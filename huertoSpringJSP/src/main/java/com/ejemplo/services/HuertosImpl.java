package com.ejemplo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.common.exceptions.ExceptionCode;
import com.ejemplo.common.exceptions.ExceptionMessage;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.common.exceptions.UtilsException;
import com.ejemplo.dao.interfaces.HuertoDAO;
import com.ejemplo.dao.interfaces.MacetaDAO;
import com.ejemplo.dao.interfaces.PlantaDAO;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.entities.Huerto;
import com.ejemplo.services.interfaces.HuertosService;

@Service
public class HuertosImpl implements HuertosService {

	Logger log = LoggerFactory.getLogger(HuertosImpl.class);
    
	@Autowired
	HuertoDAO dao;
	@Autowired
	MacetaDAO macetaDAO;
	@Autowired
	PlantaDAO plantaDAO;

	
	

	@Override
	public List<Huerto> getHuertos() throws ServiceException {
		log.debug("getHuertos");
		List<Huerto> huertos = new ArrayList<Huerto>();
		try {

			huertos = dao.findAll();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

		return huertos;
	}

	@Override
	public List<HuertoItem> getListadoHuertos() throws ServiceException {
		log.debug("getListadoHuertos");
		List<HuertoItem> huertos = new ArrayList<HuertoItem>();
		try {

			List<Huerto> huertosEntities = dao.findAll();
			
			
			for (Huerto huertoAux : huertosEntities) {
				HuertoItem huertoItem = new HuertoItem();
				huertoItem.setIdHuerto(huertoAux.getId());
				huertoItem.setNombreHuerto(huertoAux.getNombre());
				
				Long nMacetas =(long) huertoAux.getMacetas().size();				
				huertoItem.setnMacetas(nMacetas);

				huertoAux.getMacetas().stream()
						  .map(s-> (long)s.getPlantas().size())
						  .forEach(huertoItem::setnPlantas);

				huertos.add(huertoItem);
			}

		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

		return huertos;
	}

	@Override
	public Huerto getHuerto(Long idHuerto) throws ServiceException {
		log.debug("getHuerto");
		Huerto huerto = null;
		try {

			huerto = dao.findById(idHuerto).get();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

		return huerto;
	}

	@Override
	public void generateHuerto(String nombre) throws ServiceException {
		log.debug("generateHuerto");
		try {
			Huerto huerto = new Huerto(nombre);

			log.debug("huerto:"+huerto);
			newHuerto(huerto);

		}catch (ServiceException se) {
			log.error(se.getMensaje());
			throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
		

	}

	@Override
	public void generateHuerto(Huerto huerto) throws ServiceException {
		log.debug("generateHuerto");
		try {

			log.debug("huerto:"+huerto);

			newHuerto(huerto);

		} catch (ServiceException se) {
			log.error(se.getMensaje());
			throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}
	}

	@Override
	public void removeHuerto(Long idHuerto) throws ServiceException {
		log.debug("removeHuerto");

		try {

			validateRemove(idHuerto);
			dao.deleteById(idHuerto);

		} catch (ServiceException se) {
			log.error(se.getMensaje());
			throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

	}
	
	@Override
	public void modifyHuerto(Long idHuerto, String nombre) throws ServiceException {
		log.debug("modifyHuerto");
		try {
			Huerto huerto = new Huerto(idHuerto,nombre);

			setHuerto(huerto);

		} catch (ServiceException se) {
			log.error(se.getMensaje());
			throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

	}

	

	@Override
	public void modifyHuerto(Huerto huerto) throws ServiceException {
		log.debug("modifyHuerto");
		try {

			setHuerto(huerto);

		} catch (ServiceException se) {
			log.error(se.getMensaje());
			throw se;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

	}

	

	

	private void newHuerto(Huerto huerto) throws ServiceException {
		log.debug("crearHuerto");
		log.debug("huerto:"+huerto);
		validateGenerate(huerto);
		log.debug("Crear Huerto:" + huerto.getNombre());
		dao.save(huerto);
	}
	
	private void setHuerto(Huerto huerto) throws ServiceException {
		validateModify(huerto);
		dao.save(huerto);
	}

	private void validateGenerate(Huerto huerto) throws ServiceException {
		log.debug("Validar Creacion");
		log.debug("Validar si Id existe");
		log.debug("huerto:"+huerto);
		if (checkHuertoIdExist(huerto.getId()))
			throw new ServiceException(ExceptionCode.SERV_HUERTO_VAL_ID_EXISTS,
					ExceptionMessage.SERV_HUERTO_VAL_ID_EXISTS);

		validateHuertoName(huerto);

	}

	private void validateModify(Huerto huerto) throws ServiceException {
		log.debug("Validar Modificacion");
		if (!checkHuertoIdExist(huerto.getId()))
			throw new ServiceException(ExceptionCode.SERV_HUERTO_VAL_ID_NO_EXISTS,
					ExceptionMessage.SERV_HUERTO_VAL_ID_NO_EXISTS);
		validateHuertoName(huerto);

	}

	private void validateRemove(Long idHuerto) throws  ServiceException {
		if (!checkHuertoIdExist(idHuerto))
			throw new ServiceException(ExceptionCode.SERV_HUERTO_VAL_ID_NO_EXISTS,
					ExceptionMessage.SERV_HUERTO_VAL_ID_NO_EXISTS);

	}

	private boolean checkHuertoIdExist(long id)  {
		if (dao.findById(id).isPresent())
			return true;
		return false;
	}

	private void validateHuertoName(Huerto huerto) throws ServiceException {
		log.debug("validateHuertoName:" + huerto.getNombre());
		boolean nameExist = dao.findAll().stream().map(Huerto::getNombre) // Mapeamos filtramos solamente los nombres de
																			// los huertos
				.anyMatch(s -> s.equalsIgnoreCase(huerto.getNombre()));// Comparamos con el que vamos a crear
		if (nameExist)
			throw new ServiceException(ExceptionCode.SERV_HUERTO_VAL_NAME_EXISTS,
					ExceptionMessage.SERV_HUERTO_VAL_NAME_EXISTS);
		log.debug("El nombre no existe");
	}

}
