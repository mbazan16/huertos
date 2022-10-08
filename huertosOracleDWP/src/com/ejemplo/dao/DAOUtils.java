package com.ejemplo.dao;

import org.apache.log4j.Logger;

public class DAOUtils {
	
	private static final Logger log = Logger.getLogger(DAOUtils.class);
	
	public static String getUnidadPersistencia() {
		log.debug("getUnidadPersistencia");
		String unidadPersistencia="UPOracle";
		
		return unidadPersistencia;
	}

}
