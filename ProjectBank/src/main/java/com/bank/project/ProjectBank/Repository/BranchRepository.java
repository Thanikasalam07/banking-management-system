package com.bank.project.ProjectBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Branch;

@Component
public interface BranchRepository extends JpaRepository<Branch, Integer> {

	@Query("SELECT b FROM Branch b WHERE b.branchName = ?1")
	public Branch findByBranchName(String branchName);

}
