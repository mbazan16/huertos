package com.ejemplo.services.interfaces;

import java.util.List;

import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.entities.Maceta;
import com.ejemplo.entities.Planta;


public interface PlantasService {
	
	
	public List<Planta> getPlantas() throws ServiceException;
	public List<Maceta> getMacetas() throws ServiceException;
	public Planta getPlanta(Long id) throws ServiceException;
	public void generatePlanta(Long idMaceta,String nombre) throws ServiceException;
	public void generatePlanta(Planta planta) throws ServiceException;
	public void modifyPlanta(Long idPlanta,Long idMaceta,String nombre) throws ServiceException;
	public void modifyPlanta(Planta planta) throws ServiceException;
	public void removePlanta(Long idPlanta) throws ServiceException;

}
