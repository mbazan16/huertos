package com.ejemplo.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HUERTOS database table.
 * 
 */
@Entity
@Table(name="HUERTOS") 
@NamedQuery(name="Huerto.findAll", query="SELECT h FROM Huerto h")
public class Huerto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seqHuerto", sequenceName = "SEQ_HUERTO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqHuerto")
	private long id;

	private String nombre;
	
	//bi-directional many-to-one association to Maceta
	@OneToMany(mappedBy="huerto", cascade = { CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Maceta> macetas;

	public Huerto() {
	}
	
	public Huerto(String nombre) {
		this.nombre=nombre;
	}

	public Huerto(Long id, String nombre) {
		this.id=id;
		this.nombre=nombre;
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

	
	public List<Maceta> getMacetas() {
		return this.macetas;
	}

	public void setMacetas(List<Maceta> macetas) {
		this.macetas = macetas;
	}

	public Maceta addMaceta(Maceta maceta) {
		getMacetas().add(maceta);
		maceta.setHuerto(this);

		return maceta;
	}

	public Maceta removeMaceta(Maceta maceta) {
		getMacetas().remove(maceta);
		maceta.setHuerto(null);

		return maceta;
	}

	
	

}