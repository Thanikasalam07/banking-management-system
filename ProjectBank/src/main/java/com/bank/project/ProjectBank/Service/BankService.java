package com.bank.project.ProjectBank.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.Exception.BankNotFoundException;
import com.bank.project.ProjectBank.Exception.BranchNotFoundException;
import com.bank.project.ProjectBank.Exception.ManagerNotFoundException;
import com.bank.project.ProjectBank.dao.Bankdao;
import com.bank.project.ProjectBank.dao.Branchdao;
import com.bank.project.ProjectBank.dao.Managerdao;
import com.bank.project.ProjectBank.dto.Bank;
import com.bank.project.ProjectBank.dto.Branch;
import com.bank.project.ProjectBank.dto.Manager;


@Component
public class BankService {
	
	@Autowired
	Bankdao bankdao;
	
	@Autowired
	Branchdao branchdao;
	
	@Autowired
	Managerdao managerdao;
	public ResponseEntity<Bank> saveBank(Bank bank)
	{
		Bank data = bankdao.saveBank(bank);
		if(data!=null)
			return new ResponseEntity<Bank>(data,HttpStatus.CREATED);
		else 
			throw new BankNotFoundException("bank object has not been created");
	}
	
	public ResponseEntity<Bank> findBank(int id)
	{
		Bank data = bankdao.findBank(id);
		if(data!=null) return new ResponseEntity<Bank>(data,HttpStatus.FOUND);
		else throw new BankNotFoundException("bank object not found");
	}
	
	public ResponseEntity<Bank> updateBank(int it,Bank bank)
	{
		Bank data = bankdao.updateBank(it, bank);
		if(data!=null) return new ResponseEntity<Bank>(data,HttpStatus.OK);
		else throw new BankNotFoundException("bank object not modified");
	}
	
	public ResponseEntity<Bank> deleteBank(int id)
	{
		Bank data = bankdao.delBank(id);
		if(data!=null) return new ResponseEntity<Bank>(data,HttpStatus.OK);
		else throw new BankNotFoundException("bank object not removed");
	}
	
	
	public ResponseEntity<List<Bank>> getallBank()
	{
		List<Bank> data = bankdao.getAllBnak();
		if(data!=null) return new ResponseEntity<List<Bank>>(data,HttpStatus.FOUND);
		else throw new BankNotFoundException("bank details not found");
	}
	
	
	public ResponseEntity<Bank> findByBankName(String bankname)
	{
		Bank data = bankdao.findByBankName(bankname);
		if(data!=null) return new ResponseEntity<Bank>(data,HttpStatus.FOUND);
		else throw new BankNotFoundException("bank object not found");
	}
	
	public ResponseEntity<List<Branch>> getAllBranches()
	{
		List<Branch> data = branchdao.getAllBranches();
		if(data!=null) return new ResponseEntity<List<Branch>>(data,HttpStatus.FOUND);
		else throw new BranchNotFoundException("branch details not found");
	}
	
	
	
	public ResponseEntity<Branch> findByBranchName(String branchname)
	{
		Branch data = branchdao.findByBranchName(branchname);
		if(data!=null) return new ResponseEntity<Branch>(data,HttpStatus.FOUND);
		else throw new BranchNotFoundException("branch object not found");
	}
	
	
	
	public ResponseEntity<Manager> findByManagerName(String name)
	{
		Manager data = managerdao.findByManagerName(name);
		if(data!=null) return new ResponseEntity<Manager>(data,HttpStatus.FOUND);
		else throw new ManagerNotFoundException("manager object not found");
	}
	
	
	
	
	//saving new branch to bank
	public ResponseEntity<Bank> addNewBranchToBank(int bankId , Branch branch)
	{
		Bank bank = bankdao.findBank(bankId);
		Branch saveBranch = branchdao.saveBranch(branch);
		saveBranch.setBank(bank);
		if(bank!=null)
		{
			if(saveBranch!=null)
			{
				bank.getBranch().add(saveBranch);
				Bank updateBank = bankdao.updateBank(bankId, bank);
				return new ResponseEntity<Bank>(bank,HttpStatus.OK);
			}
			else throw new BranchNotFoundException("branch object not found");
		}
		else throw new BankNotFoundException("bank object not found");
	}
	
	

	public ResponseEntity<Bank> removeBranchToBank(int bankId , int branchId)
	{
		Bank bank = bankdao.findBank(bankId);
		Branch branch = branchdao.findBranch(branchId);
		branch.setBank(null);
		if(bank!=null)
		{
			if(branch!=null)
			{
				bank.getBranch().remove(branch);
				Bank updateBank = bankdao.updateBank(bankId, bank);
				branchdao.deleteBranch(branchId);
				return new ResponseEntity<Bank>(bank,HttpStatus.OK);
				
			}
			else throw new BranchNotFoundException("branch not found ");
				  
		}
		else throw new BankNotFoundException("bank object not found");
	}

	

	public ResponseEntity<Branch> addExistingManagerToBranch(int managerId , int branchId)
	{
		Branch branch = branchdao.findBranch(branchId);
		Manager manager = managerdao.findManager(managerId);
		branch.setManager(manager);
		manager.setBranch(branch);
		if(branch!=null)
		{
			
				if(manager!=null)
				{
					branch.setManager(manager);
					Branch updateBranch = branchdao.updateBranch(branchId, branch);
					return new ResponseEntity<Branch>(branch,HttpStatus.OK);
				}
				else throw new ManagerNotFoundException("manager object not found");
		
		}
		else throw new BranchNotFoundException("branch object not found");
		
	}
	
	
	
	public ResponseEntity<Branch> addNewManagerToBranch(int branchId , Manager manager)
	{
		Branch branch = branchdao.findBranch(branchId);
		Manager data = branch.getManager();
		branch.setManager(manager);
		manager.setBranch(branch);
		if(branch!=null)
		{
			if(data==null)
			{
				branch.setManager(manager);
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				return new ResponseEntity<Branch>(branch,HttpStatus.OK);
			}
			else throw new ManagerNotFoundException("manager object already present");
		}
		else throw new BranchNotFoundException("branch object not found");
	}
	
	public ResponseEntity<Branch> removeManagerToBranch(int managerId , int branchId)
	{
		Manager manager = managerdao.findManager(managerId);
		Branch branch = branchdao.findBranch(branchId);
		branch.setManager(null);
		manager.setBranch(null);
		if(branch!=null)
		{
			if(manager!=null)
			{
				Branch updateBranch = branchdao.updateBranch(branchId, branch);
				managerdao.deleteManager(managerId);
				return new ResponseEntity<Branch>(branch,HttpStatus.OK);
			}
			else throw new ManagerNotFoundException("manager object not found");
		}
		else throw new BranchNotFoundException("branch object not found");
	}
	
	
	
}
