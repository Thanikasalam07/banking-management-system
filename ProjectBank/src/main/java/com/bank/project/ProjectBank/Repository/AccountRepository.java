package com.bank.project.ProjectBank.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.AccountType;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("select a from Account a where a.accountype = ?1")
	public List<Account> findByAccountType(AccountType accountype);

}
