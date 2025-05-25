package com.bank.project.ProjectBank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.project.ProjectBank.Service.TransactionService;
import com.bank.project.ProjectBank.dto.Transaction;

@RestController
@RequestMapping("transaction")
public class TransactionController {

	@Autowired
	TransactionService service;

	public ResponseEntity<Transaction> findTransaction(@RequestParam int id) {
		return service.findById(id);
	}
}
