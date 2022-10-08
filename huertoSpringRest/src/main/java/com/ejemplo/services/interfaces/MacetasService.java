package com.ejemplo.services.interfaces;

import java.util.HashMap;
import java.util.List;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;
import com.ejemplo.common.exceptions.ServiceException;
import com.ejemplo.dto.MacetaItem;
import com.ejemplo.entities.Maceta;


public interface MacetasService {
	
	public List<MacetaItem> getListadoMacetas() throws ServiceException;
	public Maceta getMaceta(Long idMaceta) throws ServiceException;
	public void generateMaceta(Long idHuerto,String codigo,TamanioMaceta tamanio, MaterialMaceta material) throws ServiceException;
	public void generateMaceta(Maceta maceta) throws ServiceException;	
	public void modifyMaceta(Long idMaceta,Long idHuerto,String codigo,TamanioMaceta tamanio, MaterialMaceta material) throws ServiceException;
	public void modifyMaceta(Maceta maceta) throws ServiceException;	
	public void removeMaceta(Long idMaceta) throws ServiceException;
	
	public HashMap<Long,String> getHuertos() throws ServiceException;
 
}
