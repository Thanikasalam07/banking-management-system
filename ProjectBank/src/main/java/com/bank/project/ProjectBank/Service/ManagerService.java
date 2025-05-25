package com.bank.project.ProjectBank.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Exception.AccounNotFoundException;
import com.bank.project.ProjectBank.Exception.CustomerNotFoundException;
import com.bank.project.ProjectBank.Exception.EmployeeNotFoundException;
import com.bank.project.ProjectBank.Exception.ManagerNotFoundException;
import com.bank.project.ProjectBank.dao.Accountdao;
import com.bank.project.ProjectBank.dao.Customerdao;
import com.bank.project.ProjectBank.dao.EmployeeDao;
import com.bank.project.ProjectBank.dao.Managerdao;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Employee;
import com.bank.project.ProjectBank.dto.Manager;

@Component
public class ManagerService {

	@Autowired
	Managerdao managerdao;

	@Autowired
	Customerdao customerdao;

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	Accountdao accountdao;

	

	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> allEmployee = employeeDao.getAllEmployee();
		if (!allEmployee.isEmpty())
			return new ResponseEntity<List<Employee>>(allEmployee, HttpStatus.FOUND);
		else
			throw new EmployeeNotFoundException("employee details not found");
	}

	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomer = customerdao.getAllCustomer();
		if (!allCustomer.isEmpty())
			return new ResponseEntity<List<Customer>>(allCustomer, HttpStatus.FOUND);
		else
			throw new CustomerNotFoundException("customer details not found");
	}

	public ResponseEntity<List<Account>> getAllaccounts() {
		List<Account> allAccounts = accountdao.getAllAccounts();
		if (!allAccounts.isEmpty())
			return new ResponseEntity<List<Account>>(allAccounts, HttpStatus.FOUND);
		else
			throw new AccounNotFoundException("account details not found");
	}

}
