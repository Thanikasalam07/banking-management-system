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

import com.bank.project.ProjectBank.Service.EmployeeService;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeser;
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
	  return	employeeser.saveEmployee(employee);
	}
	
	@GetMapping
	public ResponseEntity<Employee> findEmployeeById(@RequestParam int id)
	{
	   return employeeser.findEmpployeeById(id);
	}
	
	@DeleteMapping
	public ResponseEntity<Employee> deleteEmployee(@RequestParam int id)
	{
	  return employeeser.deleteEmployee(id);
		
	}
	
	@GetMapping("getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		return employeeser.getAllEmployees();
	}
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestParam int id, @RequestBody Employee employee)
	{
	   return	employeeser.updateEmployee(id, employee);
	}

	@PutMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomerToBranch(@RequestParam int employeeId , @RequestBody Customer customer ,@RequestParam int branchId)
	{
	  return employeeser.createCustomerToBranch(customer, employeeId ,branchId);
	}
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomerToBranch(@RequestParam int employeeId ,@RequestParam int branchId ,@RequestParam int customerId , @RequestBody Customer customer)
	{
		return employeeser.updateCustomerToBranch(employeeId, branchId, customerId, customer);
	}
	
	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<Customer> deleteCustomer(@RequestParam int employeeId ,@RequestParam int customerId ,@RequestParam int branchId)
	{
	  return	employeeser.deleteCustomer(employeeId, customerId, branchId);
	}
}
