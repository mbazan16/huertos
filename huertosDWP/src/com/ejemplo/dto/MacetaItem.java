package com.ejemplo.dto;

import com.ejemplo.common.MaterialMaceta;
import com.ejemplo.common.TamanioMaceta;

public class MacetaItem {
	
	private Long id;
	private String codigo;
	private TamanioMaceta tamanio;
	private MaterialMaceta material;
	private String nombreHuerto;
	private Long nPlantas;
	

	public MacetaItem() {
		
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	public String getNombreHuerto() {
		return nombreHuerto;
	}

	public void setNombreHuerto(String nombreHuerto) {
		this.nombreHuerto = nombreHuerto;
	}

	public Long getnPlantas() {
		return nPlantas;
	}
	public void setnPlantas(Long nPlantas) {
		this.nPlantas = nPlantas;
	}
	
	
	
	

}
