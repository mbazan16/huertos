package com.ejemplo.dao;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.ejemplo.common.exceptions.DAOException;
import com.ejemplo.dao.interfaces.IDAO;
import com.ejemplo.entities.Huerto;
import com.ejemplo.entities.Maceta;

public class MacetaDAO implements IDAO<Maceta> {

	private static final Logger log = Logger.getLogger(MacetaDAO.class);
	
	String unidadPersistencia;
	
	public MacetaDAO() {
		unidadPersistencia= DAOUtils.getUnidadPersistencia();
	}

	
	@Override
	public Maceta find(Long id) throws DAOException {
		log.debug("find");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory(unidadPersistencia);
			emanager = emf.createEntityManager();
			
			
			Maceta maceta = emanager.find(Maceta.class, id);
			return maceta;
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}
	}

	@Override
	public List<Maceta> findAll() throws DAOException {
		log.debug("findAll");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory(unidadPersistencia);
			emanager = emf.createEntityManager();
			
			
			List<Maceta> macetas = emanager.createNamedQuery("Maceta.findAll", Maceta.class).getResultList();
			return macetas;
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}
	}

	@Override
	public void crear(Maceta element) throws DAOException {
		log.debug("crear");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory(unidadPersistencia);
			emanager = emf.createEntityManager();
			
			
			emanager.getTransaction().begin();
			emanager.persist(element);
			emanager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		} finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	@Override
	public void grabar(Maceta maceta) throws DAOException {
		log.debug("grabar");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory(unidadPersistencia);
			emanager = emf.createEntityManager();
			
			
			Maceta macetabbdd = emanager.find(Maceta.class, maceta.getId());
			
						
			emanager.getTransaction().begin();
			macetabbdd.setCodigo(maceta.getCodigo());
			macetabbdd.setMaterial(maceta.getMaterial());
			macetabbdd.setTamanio(maceta.getTamanio());	
			
			
			emanager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		}finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	

	@Override
	public void delete(Long id) throws DAOException {
		log.debug("delete");
		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory("UPMySQL");
			emanager = emf.createEntityManager();
			
			
			Maceta maceta = emanager.find(Maceta.class, id);
			emanager.getTransaction().begin();
			emanager.remove(maceta);
			emanager.getTransaction().commit();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		} finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}

	}

	
	private Long findLastId() throws DAOException {
		log.debug("findLastId");
		
		Long id = null;		
		EntityManagerFactory emf=null;
		EntityManager emanager=null;
		
		
		try {
			emf = Persistence.createEntityManagerFactory("UPMySQL");
			emanager = emf.createEntityManager();
			
			
			List<Maceta> macetas = findAll();
			id= macetas.stream().map(h ->  h.getId()).max(Comparator.naturalOrder()).get();
		} catch (Exception e) {
			log.error("Exception", e);
			throw new DAOException(0, "EXCEPCION GENERAL");
		} finally {
			if (emanager != null)
				emanager.close();
			if (emf != null)
				emf.close();
		}
		return id;

	}

}
