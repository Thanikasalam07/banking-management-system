package com.bank.project.ProjectBank.Exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException {

	String message;

	public EmployeeNotFoundException(String message) {
		super();
		this.message = message;
	}

}
