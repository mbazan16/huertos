package com.ejemplo.dao.interfaces;

import java.util.List;

import com.ejemplo.common.exceptions.DAOException;

public interface IDAO<T> {

	public T find(Long id) throws DAOException;
	public List<T> findAll() throws DAOException;
	public void crear(T element) throws DAOException;
	public void grabar(T element) throws DAOException;
	public void delete(Long id) throws DAOException;
}
