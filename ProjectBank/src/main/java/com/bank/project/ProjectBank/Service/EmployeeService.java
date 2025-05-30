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
import com.bank.project.ProjectBank.dao.Accountdao;
import com.bank.project.ProjectBank.dao.Branchdao;
import com.bank.project.ProjectBank.dao.Customerdao;
import com.bank.project.ProjectBank.dao.EmployeeDao;
import com.bank.project.ProjectBank.dao.Transactiondao;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Branch;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Employee;
import com.bank.project.ProjectBank.dto.EmployeeType;
import com.bank.project.ProjectBank.dto.Transaction;

@Component
public class EmployeeService {

	@Autowired
	EmployeeDao employeedao;

	@Autowired
	Customerdao customerdao;

	@Autowired
	Branchdao branchDao;

	@Autowired
	Accountdao accountdao;

	@Autowired
	Transactiondao transactiondao;

	// customer---->
	public ResponseEntity<Customer> createCustomerToBranch(Customer customer, int employeeId, int branchId) {
		Branch branch = branchDao.findBranch(branchId);
		Employee employee = employeedao.findEmployeebyId(employeeId);
		customer.setBranch(branch);
		customer.setCreatedBy(employee);
		branch.getCustomer().add(customer);
		if (employee.getEmployeetype() == EmployeeType.teller) {
			Customer saveCustomer = customerdao.saveCustomer(customer);
			if (saveCustomer != null)
				return new ResponseEntity<Customer>(saveCustomer, HttpStatus.CREATED);
			else
				throw new CustomerNotFoundException("customer object has not been created");
		} else
			throw new EmployeeNotFoundException("employee type is not matched");
	}

	public ResponseEntity<Customer> updateCustomerToBranch(int employeeId, int branchId, int customerId,
			Customer customer) {
		Employee employee = employeedao.findEmployeebyId(employeeId);
		Branch branch = branchDao.findBranch(branchId);
		Customer cus = customerdao.findCustomer(customerId);
		if (EmployeeType.teller == employee.getEmployeetype()) {
			if (branch != null) {
				if (cus != null) {
					customer.setCustomerId(customerId);
					customer.setBranch(branch);
					customer.setCreatedBy(employee);
					customer.setAccounts(cus.getAccounts());

					if (customer.getCustomerFirstName().equalsIgnoreCase("string"))
						customer.setCustomerFirstName(cus.getCustomerFirstName());
					else if (customer.getCustomerLastName().equalsIgnoreCase("string"))
						customer.setCustomerLastName(cus.getCustomerLastName());
					else if (customer.getCustomerdob().equalsIgnoreCase("string"))
						customer.setCustomerdob(cus.getCustomerdob());
					else if (customer.getCustomerContact() == 9999999999l)
						customer.setCustomerContact(cus.getCustomerContact());
					else if (customer.getCustomerAddress().equalsIgnoreCase("string"))
						customer.setCustomerAddress(cus.getCustomerAddress());
					else if (customer.getCustomerage().equalsIgnoreCase("string"))
						customer.setCustomerage(cus.getCustomerage());
					else if (customer.getCustomergender().equalsIgnoreCase("string"))
						customer.setCustomergender(cus.getCustomergender());

					customer.setCustomerLastName(cus.getCustomerLastName());
					Customer saveCustomer = customerdao.saveCustomer(customer);
					Branch updateBranch = branchDao.updateBranch(customerId, branch);
					return new ResponseEntity<Customer>(saveCustomer, HttpStatus.OK);
				} else
					throw new CustomerNotFoundException("customer objec not found");
			} else
				throw new BranchNotFoundException("branch object not found");

		} else
			throw new EmployeeNotFoundException("employee object not found");
	}

	public ResponseEntity<Customer> deleteCustomer(int employeeId, int customerId, int branchId) {
		Employee employee = employeedao.findEmployeebyId(employeeId);
		Customer customer = customerdao.findCustomer(customerId);
		Branch branch = branchDao.findBranch(branchId);
		if (branch != null) {
			if (EmployeeType.teller == employee.getEmployeetype()) {
				if (customer != null) {
					branch.getCustomer().remove(customer);
					List<Account> accounts = customer.getAccounts();
					customer.setAccounts(null);
					for (Account a : accounts) {
						accountdao.delAccount(a.getAccountNumber());
					}
					customer.setCreatedBy(null);
					customer.setBranch(null);
					Branch updateBranch = branchDao.updateBranch(branchId, branch);
					customerdao.deleteCustomer(customerId);
					return new ResponseEntity<Customer>(customer, HttpStatus.OK);
				} else
					throw new CustomerNotFoundException("customer object not found");
			} else
				throw new EmployeeNotFoundException("employee object not found");
		} else
			throw new BranchNotFoundException("branch object not found");
	}

}
