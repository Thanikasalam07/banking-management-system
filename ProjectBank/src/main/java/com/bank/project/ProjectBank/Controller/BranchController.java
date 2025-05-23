package com.bank.project.ProjectBank.Controller;

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
import com.bank.project.ProjectBank.dto.Manager;

@RestController
@RequestMapping("branch")
public class BranchController {
	
	@Autowired
	BranchService branchservice;
	
	@PostMapping
	public ResponseEntity<Branch> saveBranch( @RequestBody Branch branch)
	{
		return branchservice.saveBranch(branch);
	}
	
	@GetMapping
	public ResponseEntity<Branch> findBranch(@RequestParam int id)
	{
		return branchservice.findBranch(id);
	}
	
	@DeleteMapping
	public ResponseEntity<Branch> deleteBranch(@RequestParam int id)
	{
		return branchservice.deleteBranch(id);
	}

	@PutMapping
	public ResponseEntity<Branch> updateBranch(@RequestParam int id,@RequestBody Branch branch)
	{
		return branchservice.updateBranch(id, branch);
	}
	
	@GetMapping("getAllBranches")
	public ResponseEntity<List<Branch>> getAllBanks()
	{
		return branchservice.getAllBranches();
	}
	
	
	@PutMapping("addExixtiongCustomerToBranch")
	public ResponseEntity<Branch> addExixtiongCustomerToBranch(@RequestParam int branchId ,@RequestParam int customerId)
	{
	   
		return	branchservice.addExixtiongCustomerToBranch( branchId, customerId);
	}
	
	@PutMapping("addNewCustomerToBranch")
	public ResponseEntity<Branch> addNewCustomerToBranch(@RequestParam int branchId , @RequestBody Customer customer)
	{
		return branchservice.addNewCustomerToBranch(branchId, customer);
		
	}
	
	@PutMapping("upadteCustomerToBranch")
	public ResponseEntity<Branch> upadteCustomerToBranch( @RequestParam int branchId , @RequestParam int CustomerId , @RequestBody Customer customer)
	{
		return branchservice.upadteCustomerToBranch(branchId, CustomerId, customer);
	}
	
	@DeleteMapping("removeCustomerToBranch")
	public ResponseEntity<Branch> removeCustomerToBranch(@RequestParam int customerId , @RequestParam int branchId)
	{
		return branchservice.removeCustomerToBranch(branchId, customerId);
	}
	
	
	
	@PutMapping("addNewEmployeeToBranch")
	public ResponseEntity<Branch> addNewEmployeeToBranch(@RequestParam int branchId , @RequestBody Employee employee)
	{
	  return branchservice.addNewEmployeeToBranch(branchId, employee);
	}
	
	@DeleteMapping("removeEmployeeToBranch")
	public ResponseEntity<Branch> removeEmployeeToBranch(@RequestParam int branchId , @RequestParam int employeeId)
	{
		
	   return	branchservice.removeEmployeeToBranch(employeeId, branchId);
	}
	
	
	@GetMapping("getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		return branchservice.getAllEmployees();
	}
	
	@GetMapping("getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return branchservice.getAllCustomers();
	}
	
	@GetMapping("getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		return branchservice.getAllAccounts();
	}
	
	@GetMapping("findByBranchName")
	public ResponseEntity<Branch> findByBranchName(@RequestParam String branchname)
	{
		return branchservice.findByBranchName(branchname);
	}
	
	@GetMapping("findByCustomerName")
	public ResponseEntity<Customer> findByCustomerName(@RequestParam String fname , @RequestParam String lname)
	{
		return branchservice.findByCustomerName(fname, lname);
	}
	
	@GetMapping("findByAccountType")
	public ResponseEntity<List<Account>> findByAccountType(@RequestParam AccountType accountype)
	{
	   return branchservice.findByAccountType(accountype);
    }
	
	@GetMapping("calculateBranchCashFlow")
	public ResponseEntity<Double> sumOfDepositsandWithdrawInBranch(@RequestParam int branchId)
	{
		return branchservice.calculateBranchCashFlow(branchId);
	}
}
