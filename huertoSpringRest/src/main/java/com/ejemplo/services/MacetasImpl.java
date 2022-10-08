package com.ejemplo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;
import com.ejemplo.common.exceptions.ExceptionCode;
import com.ejemplo.common.exceptions.ExceptionMessage;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.common.exceptions.UtilsException;
import com.ejemplo.dao.interfaces.HuertoDAO;
import com.ejemplo.dao.interfaces.MacetaDAO;
import com.ejemplo.dao.interfaces.PlantaDAO;
import com.ejemplo.dto.MacetaItem;
import com.ejemplo.entities.Huerto;
import com.ejemplo.entities.Maceta;
import com.ejemplo.entities.Planta;
import com.ejemplo.services.interfaces.MacetasService;

@Service
public class MacetasImpl implements MacetasService{

   Logger log = LoggerFactory.getLogger(MacetasImpl.class);
    
	@Autowired
	HuertoDAO huertodao;
	@Autowired
	MacetaDAO dao;
	@Autowired
	PlantaDAO plantaDAO;

	@Override
	public List<MacetaItem> getListadoMacetas() throws ServiceException{
		log.debug("getListadoMacetas");
		List<MacetaItem> macetas = new ArrayList<MacetaItem>();
		try {

			List<Maceta> macetasEntities = dao.findAll();
			for (Maceta macetaAux : macetasEntities) {
				MacetaItem macetaItem = new MacetaItem();
				macetaItem.setId(macetaAux.getId());
				macetaItem.setCodigo(macetaAux.getCodigo());
				macetaItem.setTamanio(macetaAux.getTamanio());
				macetaItem.setMaterial(macetaAux.getMaterial());
				macetaItem.setNombreHuerto(macetaAux.getHuerto().getNombre());

				List<Planta> plantas = plantaDAO.findAll();
				Long nPlantas = plantas.stream()
						.filter(s -> s.getMaceta() != null)
						.filter(s -> s.getMaceta().getId() == macetaItem.getId()).count();
				macetaItem.setnPlantas(nPlantas);

				macetas.add(macetaItem);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

		return macetas;
		
	}
	@Override
	public Maceta getMaceta(Long idMaceta) throws ServiceException {
		log.debug("getMaceta");
		Maceta maceta = null;
		try {

			maceta = dao.findById(idMaceta).get();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}

		return maceta;
	}
	
	@Override
	public void generateMaceta(Long idHuerto, String codigo, TamanioMaceta tamanio, MaterialMaceta material) throws ServiceException {
		log.debug("generateMaceta");
		try {
			Maceta maceta = new Maceta();
			maceta.setCodigo(codigo);
			maceta.setTamanio(tamanio);
			maceta.setMaterial(material);
			
			Huerto huerto = huertodao.findById(idHuerto).get();			
			maceta.setHuerto(huerto);
			
			newMaceta(maceta);

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
	public void generateMaceta(Maceta maceta) throws ServiceException {
		log.debug("generateMaceta");
		try {
			
			
			Huerto huerto = huertodao.findById(maceta.getHuerto().getId()).get();			
			maceta.setHuerto(huerto);
			
			newMaceta(maceta);

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
	public void modifyMaceta(Long idMaceta,Long idHuerto, String codigo, TamanioMaceta tamanio, MaterialMaceta material) throws ServiceException {
		log.debug("modifyMaceta");
		try {
			Maceta maceta = new Maceta();
			maceta.setId(idMaceta);
			maceta.setCodigo(codigo);
			maceta.setTamanio(tamanio);
			maceta.setMaterial(material);
			
			Huerto huerto = huertodao.findById(idHuerto).get();			
			maceta.setHuerto(huerto);
			
			setMaceta(maceta);

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
	public void modifyMaceta(Maceta maceta) throws ServiceException {
		log.debug("modifyMaceta");
		try {
						
			Huerto huerto = huertodao.findById(maceta.getHuerto().getId()).get();			
			maceta.setHuerto(huerto);
			
			setMaceta(maceta);

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
	public void removeMaceta(Long idMaceta) throws ServiceException {
		log.debug("removeMaceta");
		try {
			
			
			validateRemove(idMaceta);
			dao.deleteById(idMaceta);

		}  catch (ServiceException se) {
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
	public HashMap<Long, String> getHuertos() throws ServiceException {
		log.debug("getHuertos");
		HashMap<Long,String> huertos = new HashMap<Long,String>();
		
		try {
			List<Huerto> huertosbbdd = huertodao.findAll();
			
			huertosbbdd.forEach(h -> huertos.put(h.getId(), h.getNombre()));

		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			ServiceException se = UtilsException.classify(e);
			if (se != null)
				throw se;
		}	
		return huertos;

	}

	private void newMaceta(Maceta maceta) throws ServiceException {
		log.debug("newMaceta");
		validateGenerate(maceta);
		dao.save(maceta);
	}
	
	private void setMaceta(Maceta maceta) throws ServiceException {
		log.debug("setMaceta");
		validateModify(maceta);
		dao.save(maceta);
	}
	
	private void validateGenerate(Maceta maceta) throws ServiceException {
		log.debug("Validar Creacion");
		log.debug("Validar si Id existe");
		if (checkMacetaIdExist(maceta.getId()))
			throw new ServiceException(ExceptionCode.SERV_MACETA_VAL_ID_EXISTS,
					ExceptionMessage.SERV_MACETA_VAL_ID_EXISTS);

		validateMacetaNewCodigo(maceta);

	}
	
	private void validateModify(Maceta maceta) throws ServiceException {
		log.debug("Validar Modificacion");
		if (!checkMacetaIdExist(maceta.getId()))
			throw new ServiceException(ExceptionCode.SERV_MACETA_VAL_ID_NO_EXISTS,
					ExceptionMessage.SERV_MACETA_VAL_ID_NO_EXISTS);

		//TODO Comprobar el c�digo de maceta

	}
	
	private void validateRemove(Long idMaceta) throws ServiceException {
		log.debug("Validar Eliminacion");
		if (!checkMacetaIdExist(idMaceta))
			throw new ServiceException(ExceptionCode.SERV_MACETA_VAL_ID_NO_EXISTS,
					ExceptionMessage.SERV_MACETA_VAL_ID_NO_EXISTS);

	}
	
	private boolean checkMacetaIdExist(long id)  {
		if (dao.findById(id).isPresent())
			return true;
		return false;
	}
	
	private void validateMacetaNewCodigo(Maceta maceta) throws  ServiceException {
		log.debug("validateMacetaCodigo:" + maceta.getCodigo());
		boolean codigoExist = dao.findAll().stream().map(Maceta::getCodigo) // Mapeamos filtramos solamente los nombres de
																			// los huertos
				.anyMatch(s -> s.equalsIgnoreCase(maceta.getCodigo()));// Comparamos con el que vamos a crear/modificar
		if (codigoExist)
			throw new ServiceException(ExceptionCode.SERV_MACETA_VAL_CODIGO_EXISTS,
					ExceptionMessage.SERV_MACETA_VAL_CODIGO_EXISTS);
		log.debug("El codigo no existe");
	}
	
	

}
