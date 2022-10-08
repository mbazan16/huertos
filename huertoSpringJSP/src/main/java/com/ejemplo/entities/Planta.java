package com.ejemplo.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PLANTAS database table.
 * 
 */
@Entity
@Table(name="PLANTAS")
@NamedQuery(name="Planta.findAll", query="SELECT p FROM Planta p")
public class Planta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String nombre;

	//bi-directional many-to-one association to Maceta
	@ManyToOne(optional = true)
	@JoinColumn(name="IDMACETA")
	private Maceta maceta;

	public Planta() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Maceta getMaceta() {
		return this.maceta;
	}

	public void setMaceta(Maceta maceta) {
		this.maceta = maceta;
	}

	
	
	

}