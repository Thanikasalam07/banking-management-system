package com.bank.project.ProjectBank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.TransactionRepository;
import com.bank.project.ProjectBank.dto.Transaction;
import com.bank.project.ProjectBank.dto.TransactionType;

@Repository
public class Transactiondao {

	@Autowired
	TransactionRepository transactionrepo;
	
	public Transaction saveTransaction(Transaction transaction)
	{
		Transaction data = transactionrepo.save(transaction);
		if(data!=null) return data;
		else return null;
	}
	
	public Transaction findTransaction(int id)
	{
		Optional<Transaction> data = transactionrepo.findById(id);
		if(data.isPresent())
			return data.get();
		else return null;
	}
	
	public Transaction deleteTransaction(int id)
	{
		Transaction data = findTransaction(id);
		if(data!=null) {transactionrepo.delete(data);
		return data;}
		else return null;
	}
	
	public Transaction updateTransaction(int id,Transaction transaction)
	{
		Transaction data = findTransaction(id);
		if(data!=null)
		{
			transaction.setTransactionId(id);
			return transactionrepo.save(transaction);
		}
		return null;
	}
	
	public List<Transaction> findByTransactionType(TransactionType type)
	{
		List<Transaction> data = transactionrepo.findByTransactionType(type);
		if(data!=null) return data;
		else return null;
	}
}
