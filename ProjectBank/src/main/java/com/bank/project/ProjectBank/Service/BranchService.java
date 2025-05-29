package com.bank.project.ProjectBank.Service;

import java.sql.Date;
import java.util.ArrayList;
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
import com.bank.project.ProjectBank.dto.EmployeeType;
import com.bank.project.ProjectBank.dto.Manager;
import com.bank.project.ProjectBank.dto.Transaction;
import com.bank.project.ProjectBank.dto.TransactionType;

import lombok.Getter;
import lombok.Setter;

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

	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> data = employeedao.getAllEmployee();
		if (data != null)
			return new ResponseEntity<List<Employee>>(data, HttpStatus.FOUND);
		else
			throw new EmployeeNotFoundException("employee details not found");
	}

	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> data = customerdao.getAllCustomer();
		if (data != null)
			return new ResponseEntity<List<Customer>>(data, HttpStatus.FOUND);
		else
			throw new CustomerNotFoundException("customer details not found");
	}

	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> data = accountdao.getAllAccounts();
		if (data != null)
			return new ResponseEntity<List<Account>>(data, HttpStatus.FOUND);
		else
			throw new AccounNotFoundException("account details not found");
	}

	public ResponseEntity<Branch> findByBranchName(String branchName) {
		Branch data = branchdao.findByBranchName(branchName);
		if (data != null)
			return new ResponseEntity<Branch>(data, HttpStatus.FOUND);
		else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity<Customer> findByCustomerName(String fname, String lname) {
		Customer data = customerdao.findByCustomerName(fname, lname);
		if (data != null)
			return new ResponseEntity<Customer>(data, HttpStatus.FOUND);
		else
			throw new CustomerNotFoundException("customer object not found");
	}

	public ResponseEntity<List<Account>> findByAccountType(AccountType accountype) {
		List<Account> data = accountdao.findByAccountType(accountype);
		if (data != null)
			return new ResponseEntity<List<Account>>(data, HttpStatus.FOUND);
		else
			throw new AccounNotFoundException("account details not found");
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////

	public ResponseEntity<Employee> addNewEmployeeToBranch(int branchId, Employee employee) {
		Branch branch = branchdao.findBranch(branchId);
		Employee saveEmployee = employeedao.saveEmployee(employee);
		saveEmployee.setAssignedbranch(branch);
		if (branch != null) {
			if (saveEmployee != null) {
				branch.getEmployee().add(saveEmployee);
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				return new ResponseEntity<Employee>(saveEmployee, HttpStatus.OK);
			} else
				throw new EmployeeNotFoundException("employee object not found ");

		} else
			throw new BranchNotFoundException("branch object not found ");
	}

	public ResponseEntity<Branch> removeEmployeeToBranch(int employeeId, int branchId) {
		Branch branchdata = branchdao.findBranch(branchId);
		Employee employeedata = employeedao.findEmployeebyId(employeeId);
		employeedata.setAssignedbranch(null);
		if (branchdata != null) {
			if (employeedata != null) {
				branchdata.getEmployee().remove(employeedata);
				branchdao.updateBranch(branchId, branchdata);
				employeedao.deleteEmployee(employeeId);
				return new ResponseEntity<Branch>(branchdata, HttpStatus.OK);
			} else
				throw new EmployeeNotFoundException("employee object not found");
		} else
			throw new EmployeeNotFoundException("branch object not found");

	}

	public ResponseEntity<Double> calculateBranchCashFlow(int branchId) {
		Branch branch = branchdao.findBranch(branchId);
		double totalamount = 0;
		if (branch != null) {
			List<Customer> customer = branch.getCustomer();

			for (Customer c : customer) {
				List<Account> accounts = c.getAccounts();
				for (Account a : accounts) {
					if (a.getAccountype() == AccountType.Saving) {
						List<Transaction> transaction = a.getTransaction();
						for (Transaction t : transaction) {
							if (t.getType() == TransactionType.credit)
								totalamount += t.getTransactionAmount();
							else if (t.getType() == TransactionType.debit)
								totalamount -= t.getTransactionAmount();
						}
					} else if (a.getAccountype() == AccountType.Current) {
						List<Transaction> transaction = a.getTransaction();
						for (Transaction t : transaction) {
							if (t.getType() == TransactionType.credit)
								totalamount += t.getTransactionAmount();
							else if (t.getType() == TransactionType.debit)
								totalamount -= t.getTransactionAmount();
						}
					}

				}

			}

			return new ResponseEntity<Double>(totalamount, HttpStatus.OK);

		} else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity countNewAccountsOpenedBetween(Date startdate, Date enddate, int branchId) {
		Branch branch = branchdao.findBranch(branchId);
		List<Account> newdata = new ArrayList<Account>();
		int accountcount = 0;
		if (branch != null) {

			List<Customer> customer = branch.getCustomer();
			for (Customer c : customer) {
				List<Account> gettingaccounts = c.getAccounts();
				for (Account a : gettingaccounts) {
					if (!a.getCreatedAt().before(startdate) && !a.getCreatedAt().after(enddate)) {
						Account account = new Account(a.getAccountNumber(), a.getAccountBalance(), a.getCreatedAt(),
								a.getAccountype(), a.getTransaction(), a.getCustomer());
						newdata.add(account);
						accountcount++;

					}
				}
			}

			branchResponse res = new branchResponse();
			res.setCount(accountcount);
			res.setAccountsdata(newdata);
			return new ResponseEntity(res, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object is not found");
	}

	public ResponseEntity<Double> getTotalDeposits(int branchId) {
		Branch branch = branchdao.findBranch(branchId);
		double totaldepositamount = 0;
		if (branch != null) {
			List<Customer> customer = branch.getCustomer();
			for (Customer c : customer) {
				List<Account> accounts = c.getAccounts();
				for (Account a : accounts) {
					List<Transaction> transaction = a.getTransaction();
					for (Transaction t : transaction) {
						if (t.getType() == TransactionType.credit)
							totaldepositamount += t.getTransactionAmount();
					}
				}
			}

			return new ResponseEntity<Double>(totaldepositamount, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity<Double> getTotalWithdrawalsByBranch(int branchId) {
		Branch branch = branchdao.findBranch(branchId);
		double totalwithdrawamount = 0;
		if (branch != null) {
			List<Customer> customer = branch.getCustomer();
			for (Customer c : customer) {
				List<Account> accounts = c.getAccounts();
				for (Account a : accounts) {
					List<Transaction> transaction = a.getTransaction();
					for (Transaction t : transaction) {
						if (t.getType() == TransactionType.debit)
							totalwithdrawamount += t.getTransactionAmount();
					}
				}
			}
			return new ResponseEntity<Double>(totalwithdrawamount, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object not found");

	}

	public ResponseEntity getNumberOfTransactions(int brachId) {
		Branch branch = branchdao.findBranch(brachId);
		List<Transaction> newdatatransaction = new ArrayList<Transaction>();
		int transactioncount = 0;
		if (branch != null) {

			List<Customer> customer = branch.getCustomer();
			for (Customer c : customer) {
				List<Account> accounts = c.getAccounts();
				for (Account a : accounts) {
					List<Transaction> transaction = a.getTransaction();
					for (Transaction t : transaction) {
						newdatatransaction.add(t);
						transactioncount++;
					}
				}
			}
			totalTransactionsbyday f = new totalTransactionsbyday();
			f.setCount(transactioncount);
			f.setTransactions(newdatatransaction);

			return new ResponseEntity(f, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity<Customer> findCustomerByMobileNumber(long mobilenum) {
		Customer customerByMobileNumber = customerdao.findCustomerByMobileNumber(mobilenum);
		if (customerByMobileNumber != null)
			return new ResponseEntity<Customer>(customerByMobileNumber, HttpStatus.FOUND);
		else
			throw new CustomerNotFoundException("customer object not found");
	}

	public ResponseEntity<Customer> findCustomerByEmail(String email) {
		Customer customerByEmail = customerdao.findCustomerByEmail(email);
		if (customerByEmail != null)
			return new ResponseEntity<Customer>(customerByEmail, HttpStatus.FOUND);
		else
			throw new CustomerNotFoundException("customer object not found");
	}

	public ResponseEntity<Employee> findEmployeesByRole(EmployeeType employeeType) {
		Employee employeesByRole = employeedao.findEmployeesByRole(employeeType);
		if (employeesByRole != null)
			return new ResponseEntity<Employee>(employeesByRole, HttpStatus.FOUND);
		else
			throw new EmployeeNotFoundException("employee object not found");
	}

	public ResponseEntity<List<Account>> findAccountsByCustomerName(String fname, String lname) {
		Customer byCustomerName = customerdao.findByCustomerName(fname, lname);
		List<Account> accounts = byCustomerName.getAccounts();
		if (!accounts.isEmpty())
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.FOUND);
		else
			throw new AccounNotFoundException("customer doesn't having any account ");
	}

	public ResponseEntity<Account> findAccountByAccountNumber(int accountnum) {
		Account account = accountdao.findAccount(accountnum);
		if (account != null)
			return new ResponseEntity<Account>(account, HttpStatus.FOUND);
		else
			throw new AccounNotFoundException("account object not found");
	}

	public ResponseEntity<List<Account>> filterAccountsByBalanceRange(int branchId, double minrange, double maxrange) {
		Branch branch = branchdao.findBranch(branchId);
		List<Account> newdata = new ArrayList<Account>();
		if (branch != null) {
			List<Customer> customer = branch.getCustomer();
			if (!customer.isEmpty()) {
				for (Customer c : customer) {
					List<Account> accounts = c.getAccounts();
					for (Account a : accounts) {
						if (a.getAccountBalance() >= minrange && a.getAccountBalance() <= maxrange)
							newdata.add(a);
					}
				}
			} else
				throw new AccounNotFoundException("customer details not found");
			return new ResponseEntity<List<Account>>(newdata, HttpStatus.FOUND);
		} else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity<List<Account>> filterAccountsByAccountType(int branchid, AccountType accountType) {
		Branch branch = branchdao.findBranch(branchid);
		List<Account> newdata = new ArrayList<Account>();
		if (branch != null) {
			List<Customer> customer = branch.getCustomer();
			if (!customer.isEmpty()) {
				for (Customer c : customer) {
					List<Account> accounts = c.getAccounts();
					for (Account a : accounts) {
						if (a.getAccountype() == accountType)
							newdata.add(a);

					}
				}
			} else
				throw new CustomerNotFoundException("customer details not found");
			return new ResponseEntity<List<Account>>(newdata, HttpStatus.FOUND);
		} else
			throw new BranchNotFoundException("branch object not found");
	}

	@Getter
	@Setter
	public class branchResponse {

		Integer count;
		List<Account> accountsdata;
	}

	@Getter
	@Setter
	public class totalTransactionsbyday {
		int count;
		List<Transaction> transactions;
	}
}
