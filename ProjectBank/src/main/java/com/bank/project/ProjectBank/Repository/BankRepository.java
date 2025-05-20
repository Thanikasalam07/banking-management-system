package com.bank.project.ProjectBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Bank;

@Component
public interface BankRepository extends JpaRepository<Bank, Integer> {

	@Query("SELECT b FROM Bank b WHERE b.bankName = ?1")
	public Bank findByBankName(String bankname);
}
