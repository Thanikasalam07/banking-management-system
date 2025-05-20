package com.bank.project.ProjectBank.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Exception.BranchNotFoundException;
import com.bank.project.ProjectBank.Exception.CustomerNotFoundException;
import com.bank.project.ProjectBank.Exception.EmployeeNotFoundException;
import com.bank.project.ProjectBank.dao.Branchdao;
import com.bank.project.ProjectBank.dao.Customerdao;
import com.bank.project.ProjectBank.dao.EmployeeDao;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Branch;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Employee;
import com.bank.project.ProjectBank.dto.EmployeeType;

@Component
public class EmployeeService {

	@Autowired
	EmployeeDao employeedao;
	
	@Autowired
	Customerdao customerdao;
	
	@Autowired
	Branchdao branchDao;
	
	public ResponseEntity<Employee> saveEmployee(Employee employee)
	{
		Employee data = employeedao.saveEmployee(employee);
		if(data!=null) return new ResponseEntity<Employee>(data,HttpStatus.CREATED);
		else throw new EmployeeNotFoundException("employee object has not been created");
	}
	
	public ResponseEntity<Employee> findEmpployeeById(int id)
	{
		Employee data = employeedao.findEmployeebyId(id);
		if(data!=null) return new ResponseEntity<Employee>(data,HttpStatus.FOUND);
		else throw new EmployeeNotFoundException("employee object not found");
	}
	
	public ResponseEntity<Employee> updateEmployee(int id , Employee employee)
	{
		Employee data = employeedao.updateEmployee(id, employee);
		if(data!=null) return new ResponseEntity<Employee>(data,HttpStatus.OK);
		else throw new EmployeeNotFoundException("employee object not modified");
	}
	
	public ResponseEntity<Employee> deleteEmployee(int id)
	{
		Employee data = employeedao.deleteEmployee(id);
		if(data!=null) return new ResponseEntity<Employee>(data,HttpStatus.OK);
		else throw new EmployeeNotFoundException("employee object not deleted");	
	}
	
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		List<Employee> data = employeedao.getAllEmployee();
		if(data!=null) return new ResponseEntity<List<Employee>>(data,HttpStatus.FOUND);
		else throw new EmployeeNotFoundException("employee details not found");
	}
	
	//customer---->
	public ResponseEntity<Customer> createCustomerToBranch(Customer customer,int employeeId ,int branchId)
	{
		Branch branch = branchDao.findBranch(branchId);
		Employee employee = employeedao.findEmployeebyId(employeeId);
		customer.setBranch(branch);
		customer.setCreatedBy(employee);
		branch.getCustomer().add(customer);
		if(employee.getEmployeePosition().equalsIgnoreCase("teller")) {
		Customer saveCustomer = customerdao.saveCustomer(customer);
		if(saveCustomer!=null) return new ResponseEntity<Customer>(saveCustomer , HttpStatus.CREATED);
		else throw new CustomerNotFoundException("customer object has not been created");
		}
		else throw new EmployeeNotFoundException("employee type is not matched");
	}
	
	public ResponseEntity<Customer> updateCustomerToBranch(int employeeId , int branchId , int customerId , Customer customer)
	{
		Employee employee = employeedao.findEmployeebyId(employeeId);
		Branch branch = branchDao.findBranch(branchId);
		Customer cus = customerdao.findCustomer(customerId);
		
		
		if(employee.getEmployeePosition().equalsIgnoreCase("teller"))
		{
			if(branch!=null)
			{
				if(cus!=null)
				{
					customer.setCustomerId(customerId);
					customer.setBranch(branch);
					customer.setCreatedBy(employee);
					Customer saveCustomer = customerdao.saveCustomer(customer);
					Branch updateBranch = branchDao.updateBranch(customerId, branch);
					return new ResponseEntity<Customer>(saveCustomer,HttpStatus.OK);
				}
				else throw new CustomerNotFoundException("customer objec not found");
			}
			else throw new BranchNotFoundException("branch object not found");
			
		}
		else throw new EmployeeNotFoundException("employee object not found");
	}
	
	
	public ResponseEntity<Customer> deleteCustomer(int employeeId , int customerId , int branchId)
	{
		Employee employee = employeedao.findEmployeebyId(employeeId);
		Customer customer = customerdao.findCustomer(customerId);
		Branch branch = branchDao.findBranch(branchId);
		if(branch!=null)
		{
		if(employee.getEmployeePosition().equalsIgnoreCase("teller"))
		{
			if(customer!=null)
			{
				branch.getCustomer().remove(customer);
				Branch updateBranch = branchDao.updateBranch(branchId, branch);
				customerdao.deleteCustomer(customerId);
				return new ResponseEntity<Customer>(customer,HttpStatus.OK);
			}
			else throw new CustomerNotFoundException("customer object not found");
		}
		else throw new EmployeeNotFoundException("employee object not found");
		}
		else throw new BranchNotFoundException("branch object not found");
	}
	
	
	
	
	
}
