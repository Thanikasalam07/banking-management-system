package com.bank.project.ProjectBank.Exception;

import lombok.Getter;

@Getter
public class AddressNotFoundException extends RuntimeException{

	String message;

	public AddressNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
