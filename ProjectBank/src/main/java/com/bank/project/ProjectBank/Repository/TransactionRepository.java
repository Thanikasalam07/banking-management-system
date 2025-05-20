package com.bank.project.ProjectBank.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Transaction;
import com.bank.project.ProjectBank.dto.TransactionType;

@Component
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query("select t from Transaction t where t.type = ?1")
	public List<Transaction> findByTransactionType(TransactionType type);

}
