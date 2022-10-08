package com.ejemplo.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.entities.Planta;

@Repository
public interface PlantaDAO extends JpaRepository<Planta, Long>{

}
