package com.ejemplo.common.exceptions;

public class DAOException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String mensaje;	
	
	
	public DAOException() {
		super();
	}
	public DAOException(int codigo) {
		super();
		this.codigo = codigo;
	}
	public DAOException(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
