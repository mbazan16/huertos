package com.ejemplo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;


/**
 * The persistent class for the MACETAS database table.
 * 
 */
@Entity
@Table(name="MACETAS")
@NamedQuery(name="Maceta.findAll", query="SELECT m FROM Maceta m")
public class Maceta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String codigo;
	
	@Enumerated
	private TamanioMaceta tamanio;
	
	@Enumerated(value = EnumType.STRING)
	private MaterialMaceta material;
	

	//bi-directional many-to-one association to Huerto
	@ManyToOne
	@JoinColumn(name="IDHUERTO")
	private Huerto huerto;

	//bi-directional many-to-one association to Planta
	@OneToMany(mappedBy="maceta", cascade = { CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Planta> plantas;

	public Maceta() {
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TamanioMaceta getTamanio() {
		return tamanio;
	}


	public void setTamanio(TamanioMaceta tamanio) {
		this.tamanio = tamanio;
	}


	public MaterialMaceta getMaterial() {
		return material;
	}


	public void setMaterial(MaterialMaceta material) {
		this.material = material;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Huerto getHuerto() {
		return huerto;
	}

	public void setHuerto(Huerto huerto) {
		this.huerto = huerto;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	

	
	
	

}