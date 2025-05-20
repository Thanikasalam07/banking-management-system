package com.bank.project.ProjectBank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Exception.TransactionNotFoundException;
import com.bank.project.ProjectBank.dao.Transactiondao;
import com.bank.project.ProjectBank.dto.Transaction;

@Component
public class TransactionService {

	@Autowired
	Transactiondao transactiondao;
	
	public ResponseEntity<Transaction> findById(int id)
	{
		Transaction data = transactiondao.findTransaction(id);
		if(data!=null) return new ResponseEntity<Transaction>(data,HttpStatus.FOUND);
		else throw new TransactionNotFoundException("transaction object not found");
	}
}
