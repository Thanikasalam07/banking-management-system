package com.bank.project.ProjectBank.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.BankRepository;
import com.bank.project.ProjectBank.dto.Account;
import com.bank.project.ProjectBank.dto.Bank;
import com.bank.project.ProjectBank.dto.Branch;

@Repository
public class Bankdao {

	@Autowired
	BankRepository BankRepo;

	public Bank saveBank(Bank bank) {
		return BankRepo.save(bank);
	}

	public Bank findBank(int id) {
		Optional<Bank> data = BankRepo.findById(id);
		if (data.isPresent()) {
			return data.get();
		} else
			return null;
	}

	public Bank updateBank(int id, Bank bank) {
		Bank data = findBank(id);
		if (data != null) {
			bank.setBankId(id);
			return BankRepo.save(bank);

		} else
			return null;

	}

	public Bank delBank(int id) {
		Bank bank = findBank(id);

		if (bank != null) {
			bank.getBranch().clear();

			BankRepo.save(bank);
			BankRepo.delete(bank);
			return bank;
		} else
			return null;
	}

	public List<Bank> getAllBnak() {
		List<Bank> data = BankRepo.findAll();
		if (data != null)
			return data;
		else
			return null;
	}

	public Bank findByBankName(String bankanme) {
		Bank byBankName = BankRepo.findByBankName(bankanme);
		if (byBankName != null)
			return byBankName;
		else
			return null;
	}

	public Bank findBankByStateAndCity(String city, String state) {

		Bank byStateAndCity = BankRepo.findByStateAndCity(city, state);
		if (byStateAndCity != null)
			return byStateAndCity;
		else
			return null;
	}

	public Bank findBranchByIFSCCode(String ifsccode) {
		Bank branchByIFSCCode = BankRepo.findBranchByIFSCCode(ifsccode);
		if (branchByIFSCCode != null)
			return branchByIFSCCode;
		else
			return null;
	}

}
