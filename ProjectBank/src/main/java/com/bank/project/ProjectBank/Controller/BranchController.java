package com.bank.project.ProjectBank.Controller;

import java.sql.Date;
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

import com.bank.project.ProjectBank.Service.BranchService;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.AccountType;
import com.bank.project.ProjectBank.dto.Branch;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Employee;
import com.bank.project.ProjectBank.dto.EmployeeType;
import com.bank.project.ProjectBank.dto.Manager;
import com.bank.project.ProjectBank.dto.Transaction;

@RestController
@RequestMapping("branch")
public class BranchController {

	@Autowired
	BranchService branchservice;

	@PutMapping("addNewEmployeeToBranch")
	public ResponseEntity<Employee> addNewEmployeeToBranch(@RequestParam int branchId, @RequestBody Employee employee) {
		return branchservice.addNewEmployeeToBranch(branchId, employee);
	}

	@DeleteMapping("removeEmployeeToBranch")
	public ResponseEntity<Branch> removeEmployeeToBranch(@RequestParam int branchId, @RequestParam int employeeId) {

		return branchservice.removeEmployeeToBranch(employeeId, branchId);
	}

	@GetMapping("getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return branchservice.getAllEmployees();
	}

	@GetMapping("getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return branchservice.getAllCustomers();
	}

	@GetMapping("getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return branchservice.getAllAccounts();
	}

	@GetMapping("findByBranchName")
	public ResponseEntity<Branch> findByBranchName(@RequestParam String branchname) {
		return branchservice.findByBranchName(branchname);
	}

	@GetMapping("findByCustomerName")
	public ResponseEntity<Customer> findByCustomerName(@RequestParam String fname, @RequestParam String lname) {
		return branchservice.findByCustomerName(fname, lname);
	}

	@GetMapping("findByAccountType")
	public ResponseEntity<List<Account>> findByAccountType(@RequestParam AccountType accountype) {
		return branchservice.findByAccountType(accountype);
	}

	@GetMapping("calculateBranchCashFlow")
	public ResponseEntity<Double> sumOfDepositsandWithdrawInBranch(@RequestParam int branchId) {
		return branchservice.calculateBranchCashFlow(branchId);
	}

	@GetMapping("countNewAccountsOpenedBetween")
	public ResponseEntity countNewAccountsOpenedBetween(@RequestParam Date startdate, @RequestParam Date enddate,
			@RequestParam int branchid) {
		return branchservice.countNewAccountsOpenedBetween(startdate, enddate, branchid);
	}

	@GetMapping("getTotalDeposits")
	public ResponseEntity<Double> getTotalDepositsByBranch(@RequestParam int branchId) {
		return branchservice.getTotalDeposits(branchId);
	}

	@GetMapping("getTotalWithdrawalsByBranch")
	public ResponseEntity<Double> getTotalWithdrawalsByBranch(@RequestParam int branchId) {
		return branchservice.getTotalWithdrawalsByBranch(branchId);
	}

	@GetMapping("getNumberOfTransactions")
	public ResponseEntity getNumberOfTransactionsByBranch(@RequestParam int branchId) {
		return branchservice.getNumberOfTransactions(branchId);
	}

	@GetMapping("findCustomerByMobileNumber")
	public ResponseEntity<Customer> findCustomerByMobileNumber(@RequestParam long mobilenum) {
		return branchservice.findCustomerByMobileNumber(mobilenum);
	}

	@GetMapping("findCustomerByEmail")
	public ResponseEntity<Customer> findCustomerByEmail(@RequestParam String email) {
		return branchservice.findCustomerByEmail(email);
	}

	@GetMapping("findEmployeesByRole")
	public ResponseEntity<Employee> findEmployeesByRole(@RequestParam EmployeeType findEmployeesByRole) {
		return branchservice.findEmployeesByRole(findEmployeesByRole);
	}

	@GetMapping("findAccountsByCustomerName")
	public ResponseEntity<List<Account>> findAccountsByCustomerName(@RequestParam String fname,
			@RequestParam String lname) {
		return branchservice.findAccountsByCustomerName(fname, lname);
	}

	@GetMapping("findAccountByAccountNumber")
	public ResponseEntity<Account> findAccountByAccountNumber(@RequestParam int accountnum) {
		return branchservice.findAccountByAccountNumber(accountnum);
	}

	@GetMapping("filterAccountsByBalanceRange")
	public ResponseEntity<List<Account>> filterAccountsByBalanceRange(@RequestParam int branchid,
			@RequestParam double minrange, @RequestParam double maxrange) {
		return branchservice.filterAccountsByBalanceRange(branchid, minrange, maxrange);
	}

	@GetMapping("filterAccountsByAccountType")
	public ResponseEntity<List<Account>> filterAccountsByAccountType(@RequestParam int branchid,
			@RequestParam AccountType accountType) {
		return branchservice.filterAccountsByAccountType(branchid, accountType);
	}

	@GetMapping("filterTransactionsByDateRange")
	public ResponseEntity<List<Transaction>> filterTransactionsByDateRange(@RequestParam LocalDateTime startdate,
		 @RequestParam 	LocalDateTime enddate, @RequestParam int branchid) {
		
		return branchservice.filterTransactionsByDateRange(startdate, enddate, branchid);

	}
	
	@GetMapping("filterTransactionsByAmountRange")
	public ResponseEntity<List<Transaction>> filterTransactionsByAmountRange(@RequestParam double startamount, @RequestParam double endamount,
		@RequestParam	int branchid) {
		
		return branchservice.filterTransactionsByAmountRange(startamount, endamount, branchid);
				 
	}
	
	@GetMapping("filterCustomersByAgeRange")
	public ResponseEntity<List<Customer>> filterCustomersByAgeRange(int branchid, String customerage) {
		
		return branchservice.filterCustomersByAgeRange(branchid, customerage);
	}
	
	
	@GetMapping("filterEmployeesByJoiningDate")
	public ResponseEntity<List<Employee>> filterEmployeesByJoiningDate(int branchid, Date joiningdate) {
		return branchservice.filterEmployeesByJoiningDate(branchid, joiningdate);
	}
	
	@GetMapping("filterCustomersByGender")
	public ResponseEntity<List<Customer>> filterCustomersByGender(int branchId , String gender)
	{
		return branchservice.filterCustomersByGender(branchId, gender);
	}
	

}
