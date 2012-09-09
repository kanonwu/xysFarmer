package com.xys.cenxi.customer.exception;

public class CusException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4047869169657653130L;

	public CusException(){
		
	}
	
	public CusException(Throwable e){
		super(e);
	}
	
	public CusException(String mes){
		super(mes);
	}
	
	public CusException(String mes, Throwable e){
		super(mes, e);
	}
}
