package com.bank.project.ProjectBank.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CustomerService {

	@Autowired
	Customerdao customerdao;

	@Autowired
	Accountdao accountdao;

	@Autowired
	Transactiondao transactiondao;

	
	public ResponseEntity<Transaction> depositToAccount(int accountId, Transaction transaction)
	{
		Account account = accountdao.findAccount(accountId);
		if(account!=null)
		{
			double accountBalance = account.getAccountBalance();
			accountBalance = accountBalance + transaction.getTransactionAmount();
			account.getTransaction().add(transaction);
			transaction.setStatus("success");
			Transaction saveTransaction = transactiondao.saveTransaction(transaction);			
			return new ResponseEntity<Transaction>(saveTransaction,HttpStatus.OK);
		}
		else throw new AccounNotFoundException("account object not found");
	}

	
	public ResponseEntity<Transaction> withdrawFromAccount(int accountId, Transaction transaction)
	{
		Account account = accountdao.findAccount(accountId);
		if(account!=null)
		{
			if(account.getAccountBalance()>=transaction.getTransactionAmount())
			{
				double accountBalance = account.getAccountBalance();
				accountBalance = accountBalance - transaction.getTransactionAmount();
				account.getTransaction().add(transaction);
				transaction.setStatus("success");
				accountdao.updateAccount(accountId, account);
				Transaction saveTransaction = transactiondao.saveTransaction(transaction);
				
				return new ResponseEntity<Transaction>(saveTransaction ,HttpStatus.OK);
			}
			else throw new TransactionNotFoundException("insufficient amount");
		}
		else throw new AccounNotFoundException("account object not found");
	}
	
	
	public ResponseEntity<Transaction> transferBetweenAccounts(int senderaccount, int receiveraccount, Transaction transaction)
	{
		
		Account sender_account = accountdao.findAccount(senderaccount);
		Account receiver_account = accountdao.findAccount(receiveraccount);
		if(sender_account!=null)
		{
			if(receiver_account!=null)
			{
				double accountBalance_s = sender_account.getAccountBalance();
				double accountBalance_receiver = receiver_account.getAccountBalance();
				if(accountBalance_s>=transaction.getTransactionAmount())
				{
					accountBalance_s = sender_account.getAccountBalance() - transaction.getTransactionAmount();
					accountBalance_receiver = receiver_account.getAccountBalance() + transaction.getTransactionAmount();
					sender_account.getTransaction().add(transaction);
					receiver_account.getTransaction().add(transaction);
					sender_account.getSentTransactions().add(transaction);
					
					
					receiver_account.getTransaction().add(transaction);
					receiver_account.getReceivedTransactions().add(transaction);
					
					Transaction saveTransaction = transactiondao.saveTransaction(transaction);
					accountdao.updateAccount(receiveraccount, receiver_account);
					accountdao.updateAccount(senderaccount, sender_account);
					
					return new ResponseEntity<Transaction>(saveTransaction,HttpStatus.OK);
				}
				else throw new TransactionNotFoundException("insufficient balance");
			}
			else throw new AccounNotFoundException("receiver account not found");
		}
		else throw new AccounNotFoundException("sender account not found");
	}
	
	
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccountId(int accountId)
    {
    	Account account = accountdao.findAccount(accountId);
    	if(account!=null)
    	{
    		List<Transaction> transaction = account.getTransaction();
    		return new ResponseEntity<List<Transaction>>(transaction,HttpStatus.FOUND);
  
    	}
    	else throw new AccounNotFoundException("account object not found");
    }
    
    public ResponseEntity<List<Transaction>> 	getCreditTransactions(int accountId){
    	
    	Account account = accountdao.findAccount(accountId);
    	List<Transaction> newdata = new ArrayList<Transaction>();
    	if(account!=null)
    	{
    		List<Transaction> transaction = account.getTransaction();
    		for(Transaction t:transaction)
    		{
    			if(t.getType()==TransactionType.credit)
    			{
    				newdata.add(t);
    			}
    		}
    		
    		return new ResponseEntity<List<Transaction>>(newdata,HttpStatus.FOUND);
    	}
    	
    	else throw new AccounNotFoundException("account object not found");
    }
    
    
    public ResponseEntity<List<Transaction>> getDebitTransactions(int accountId){
    	
    	Account account = accountdao.findAccount(accountId);
    	List<Transaction> newdata = new ArrayList<Transaction>();
    	if(account!=null)
    	{
    		List<Transaction> transaction = account.getTransaction();
    		for(Transaction t:transaction)
    		{
    			if(t.getType()==TransactionType.debit)
    			{
    				newdata.add(t);
    			}
    		}
    		
    		return new ResponseEntity<List<Transaction>>(newdata,HttpStatus.FOUND);
    	}
    	
    	else throw new AccounNotFoundException("account object not found");
    }
    
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(int accountId, LocalDateTime from, LocalDateTime to)
    {
    	
    	Account account = accountdao.findAccount(accountId);
    	List<Transaction> newdata = new ArrayList<Transaction>();
    	if(account!=null)
    	{
    		List<Transaction> transaction = account.getTransaction();
    		for(Transaction t:transaction)
    		{
    			if(!t.getTimestamp().isBefore(from) && !t.getTimestamp().isAfter(to))
    			{
    				newdata.add(t);
    			}
    		}
    		
    		return new ResponseEntity<List<Transaction>>(newdata,HttpStatus.FOUND);
    	}
    	else throw new AccounNotFoundException("account object not found");
    	
    }
    
}
