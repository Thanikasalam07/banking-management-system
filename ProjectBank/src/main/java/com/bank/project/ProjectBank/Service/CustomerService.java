package com.bank.project.ProjectBank.Service;

import java.util.List;

import javax.security.auth.login.AccountLockedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Exception.AccounNotFoundException;
import com.bank.project.ProjectBank.Exception.CustomerNotFoundException;
import com.bank.project.ProjectBank.Exception.TransactionNotFoundException;
import com.bank.project.ProjectBank.Repository.AccountRepository;
import com.bank.project.ProjectBank.dao.Accountdao;
import com.bank.project.ProjectBank.dao.Customerdao;
import com.bank.project.ProjectBank.dao.Transactiondao;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Transaction;
import com.bank.project.ProjectBank.dto.TransactionType;

import jakarta.transaction.TransactionalException;

@Component
public class CustomerService 
{

	@Autowired
	Customerdao customerdao;
	
	@Autowired
	Accountdao accountdao;
	
	@Autowired
	Transactiondao transactiondao;
	
	public ResponseEntity<Customer> saveCustomer(Customer customer)
	{
		Customer data = customerdao.saveCustomer(customer);
		if(data!=null) return new ResponseEntity<Customer>(data,HttpStatus.CREATED);
		else throw new CustomerNotFoundException("customer object has not been created");
		
	}
	
	public ResponseEntity<Customer> updateCustomer(int id,Customer customer)
	{
		Customer data = customerdao.updateCustomer(id, customer);
		if(data!=null) return new ResponseEntity<Customer>(data,HttpStatus.OK);
		else throw new CustomerNotFoundException("customer object not modified");
	}
	
	public ResponseEntity<Customer> deleteCustomer(int id)
	{
		Customer data = customerdao.deleteCustomer(id);
		if(data!=null) return new ResponseEntity<Customer>(data,HttpStatus.OK);
		else throw new CustomerNotFoundException("customer object not removed");
	}
	
	public ResponseEntity<Customer> findCustomer(int id)
	{
		Customer data = customerdao.findCustomer(id);
		if(data!=null) return new ResponseEntity<Customer>(data,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("customer object not found");
		
	}
	


	
	public ResponseEntity<List<Transaction>> findByTransactionType(TransactionType type)
	{
		List<Transaction> data = transactiondao.findByTransactionType(type);
		if(data!=null) return new ResponseEntity<List<Transaction>>(data,HttpStatus.FOUND);
		else throw new TransactionNotFoundException("transaction details not found");
	}
	
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		List<Customer> data = customerdao.getAllCustomer();
		if(data!=null) return new ResponseEntity<List<Customer>>(data,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("customer details not found");
	}
	
	public ResponseEntity<Account> addAmountToCustomer(Transaction transaction ,int accountNum ,int customerId)
	{
		Customer customer = customerdao.findCustomer(customerId);
		Account account = accountdao.findAccount(accountNum);
		account.getTransaction().add(transaction);
		account.setCustomer(customer);
		if(customer!=null)
		{
			if(account!=null)
			{
				account.setAccountBalance(account.getAccountBalance()+ transaction.getTransactionAmount());
				transaction.setAccount(account);
				transactiondao.saveTransaction(transaction);
				return new ResponseEntity<Account>(account,HttpStatus.OK);
			}
			else throw new AccounNotFoundException("account object not found");
		}
		else throw new CustomerNotFoundException("customer object not found");
		
	}
	
	public ResponseEntity<Account> ReduceAmountToCustomer(Transaction transaction ,int accountNum ,int customerId)
	{
		Customer customer = customerdao.findCustomer(customerId);
		Account account = accountdao.findAccount(accountNum);
		account.getTransaction().add(transaction);
		account.setCustomer(customer);
		if(customer!=null)
		{
			if(account!=null)
			{
				double accountBalance = account.getAccountBalance();
				double transactionAmount = transaction.getTransactionAmount();
				double amount = accountBalance - transactionAmount;
				account.setAccountBalance(amount);
				transaction.setAccount(account);
				transactiondao.saveTransaction(transaction);
				return new ResponseEntity<Account>(account,HttpStatus.OK);
			}
			else throw new AccounNotFoundException("account object not found");
		}
		else throw new CustomerNotFoundException("customer object not found");
		
	}
	
	
	
}
