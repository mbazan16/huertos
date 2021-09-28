package com.ejemplo.services.interfaces;

import java.util.List;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.HuertoItem;
import com.ejemplo.dto.MacetaItem;
import com.ejemplo.entities.Huerto;
import com.ejemplo.entities.Maceta;
import com.ejemplo.entities.Planta;

public interface HuertosService {

	public List<Huerto> getHuertos() throws ServiceException;

	public List<HuertoItem> getListadoHuertos() throws ServiceException;

	public Huerto getHuerto(Long idHuerto) throws ServiceException;

	public void generateHuerto(String nombre) throws ServiceException;

	public void generateHuerto(Huerto huerto) throws ServiceException;

	public void modifyHuerto(Long idHuerto, String nombre) throws ServiceException;

	public void modifyHuerto(Huerto huerto) throws ServiceException;

	public void removeHuerto(Long idHuerto) throws ServiceException;
	
}
