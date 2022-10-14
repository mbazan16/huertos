package com.ejemplo.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.entities.Maceta;

@Repository
public interface MacetaDAO extends JpaRepository<Maceta, Long>{	
	
	

}
