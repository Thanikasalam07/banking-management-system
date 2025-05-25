package com.bank.project.ProjectBank.Controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.bank.project.ProjectBank.Service.BankService;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Bank;
import com.bank.project.ProjectBank.dto.Branch;
import com.bank.project.ProjectBank.dto.Manager;

@RestController
@RequestMapping("bank")
public class BankController {

	@Autowired
	BankService bankservice;

	@PostMapping
	public ResponseEntity<Bank> saveBank(@RequestBody Bank bank) {
		return bankservice.saveBank(bank);
	}

	@GetMapping
	public ResponseEntity<Bank> findBank(@RequestParam int id) {
		return bankservice.findBank(id);
	}

	@DeleteMapping
	public ResponseEntity<Bank> deleteBank(@RequestParam int id) {
		return bankservice.deleteBank(id);
	}

	@PutMapping
	public ResponseEntity<Bank> updateBank(@RequestParam int id, @RequestBody Bank bank) {
		return bankservice.updateBank(id, bank);
	}

	@GetMapping("findByBankName")
	public ResponseEntity<Bank> findByBankName(@RequestParam String bankname) {
		return bankservice.findByBankName(bankname);

	}

	@PutMapping("addNewBranchToBank")
	public ResponseEntity<Bank> addNewBranchToBank(@RequestParam int bankId, @RequestBody Branch branch) {
		return bankservice.addNewBranchToBank(bankId, branch);
	}

	@DeleteMapping("removeBranchToBank")
	public ResponseEntity<Bank> removeBranchToBank(@RequestParam int bankId, @RequestParam int branchId) {
		return bankservice.removeBranchToBank(bankId, branchId);
	}

	@PutMapping("addExistingManagerToBranch")
	public ResponseEntity<Branch> addExistingManagerToBranch(int managerId, int branchId) {
		return bankservice.addExistingManagerToBranch(managerId, branchId);
	}

	@PutMapping("addNewManagerToBranch")
	public ResponseEntity<Branch> addNewManagerToBranch(@RequestParam int branchId, @RequestBody Manager manager) {
		return bankservice.addNewManagerToBranch(branchId, manager);
	}

	@DeleteMapping("removeManagerToBranch")
	public ResponseEntity<Branch> removeManagerToBranch(@RequestParam int managerId, @RequestParam int branchId) {
		return bankservice.removeManagerToBranch(managerId, branchId);
	}

	@GetMapping("getBanks")
	public ResponseEntity<List<Bank>> getAllBank() {
		return bankservice.getallBank();
	}

	@GetMapping("findByBranchName")
	public ResponseEntity<Branch> findByBranchName(@RequestParam String branchname) {
		return bankservice.findByBranchName(branchname);
	}

	@GetMapping("getAllBranches")
	public ResponseEntity<List<Branch>> getAllBranches() {
		return bankservice.getAllBranches();
	}

	@GetMapping("findByManagerName")
	public ResponseEntity<Manager> findByManagerName(@RequestParam String name) {
		return bankservice.findByManagerName(name);
	}

	@GetMapping("calculateBranchCashFlow")
	public ResponseEntity<Double> sumOfDepositsandWithdrawInBranch(@RequestParam int branchId) {
		return bankservice.calculateBranchCashFlow(branchId);
	}

	@GetMapping("countNewAccountsOpenedBetween")
	public ResponseEntity countNewAccountsOpenedBetween(@RequestParam Date startdate, @RequestParam Date enddate,
			@RequestParam int branchid) {
		return bankservice.countNewAccountsOpenedBetween(startdate, enddate, branchid);
	}

	@GetMapping("getTotalDepositsByBranch")
	public ResponseEntity<Double> getTotalDepositsByBranch(@RequestParam int branchId) {

		return bankservice.getTotalDepositsByBranch(branchId);
	}

	@GetMapping("getTotalWithdrawalsByBranch")
	public ResponseEntity<Double> getTotalWithdrawalsByBranch(@RequestParam int branchId) {
		return bankservice.getTotalWithdrawalsByBranch(branchId);
	}

	@GetMapping("getNumberOfTransactionsByBranch")
	public ResponseEntity getNumberOfTransactionsByBranch(@RequestParam int branchId) {
		return bankservice.getNumberOfTransactionsByBranch(branchId);
	}

	@GetMapping("getTotalDepositsBetweenDates")
	public ResponseEntity getTotalDepositsBetweenDates(@RequestParam LocalDateTime startdate, @RequestParam LocalDateTime enddate,
			@RequestParam int branchId) {
		return bankservice.getTotalDepositsBetweenDates(startdate, enddate, branchId);
	}
	
	@GetMapping("accountsOpenedInSingleDay")
	public ResponseEntity accountsOpenedInSingleDay(@RequestParam Date date , @RequestParam int branchId)
	{
		return bankservice.accountsOpenedInSingleDay(branchId, date);
	}
	
}
