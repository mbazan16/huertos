package com.ejemplo.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.entities.Huerto;

@Repository
public interface HuertoDAO extends JpaRepository<Huerto, Long>{

}
