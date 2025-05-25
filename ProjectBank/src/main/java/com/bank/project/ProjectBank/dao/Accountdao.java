package com.bank.project.ProjectBank.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.AccountRepository;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.AccountType;
import com.bank.project.ProjectBank.dto.Account;

@Repository
public class Accountdao {

	@Autowired
	AccountRepository Account;

	public Account saveAccount(Account account) {
		Account save = Account.save(account);
		if (save != null)
			return save;
		else
			return null;
	}

	public Account findAccount(int id) {
		Optional<Account> data = Account.findById(id);
		if (data.isPresent()) {
			return data.get();
		} else
			return null;
	}

	public Account delAccount(int id) {
		Account data = findAccount(id);
		if (data != null) {
			Account.delete(data);
			return data;
		} else
			return null;
	}

	public Account updateAccount(int id, Account acc) {
		Account data = findAccount(id);
		if (data != null) {
			acc.setAccountNumber(id);
			return Account.save(acc);
		} else
			return null;
	}

	public List<Account> getAllAccounts() {
		List<Account> data = Account.findAll();
		if (data != null)
			return data;
		else
			return null;

	}

	public List<Account> findByAccountType(AccountType accountype) {
		List<Account> data = Account.findByAccountType(accountype);
		if (data != null)
			return data;
		else
			return null;
	}
	
	
}
