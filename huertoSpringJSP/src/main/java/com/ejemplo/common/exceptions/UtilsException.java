package com.ejemplo.common.exceptions;

public class UtilsException {
	
	
	public static ServiceException classify(Exception e) {
		return new ServiceException(ExceptionCode.GENERAL_EXCEPTION,e.getMessage());
	}
	
	public static String classify(ServiceException se) {
		switch(se.getCodigo()) {
		case ExceptionCode.SERV_HUERTO_VAL_ID_EXISTS:
			return "error.huerto.exist";
		case ExceptionCode.SERV_HUERTO_VAL_ID_NO_EXISTS:
			return "error.huerto.noexist";
		case ExceptionCode.SERV_HUERTO_VAL_NAME_EXISTS:
			return "error.huerto.name.exist";
		case ExceptionCode.SERV_MACETA_VAL_ID_EXISTS:
			return "error.maceta.exist";
		case ExceptionCode.SERV_MACETA_VAL_ID_NO_EXISTS:
			return "error.maceta.noexist";
		case ExceptionCode.SERV_MACETA_VAL_CODIGO_EXISTS:
			return "error.maceta.codigo.exist";
		default: return null;
		}
		
	}

}
