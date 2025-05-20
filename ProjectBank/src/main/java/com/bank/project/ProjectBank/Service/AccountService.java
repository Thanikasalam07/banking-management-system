package com.bank.project.ProjectBank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Exception.AccounNotFoundException;
import com.bank.project.ProjectBank.dao.Accountdao;
import com.bank.project.ProjectBank.dto.Account;



@Component
public class AccountService {

	@Autowired
	Accountdao accountdao;
	
	public ResponseEntity<Account> saveAccount(Account account)
	{
		Account data = accountdao.saveAccount(account);
		if(data!=null) return new ResponseEntity<Account>(data,HttpStatus.CREATED);
		else throw new AccounNotFoundException("account object has not been created");
	}
	
	public ResponseEntity<Account> findAccount(int id)
	{
		Account data = accountdao.findAccount(id);
		if(data!=null) return new ResponseEntity<Account>(data,HttpStatus.FOUND);
		else throw new AccounNotFoundException("account not found");
	}
	
	public ResponseEntity<Account> deleteAccount(int id)
	{
		Account data = accountdao.delAccount(id);
		if(data!=null) return new ResponseEntity<Account>(data,HttpStatus.OK);
		else throw new AccounNotFoundException("aacount not removed");
		
	}
	
	public ResponseEntity<Account> updateAccount(int id,Account account)
	{
		Account data = accountdao.updateAccount(id, account);
		if(data!=null) return new ResponseEntity<Account>(data,HttpStatus.OK);
		else throw new AccounNotFoundException("account not modified");
		 
	}
}
