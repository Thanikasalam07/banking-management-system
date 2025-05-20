package com.bank.project.ProjectBank.Service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Controller.CustomerController;
import com.bank.project.ProjectBank.Exception.AccounNotFoundException;
import com.bank.project.ProjectBank.Exception.BranchNotFoundException;
import com.bank.project.ProjectBank.Exception.CustomerNotFoundException;
import com.bank.project.ProjectBank.Exception.EmployeeNotFoundException;
import com.bank.project.ProjectBank.Exception.ManagerNotFoundException;
import com.bank.project.ProjectBank.dao.Accountdao;
import com.bank.project.ProjectBank.dao.Branchdao;
import com.bank.project.ProjectBank.dao.Customerdao;
import com.bank.project.ProjectBank.dao.EmployeeDao;
import com.bank.project.ProjectBank.dao.Managerdao;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.AccountType;
import com.bank.project.ProjectBank.dto.Branch;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Employee;
import com.bank.project.ProjectBank.dto.Manager;

@Component
public class BranchService {
	
	@Autowired
	Branchdao branchdao;
	
	@Autowired
	Customerdao customerdao;
	
	@Autowired
	Managerdao managerdao;
	
	
	@Autowired
	EmployeeDao employeedao;
	
	@Autowired
	Accountdao accountdao;
	
	public ResponseEntity<Branch> saveBranch(Branch branch)
	{
		Branch data = branchdao.saveBranch(branch);
		if(data!=null) return new ResponseEntity<Branch>(data,HttpStatus.CREATED);
		else throw new BranchNotFoundException("branch object has not been created");
	}

	public ResponseEntity<Branch> findBranch(int id)
	{
		Branch data = branchdao.findBranch(id);
		if(data!=null) return new ResponseEntity<Branch>(data,HttpStatus.FOUND);
		else throw new BranchNotFoundException("branch object not found");
	}
	
	public ResponseEntity<Branch> deleteBranch(int id)
	{
		Branch data = branchdao.deleteBranch(id);
		if(data!=null) return new ResponseEntity<Branch>(data,HttpStatus.OK);
		else throw new BranchNotFoundException("branch object not removed");
	}
	
	
	public ResponseEntity<Branch> updateBranch(int id,Branch branch)
	{
		Branch data = branchdao.updateBranch(id, branch);
		if(data!=null) return new ResponseEntity<Branch>(data,HttpStatus.OK);
		else throw new BranchNotFoundException("branch object not modified");
	}
	
	
	public ResponseEntity<List<Branch>> getAllBranches()
	{
		List<Branch> data = branchdao.getAllBranches();
		if(data!=null) return new ResponseEntity<List<Branch>>(data,HttpStatus.FOUND);
		else throw new BranchNotFoundException("branch details not found");
	}
	
	
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		List<Employee> data = employeedao.getAllEmployee();
		if(data!=null) return new ResponseEntity<List<Employee>>(data,HttpStatus.FOUND);
		else throw new EmployeeNotFoundException("employee details not found");
	}
	
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		List<Customer> data = customerdao.getAllCustomer();
		if(data!=null) return new ResponseEntity<List<Customer>>(data,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("customer details not found");
	}
	
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		List<Account> data = accountdao.getAllAccounts();
		if(data!=null) return new ResponseEntity<List<Account>>(data,HttpStatus.FOUND);
		else throw new AccounNotFoundException("account details not found");
	}
	
	
	
	public ResponseEntity<Branch> findByBranchName(String branchName)
	{
		Branch data = branchdao.findByBranchName(branchName);
		if(data!=null) return new ResponseEntity<Branch>(data,HttpStatus.FOUND);
		else throw new BranchNotFoundException("branch object not found");
	}
	
	
	public ResponseEntity<Customer> findByCustomerName(String fname ,String lname)
	{
		Customer data = customerdao.findByCustomerName(fname, lname);
		if(data!=null) return new ResponseEntity<Customer>(data,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("customer object not found");
	}
	
	
	public ResponseEntity<List<Account>> findByAccountType(AccountType accountype)
	{
		List<Account> data = accountdao.findByAccountType(accountype);
		if(data!=null) return new ResponseEntity<List<Account>>(data,HttpStatus.FOUND);
		else throw new AccounNotFoundException("account details not found");
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	public ResponseEntity<Branch> addExixtiongCustomerToBranch(int branchID, int customerId)
	{
		Customer customer = customerdao.findCustomer(customerId);
		Branch branch = branchdao.findBranch(branchID);
		if(branch!=null)
		{
			if(customer!=null)
			{
				branch.getCustomer().add(customer);
				Branch updateBranch = branchdao.updateBranch(branchID, branch);
				return new ResponseEntity<Branch>(branch,HttpStatus.OK);
				
			}
			else throw new CustomerNotFoundException("customer object not found");
		}
		 
		else throw new BranchNotFoundException("branch object not found");
	}
	
	
	
	public ResponseEntity<Branch> addNewCustomerToBranch(int branchId , Customer customer)
	{
		
		Branch branch = branchdao.findBranch(branchId);
		Customer saveCustomer = customerdao.saveCustomer(customer);
		if(branch!=null)
		{
			if(saveCustomer!=null)
			{
				branch.getCustomer().add(saveCustomer);
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				return new ResponseEntity<Branch>(branch,HttpStatus.OK);
			}
			else throw new CustomerNotFoundException("customer object has not been created");
		}
		else throw new BranchNotFoundException("branch object not found");
	}
	
	public ResponseEntity<Branch> upadteCustomerToBranch(int branchId, int customerId ,Customer customer)
	{
		Branch branch = branchdao.findBranch(branchId);
		Customer cus = customerdao.findCustomer(customerId);
		if(branch!=null)
		{
			if(cus!=null)
			{
				customer.setCustomerId(customerId);
				Customer saveCustomer = customerdao.saveCustomer(customer);
			    branch.getCustomer().add(saveCustomer);
			    return new ResponseEntity<Branch>(branch,HttpStatus.OK);
			}
			else throw new CustomerNotFoundException("customer object not found");
		}
		else throw new BranchNotFoundException("branch object not found");
	}
	
	public ResponseEntity<Branch> removeCustomerToBranch(int branchId ,  int customerId)
	{
		Branch branch = branchdao.findBranch(branchId);
		Customer customer = customerdao.findCustomer(customerId);
		if(branch!=null)
		{
			if(customer!=null)
			{
				branch.getCustomer().remove(customer);
				Branch updateBranch = branchdao.updateBranch(customerId, branch);
				return new ResponseEntity<Branch>(branch,HttpStatus.OK);
			}
			else throw new CustomerNotFoundException("customer object not found");
		}
		else throw new BranchNotFoundException("branch not found");	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public ResponseEntity<Branch> addNewEmployeeToBranch(int branchId , Employee employee)
	{
		Branch branch = branchdao.findBranch(branchId);
		Employee saveEmployee = employeedao.saveEmployee(employee);
		saveEmployee.setAssignedbranch(branch);
		if(branch!=null)
		{
			if(saveEmployee!=null) {
				branch.getEmployee().add(saveEmployee);
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				return new ResponseEntity<Branch>(branch,HttpStatus.OK);
			}
			else throw new EmployeeNotFoundException("employee object not found ");
		  
		}
		else throw new BranchNotFoundException("branch object not found ");
	}
	
	
	
	public ResponseEntity<Branch> removeEmployeeToBranch(int employeeId , int branchId)
	{
		Branch branchdata = branchdao.findBranch(branchId);
		Employee employeedata = employeedao.findEmployeebyId(employeeId);
		employeedata.setAssignedbranch(null);
		if(branchdata!=null) {
			if(employeedata!=null)
			{
				branchdata.getEmployee().remove(employeedata);
				branchdao.updateBranch(branchId, branchdata);
				employeedao.deleteEmployee(employeeId);
				return new ResponseEntity<Branch>(branchdata,HttpStatus.OK);
			}
			else throw new EmployeeNotFoundException("employee object not found");
		}
		else throw new EmployeeNotFoundException("branch object not found");
			
	}
}
