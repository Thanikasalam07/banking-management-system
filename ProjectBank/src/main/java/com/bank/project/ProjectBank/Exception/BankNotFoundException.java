package com.bank.project.ProjectBank.Exception;

import lombok.Getter;

@Getter
public class BankNotFoundException extends RuntimeException{

	public BankNotFoundException(String message) {
		super();
		this.message = message;
	}

	String message;
	
	
}
