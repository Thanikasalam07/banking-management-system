package com.bank.project.ProjectBank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.BankRepository;
import com.bank.project.ProjectBank.dto.Bank;

@Repository
public class Bankdao {

	@Autowired
	BankRepository BankRepo;
	
	public Bank saveBank(Bank bank)
	{
		return BankRepo.save(bank);
	}
	
	public Bank findBank(int id)
	{
		Optional<Bank> data = BankRepo.findById(id);
		if(data.isPresent())
		{
			return data.get();
		}
		else return null;
	}
	
	public Bank updateBank(int id,Bank bank)
	{
		Bank data = findBank(id);
		if(data!=null)
		{
			bank.setBankId(id);
			return BankRepo.save(bank);
			
		}
		else return null;	
		
	}
	
	public Bank delBank(int id)
	{
		Bank bank = findBank(id);
		if(bank!=null)
		{
			BankRepo.delete(bank);
		    return bank;
		}
		return null;
	}
	
	public List<Bank> getAllBnak()
	{
		 List<Bank> data = BankRepo.findAll();
		 if(data!=null) return data;
		 else return null;
	}
	
	public Bank findByBankName(String bankanme)
	{
		return BankRepo.findByBankName(bankanme);
	}
}
