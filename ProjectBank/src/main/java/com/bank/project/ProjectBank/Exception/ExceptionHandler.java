package com.bank.project.ProjectBank.Exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> accounNotFoundException(AccounNotFoundException acc) {
		return new ResponseEntity<String>(acc.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> bankNotFoundException(BankNotFoundException bank) {
		return new ResponseEntity<String>(bank.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> branchNotFoundException(BranchNotFoundException branch) {
		return new ResponseEntity<String>(branch.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> customerNotFoundException(CustomerNotFoundException customer) {
		return new ResponseEntity<String>(customer.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> managerNotFoundException(ManagerNotFoundException manager) {
		return new ResponseEntity<String>(manager.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> transactionNotFoundException(TransactionNotFoundException transaction) {
		return new ResponseEntity<String>(transaction.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> EmployeeNotFoundException(EmployeeNotFoundException employee) {
		return new ResponseEntity<String>(employee.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> EmployeeNotFoundException(AddressNotFoundException address) {
		return new ResponseEntity<String>(address.getMessage(), HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Map<String, String>> constraintViolationException(ConstraintViolationException constraint) {
		LinkedHashMap<String, String> data = new LinkedHashMap<>();
		for (ConstraintViolation<?> violation : constraint.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			data.put(field, message);
		}

		return new ResponseEntity<Map<String, String>>(data, HttpStatus.FORBIDDEN);
	}
}
