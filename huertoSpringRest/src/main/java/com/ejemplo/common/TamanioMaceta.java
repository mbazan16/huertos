package com.ejemplo.common;

public enum TamanioMaceta {PEQUENA(13),MEDIANA(23),GRANDE(45);
	
	private int cms;
	
	private TamanioMaceta(int cms) {
		this.cms=cms;
	}
	
	public int getCms() {
		return cms;
	}

}
