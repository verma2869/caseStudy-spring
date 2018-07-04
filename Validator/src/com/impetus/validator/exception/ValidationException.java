package com.impetus.validator.exception;

public class ValidationException extends Exception{


	private static final long serialVersionUID = 1L;

	public ValidationException(String message, Throwable e) {
		super(e);
		System.out.println("Exception for wrong syntax ");
	}
	
	public ValidationException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
