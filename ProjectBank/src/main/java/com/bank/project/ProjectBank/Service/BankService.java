package com.bank.project.ProjectBank.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Exception.BankNotFoundException;
import com.bank.project.ProjectBank.Exception.BranchNotFoundException;
import com.bank.project.ProjectBank.Exception.ManagerNotFoundException;
import com.bank.project.ProjectBank.dao.Accountdao;
import com.bank.project.ProjectBank.dao.Bankdao;
import com.bank.project.ProjectBank.dao.Branchdao;
import com.bank.project.ProjectBank.dao.Managerdao;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.AccountType;
import com.bank.project.ProjectBank.dto.Bank;
import com.bank.project.ProjectBank.dto.Branch;
import com.bank.project.ProjectBank.dto.Customer;
import com.bank.project.ProjectBank.dto.Manager;
import com.bank.project.ProjectBank.dto.Transaction;
import com.bank.project.ProjectBank.dto.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Component
public class BankService {

	@Autowired
	Bankdao bankdao;

	@Autowired
	Branchdao branchdao;

	@Autowired
	Managerdao managerdao;

	@Autowired
	Accountdao accountdao;

	public ResponseEntity<Bank> saveBank(Bank bank) {

		if (bank != null) {
			List<Branch> branch = bank.getBranch();
			for (Branch b : branch) {
				b.setBank(bank);
			}
			Bank saveBank = bankdao.saveBank(bank);
			return new ResponseEntity<Bank>(saveBank, HttpStatus.CREATED);
		} else
			throw new BankNotFoundException("bank object has not been created");
	}

	public ResponseEntity<Bank> findBank(int id) {
		Bank data = bankdao.findBank(id);
		if (data != null)
			return new ResponseEntity<Bank>(data, HttpStatus.FOUND);
		else
			throw new BankNotFoundException("bank object not found");
	}

	public ResponseEntity<Bank> updateBank(int id, Bank bank) {
		Bank bank2 = bankdao.findBank(id);

		if (bank2 != null) {
			List<Branch> branchdata = bank2.getBranch();
			bank.setBranch(branchdata);
			bank.setBankId(id);
			Bank updateBank = bankdao.updateBank(id, bank);

			return new ResponseEntity<Bank>(updateBank, HttpStatus.OK);
		} else
			throw new BankNotFoundException("bank object not modified");
	}

	public ResponseEntity<Bank> deleteBank(int id) {
		Bank data = bankdao.delBank(id);
		if (data != null)
			return new ResponseEntity<Bank>(data, HttpStatus.OK);
		else
			throw new BankNotFoundException("bank object not removed");
	}

	public ResponseEntity<List<Bank>> getallBank() {
		List<Bank> data = bankdao.getAllBnak();
		if (data != null)
			return new ResponseEntity<List<Bank>>(data, HttpStatus.FOUND);
		else
			throw new BankNotFoundException("bank details not found");
	}

	public ResponseEntity<Bank> findByBankName(String bankname) {
		Bank data = bankdao.findByBankName(bankname);
		if (data != null)
			return new ResponseEntity<Bank>(data, HttpStatus.FOUND);
		else
			throw new BankNotFoundException("bank object not found");
	}

	public ResponseEntity<List<Branch>> getAllBranches() {
		List<Branch> data = branchdao.getAllBranches();
		if (data != null)
			return new ResponseEntity<List<Branch>>(data, HttpStatus.FOUND);
		else
			throw new BranchNotFoundException("branch details not found");
	}

	public ResponseEntity<Branch> findByBranchName(String branchname) {
		Branch data = branchdao.findByBranchName(branchname);
		if (data != null)
			return new ResponseEntity<Branch>(data, HttpStatus.FOUND);
		else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity<Manager> findByManagerName(String name) {
		Manager data = managerdao.findByManagerName(name);
		if (data != null)
			return new ResponseEntity<Manager>(data, HttpStatus.FOUND);
		else
			throw new ManagerNotFoundException("manager object not found");
	}

	// saving new branch to bank
	public ResponseEntity<Bank> addNewBranchToBank(int bankId, Branch branch) {
		Bank bank = bankdao.findBank(bankId);
		Branch saveBranch = branchdao.saveBranch(branch);
		saveBranch.setBank(bank);
		if (bank != null) {
			if (saveBranch != null) {
				bank.getBranch().add(saveBranch);
				Bank updateBank = bankdao.updateBank(bankId, bank);
				return new ResponseEntity<Bank>(bank, HttpStatus.OK);
			} else
				throw new BranchNotFoundException("branch object not found");
		} else
			throw new BankNotFoundException("bank object not found");
	}

	public ResponseEntity<Bank> removeBranchToBank(int bankId, int branchId) {
		Bank bank = bankdao.findBank(bankId);
		Branch branch = branchdao.findBranch(branchId);
		branch.setBank(null);
		if (bank != null) {
			if (branch != null) {
				bank.getBranch().remove(branch);
				Bank updateBank = bankdao.updateBank(bankId, bank);
				branchdao.deleteBranch(branchId);
				return new ResponseEntity<Bank>(bank, HttpStatus.OK);

			} else
				throw new BranchNotFoundException("branch not found ");

		} else
			throw new BankNotFoundException("bank object not found");
	}

	public ResponseEntity<Branch> addExistingManagerToBranch(int managerId, int branchId) {
		Branch branch = branchdao.findBranch(branchId);
		Manager manager = managerdao.findManager(managerId);
		branch.setManager(manager);
		manager.setBranch(branch);
		if (branch != null) {

			if (manager != null) {
				branch.setManager(manager);
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				return new ResponseEntity<Branch>(branch, HttpStatus.OK);
			} else
				throw new ManagerNotFoundException("manager object not found");

		} else
			throw new BranchNotFoundException("branch object not found");

	}

	public ResponseEntity<Branch> addNewManagerToBranch(int branchId, Manager manager) {
		Branch branch = branchdao.findBranch(branchId);
		Manager data = branch.getManager();
		branch.setManager(manager);
		manager.setBranch(branch);
		if (branch != null) {
			if (data == null) {
				branch.setManager(manager);
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				return new ResponseEntity<Branch>(branch, HttpStatus.OK);
			} else
				throw new ManagerNotFoundException("manager object already present");
		} else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity<Branch> removeManagerToBranch(int managerId, int branchId) {
		Manager manager = managerdao.findManager(managerId);
		Branch branch = branchdao.findBranch(branchId);
		branch.setManager(null);
		manager.setBranch(null);
		if (branch != null) {
			if (manager != null) {
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				managerdao.deleteManager(managerId);
				return new ResponseEntity<Branch>(branch, HttpStatus.OK);
			} else
				throw new ManagerNotFoundException("manager object not found");
		} else
			throw new BranchNotFoundException("branch object not found");
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

			accountResponse res = new accountResponse();
			res.setCount(accountcount);
			res.setAccountsdata(newdata);
			return new ResponseEntity(res, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object is not found");
	}

	public ResponseEntity<Double> getTotalDepositsByBranch(int branchId) {
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

	public ResponseEntity getNumberOfTransactionsByBranch(int brachId) {
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
			transactionresponse f = new transactionresponse();
			f.setCount(transactioncount);
			f.setTransactions(newdatatransaction);

			return new ResponseEntity(f, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity getTotalDepositsBetweenDates(LocalDateTime startdate, LocalDateTime enddate, int branchId) {
		Branch branch = branchdao.findBranch(branchId);
		List<Transaction> deposittransactions = new ArrayList<Transaction>();
		int transactioncount = 0;

		if (branch != null) {
			List<Customer> customer = branch.getCustomer();
			for (Customer c : customer) {
				List<Account> accounts = c.getAccounts();
				for (Account a : accounts) {
					List<Transaction> transaction = a.getTransaction();
					for (Transaction t : transaction) {
						if (t.getType() == TransactionType.credit && !t.getTimestamp().isBefore(startdate)
								&& !t.getTimestamp().isAfter(enddate)) {
							transactioncount++;
							deposittransactions.add(t);
						}
					}
				}
			}

			transactionresponse data = new transactionresponse();
			data.setCount(transactioncount);
			data.setTransactions(deposittransactions);

			return new ResponseEntity(data, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object not found");
	}

	public ResponseEntity accountsOpenedInSingleDay(int branchId, Date date) {
		Branch branch = branchdao.findBranch(branchId);
		List<Account> newdata = new ArrayList<Account>();
		int accountcount = 0;
		if (branch != null) {
			List<Customer> customer = branch.getCustomer();
			for (Customer c : customer) {
				List<Account> accounts = c.getAccounts();
				for (Account a : accounts) {

					if (a.getCreatedAt().equals(date)) {
						newdata.add(a);
						accountcount++;
					}
				}
			}

			accountResponse a = new accountResponse();
			a.setAccountsdata(newdata);
			a.setCount(accountcount);

			return new ResponseEntity(a, HttpStatus.OK);
		} else
			throw new BranchNotFoundException("branch object not found(check the branch id)");
	}

	public ResponseEntity<Bank> findBankByCityAndState(String city, String state) {
		Bank bankByStateAndCity = bankdao.findBankByStateAndCity(city, state);
		if (bankByStateAndCity != null)
			return new ResponseEntity<Bank>(bankByStateAndCity, HttpStatus.FOUND);
		else
			throw new BankNotFoundException("bank object not found");
	}

	public ResponseEntity<Bank> findBranchByIFSCCode(String ifsccode) {
		Bank branchByIFSCCode = bankdao.findBranchByIFSCCode(ifsccode);
		if (branchByIFSCCode != null)
			return new ResponseEntity<Bank>(branchByIFSCCode, HttpStatus.FOUND);
		else
			throw new BankNotFoundException("bank object not found");
	}

	public ResponseEntity<Manager> searchManagersByLocation(String location) {
		Manager data = managerdao.searchManagersByLocation(location);
		if (data != null)
			return new ResponseEntity<Manager>(data, HttpStatus.FOUND);
		else
			return null;

	}
	
	public ResponseEntity<Manager> findManagerByEmail(String email)
	{
		Manager managerByEmail = managerdao.findManagerByEmail(email);
		if(managerByEmail!=null) return new ResponseEntity<Manager>(managerByEmail,HttpStatus.FOUND);
		else throw new ManagerNotFoundException("manager object not found");
	}

	@Getter
	@Setter
	public class accountResponse {

		Integer count;
		List<Account> accountsdata;
	}

	@Getter
	@Setter
	public class transactionresponse {
		int count;
		List<Transaction> transactions;
	}
}
