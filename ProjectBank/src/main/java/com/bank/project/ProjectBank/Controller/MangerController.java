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
import com.bank.project.ProjectBank.Service.ManagerService;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Employee;
import com.bank.project.ProjectBank.dto.Manager;

@RestController
@RequestMapping("manager")
public class MangerController {

	@Autowired
	ManagerService managerservice;

	@Autowired
	BranchService branchService;
    

	@GetMapping("getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return managerservice.getAllEmployees();
	}

	@GetMapping("getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return managerservice.getAllCustomers();
	}

	@GetMapping("getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return managerservice.getAllaccounts();
	}
	
	@GetMapping("calculateBranchCashFlow")
	
	public ResponseEntity<Double> calculateBranchCashFlow(@RequestParam int branchId)
	{
	  return	branchService.calculateBranchCashFlow(branchId);
	}

	
}
