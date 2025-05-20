package com.bank.project.ProjectBank.Exception;

import lombok.Getter;

@Getter
public class TransactionNotFoundException  extends RuntimeException{

	String message;

	public TransactionNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	
}
