package com.bank.project.ProjectBank.Controller;

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
	
	
	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer)
	{
		return customerservice.saveCustomer(customer);
	}
	
	@GetMapping
	public ResponseEntity<Customer> findCustomer(@RequestParam int id)
	{
		return customerservice.findCustomer(id);
		
	}
	
	@DeleteMapping
	public ResponseEntity<Customer> deleteCustomer(@RequestParam int id)
	{
		return customerservice.deleteCustomer(id);
	}
	
	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestParam int id , @RequestBody Customer customer)
	{
		return customerservice.updateCustomer(id, customer);
	}
	
	@GetMapping("getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return customerservice.getAllCustomers();
	}
	
	@PutMapping("deposit")
	public ResponseEntity<Account> addAmountToCustomer(@RequestBody Transaction transaction ,@RequestParam int accountNum ,@RequestParam int customerId)
	{
		 return customerservice.addAmountToCustomer(transaction, accountNum, customerId);
	}
	
	@PutMapping("withdraw")
	public ResponseEntity<Account> ReduceAmountToCustomer(@RequestBody Transaction transaction ,@RequestParam int accountNum ,@RequestParam int customerId)
	{
	    return	customerservice.ReduceAmountToCustomer(transaction, accountNum, customerId);
	}
	
	@GetMapping("findByTransactionType")
	public ResponseEntity<List<Transaction>> findByTransactionType(@RequestParam TransactionType type)
	{
		return customerservice.findByTransactionType(type);
	}
	}
