package com.ejemplo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.ejemplo.common.exceptions.DAOException;
import com.ejemplo.common.exceptions.ExceptionCode;
import com.ejemplo.common.exceptions.ExceptionMessage;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.common.exceptions.UtilsException;
import com.ejemplo.dao.HuertoDAO;
import com.ejemplo.dao.MacetaDAO;
import com.ejemplo.dao.PlantaDAO;
import com.ejemplo.dao.interfaces.IDAO;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.entities.Huerto;
import com.ejemplo.entities.Maceta;
import com.ejemplo.entities.Planta;
import com.ejemplo.services.interfaces.HuertosService;

public class HuertosImpl implements HuertosService {

	private static final Logger log = Logger.getLogger(HuertosImpl.class);

	IDAO<Huerto> dao;
	IDAO<Maceta> macetaDAO;
	IDAO<Planta> plantaDAO;

	private HuertosImpl() {
	}

	public static HuertosImpl init() {
		log.debug("init");
		HuertosImpl servicio = new HuertosImpl();
		servicio.dao = new HuertoDAO();
		servicio.macetaDAO = new MacetaDAO();
		servicio.plantaDAO = new PlantaDAO();
		return servicio;
	}

	@Override
	public List<Huerto> getHuertos() throws ServiceException {
		log.debug("getHuertos");
		List<Huerto> huertos = new ArrayList<Huerto>();
		try {

			huertos = dao.findAll();

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

		return huertos;
	}

	@Override
	public List<HuertoItem> getListadoHuertos() throws ServiceException {
		log.debug("getListadoHuertos");
		List<HuertoItem> huertos = new ArrayList<HuertoItem>();
		try {

			List<Huerto> huertosEntities = dao.findAll();
			List<Planta> plantas = plantaDAO.findAll();
			
			for (Huerto huertoAux : huertosEntities) {
				HuertoItem huertoItem = new HuertoItem();
				huertoItem.setIdHuerto(huertoAux.getId());
				huertoItem.setNombreHuerto(huertoAux.getNombre());
				
				Long nMacetas =(long) huertoAux.getMacetas().size();				
				huertoItem.setnMacetas(nMacetas);

				//OPCION 1: Se recorre toda la lista de plantas para comprobar que tiene
				//el mismo id de huerto que el que estamos tratando
				Long nPlantas = plantas.stream()
						.filter(s -> s.getMaceta() != null)
						.filter(s -> s.getMaceta().getHuerto().getId() == huertoItem.getIdHuerto()).count();
				huertoItem.setnPlantas(nPlantas);
				
				//OPCION 2: Se recorren todas las macetas del huerto que estamos tratando
				// y conseguimos el número de plantas que tienen. 
				//Esta opción es mucho más optima si hay un número grande de huertos,macetas y plantas
				//Aqui no haría falta la variable "plantas"
				/*
				 * for(Maceta maceta: huertoAux.getMacetas()) { Long nPlantas =(long)
				 * maceta.getPlantas().size(); huertoItem.setnPlantas(nPlantas);
				 * 
				 * }
				 */
				//OPCION 2: Igual que la OPCION 2 pero con lambdas 
				//Aqui no haría falta la variable "plantas"
				/*
				 * huertoAux.getMacetas().stream() //stream Maceta
				 * .map(s->(long)s.getPlantas().size())//stream long .forEach(l ->
				 * huertoItem.setnPlantas(l)); //.forEach(huertoItem::setnPlantas);
				 */

				huertos.add(huertoItem);
			}

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

		return huertos;
	}

	@Override
	public Huerto getHuerto(Long idHuerto) throws ServiceException {
		log.debug("getHuerto");
		Huerto huerto = null;
		try {

			huerto = dao.find(idHuerto);

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

		return huerto;
	}

	@Override
	public void generateHuerto(String nombre) throws ServiceException {
		log.debug("generateHuerto");
		try {
			Huerto huerto = new Huerto(nombre);
			newHuerto(huerto);

		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
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
	public void generateHuerto(Huerto huerto) throws ServiceException {
		log.debug("generateHuerto");
		try {

			newHuerto(huerto);

		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
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
			dao.delete(idHuerto);

		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
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

		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
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

		} catch (DAOException daoe) {
			log.error(daoe.getMensaje());
			ServiceException se = UtilsException.classify(daoe);
			if (se != null)
				throw se;
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

	/*
	 * *
	 * 
	 * @Override public List<Maceta> getListadoMacetas(Long idHuerto) throws
	 * ServiceException { log.debug("getHuertos"); List<Huerto> huertos = new
	 * ArrayList<Huerto>(); try {
	 * 
	 * huertos = dao.findAll();
	 * 
	 * } catch (DAOException daoe) { log.error(daoe.getMensaje()); ServiceException
	 * se = UtilsException.classify(daoe); if (se != null) throw se; } catch
	 * (Exception e) { log.error(e.getMessage(), e); ServiceException se =
	 * UtilsException.classify(e); if (se != null) throw se; }
	 * 
	 * return huertos; }
	 * 
	 * @Override public void generateMaceta(Long idHuerto, Maceta maceta) throws
	 * ServiceException { log.debug("getHuertos"); List<Huerto> huertos = new
	 * ArrayList<Huerto>(); try {
	 * 
	 * huertos = dao.findAll();
	 * 
	 * } catch (DAOException daoe) { log.error(daoe.getMensaje()); ServiceException
	 * se = UtilsException.classify(daoe); if (se != null) throw se; } catch
	 * (Exception e) { log.error(e.getMessage(), e); ServiceException se =
	 * UtilsException.classify(e); if (se != null) throw se; }
	 * 
	 * return huertos;
	 * 
	 * }
	 * 
	 * @Override public List<Planta> getListadoPlantas(Long idHuerto) throws
	 * ServiceException { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public void generatePlanta(Long idHuerto, Long idMaceta, Planta
	 * planta) throws ServiceException { log.debug("getHuertos"); List<Huerto>
	 * huertos = new ArrayList<Huerto>(); try {
	 * 
	 * huertos = dao.findAll();
	 * 
	 * } catch (DAOException daoe) { log.error(daoe.getMensaje()); ServiceException
	 * se = UtilsException.classify(daoe); if (se != null) throw se; } catch
	 * (Exception e) { log.error(e.getMessage(), e); ServiceException se =
	 * UtilsException.classify(e); if (se != null) throw se; }
	 * 
	 * return huertos; }
	 */

	private List<Planta> getPlantasHuertos(Long idHuerto) throws DAOException {
		List<Planta> plantas = plantaDAO.findAll();
		List<Planta> plantasHuerto = plantas.stream().filter(s -> s.getMaceta().getHuerto().getId() == idHuerto)
				.collect(Collectors.toList());
		return plantasHuerto;
	}

	private void newHuerto(Huerto huerto) throws DAOException, ServiceException {
		log.debug("crearHuerto");
		validateGenerate(huerto);
		log.debug("Crear Huerto:" + huerto.getNombre());
		dao.crear(huerto);
	}
	
	private void setHuerto(Huerto huerto) throws DAOException, ServiceException {
		validateModify(huerto);
		dao.grabar(huerto);
	}

	private void validateGenerate(Huerto huerto) throws DAOException, ServiceException {
		log.debug("Validar Creacion");
		log.debug("Validar si Id existe");
		if (checkHuertoIdExist(huerto.getId()))
			throw new ServiceException(ExceptionCode.SERV_HUERTO_VAL_ID_EXISTS,
					ExceptionMessage.SERV_HUERTO_VAL_ID_EXISTS);

		validateHuertoName(huerto);

	}

	private void validateModify(Huerto huerto) throws DAOException, ServiceException {
		log.debug("Validar Modificacion");
		if (!checkHuertoIdExist(huerto.getId()))
			throw new ServiceException(ExceptionCode.SERV_HUERTO_VAL_ID_NO_EXISTS,
					ExceptionMessage.SERV_HUERTO_VAL_ID_NO_EXISTS);
		validateHuertoName(huerto);

	}

	private void validateRemove(Long idHuerto) throws DAOException, ServiceException {
		if (!checkHuertoIdExist(idHuerto))
			throw new ServiceException(ExceptionCode.SERV_HUERTO_VAL_ID_NO_EXISTS,
					ExceptionMessage.SERV_HUERTO_VAL_ID_NO_EXISTS);

	}

	private boolean checkHuertoIdExist(long id) throws DAOException {
		if (dao.find(id) != null)
			return true;
		return false;
	}

	private void validateHuertoName(Huerto huerto) throws DAOException, ServiceException {
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
