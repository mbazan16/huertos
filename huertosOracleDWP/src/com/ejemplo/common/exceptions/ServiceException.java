package com.ejemplo.common.exceptions;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String mensaje;	
	
	
	public ServiceException() {
		super();
	}
	public ServiceException(int codigo) {
		super();
		this.codigo = codigo;
	}
	public ServiceException(int codigo, String mensaje) {
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
