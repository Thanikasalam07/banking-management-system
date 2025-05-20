package com.bank.project.ProjectBank.Exception;

import lombok.Getter;

@Getter
public class AccounNotFoundException extends RuntimeException{

	String message;

	public AccounNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	
}
