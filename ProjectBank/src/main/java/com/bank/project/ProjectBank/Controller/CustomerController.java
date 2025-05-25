package com.bank.project.ProjectBank.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.project.ProjectBank.Service.AccountService;
import com.bank.project.ProjectBank.Service.CustomerService;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Transaction;
import com.bank.project.ProjectBank.dto.TransactionType;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerService customerservice;

	@PostMapping("credit")
	public ResponseEntity<Transaction> credit(@RequestParam int accountId, @RequestBody Transaction transaction) {
		return customerservice.depositToAccount(accountId, transaction);

	}

	@PostMapping("debit")
	public ResponseEntity<Transaction> debit(@RequestParam int accountId, @RequestBody Transaction transaction) {
		return customerservice.withdrawFromAccount(accountId, transaction);

	}

	@PostMapping("transfer")
	public ResponseEntity<Transaction> transferBetweenAccounts(@RequestParam int senderaccount,
			@RequestParam int receiveraccount, @RequestBody Transaction transaction) {
		return customerservice.transferBetweenAccounts(senderaccount, receiveraccount, transaction);

	}

	@GetMapping("AllTransactions")
	public ResponseEntity<List<Transaction>> getAllTransactionsByAccountId(@RequestParam int accountId) {
		return customerservice.getAllTransactionsByAccountId(accountId);
	}

	@GetMapping("DebitTransactions")
	public ResponseEntity<List<Transaction>> getDebitTransactions(@RequestParam int accountId) {
     
		return customerservice.getDebitTransactions(accountId);
	}
	
	@GetMapping("TransactionsByDateRange")
	 public ResponseEntity<List<Transaction>> getTransactionsByDateRange(@RequestParam int accountId, @RequestParam LocalDateTime from, @RequestParam LocalDateTime to)
	    {
		  return customerservice.getTransactionsByDateRange(accountId, from, to);
	    }
}
