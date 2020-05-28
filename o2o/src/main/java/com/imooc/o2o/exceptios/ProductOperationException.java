package com.imooc.o2o.exceptios;

public class ProductOperationException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4727420275574516707L;

	public ProductOperationException(String msg){
		super(msg);
	}
}
