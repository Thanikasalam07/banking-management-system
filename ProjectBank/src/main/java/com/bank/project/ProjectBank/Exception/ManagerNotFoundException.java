package com.bank.project.ProjectBank.Exception;

import lombok.Getter;

@Getter
public class ManagerNotFoundException  extends RuntimeException{

	String message;

	public ManagerNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
