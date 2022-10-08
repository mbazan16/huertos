package com.ejemplo.dto;

public class HuertoItem {
	
	private Long idHuerto;
	private String nombreHuerto;
	private Long nMacetas;
	private Long nPlantas;
	
	
	public HuertoItem() {
		
	}
	
	public Long getIdHuerto() {
		return idHuerto;
	}
	public void setIdHuerto(Long idHuerto) {
		this.idHuerto = idHuerto;
	}
	public String getNombreHuerto() {
		return nombreHuerto;
	}
	public void setNombreHuerto(String nombreHuerto) {
		this.nombreHuerto = nombreHuerto;
	}
	public Long getnMacetas() {
		return nMacetas;
	}
	public void setnMacetas(Long nMacetas) {
		this.nMacetas = nMacetas;
	}
	public Long getnPlantas() {
		return nPlantas;
	}
	public void setnPlantas(Long nPlantas) {
		this.nPlantas = nPlantas;
	}
	
	

}
