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
	
	@PostMapping
	public ResponseEntity<Manager> saveManager(@RequestBody Manager manager)
	{
		
		return managerservice.saveManager(manager);
	}
	
	
	@PutMapping
	public ResponseEntity<Manager> updateManager(@RequestParam int id , @RequestBody Manager manager)
	{
		return managerservice.updateManager(id, manager);
	}
	
	@DeleteMapping
	public ResponseEntity<Manager> deleteManager(@RequestParam int id)
	{
	return	managerservice.deleteManager(id);
		
	}
	
	@GetMapping
	public ResponseEntity<Manager> findManager(@RequestParam int id)
	{
		return managerservice.findManager(id);
	}
	
	@GetMapping("getAllManagers")
	public ResponseEntity<List<Manager>> getAllManagers()
	{
		return managerservice.getAllManagers();
	}
	
	@GetMapping("findByManagerName")
	public ResponseEntity<Manager> findByManagerName(@RequestParam String name)
	{
		return managerservice.findByManagerName(name);
	}
	
	
	@GetMapping("getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		return managerservice.getAllEmployees();
	}
	
	@GetMapping("getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return managerservice.getAllCustomers();
	}
	
	@GetMapping("getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		return managerservice.getAllaccounts();
	}
}
