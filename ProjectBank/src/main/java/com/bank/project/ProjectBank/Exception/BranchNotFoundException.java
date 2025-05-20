package com.bank.project.ProjectBank.Exception;

import lombok.Getter;

@Getter
public class BranchNotFoundException extends RuntimeException {

	String message;

	public BranchNotFoundException(String message) {
		super();
		this.message = message;
	}
}
