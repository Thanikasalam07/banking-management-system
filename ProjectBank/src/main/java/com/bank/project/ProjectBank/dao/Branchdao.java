package com.bank.project.ProjectBank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.BranchRepository;
import com.bank.project.ProjectBank.dto.Branch;

@Repository
public class Branchdao {

	@Autowired
	BranchRepository branchrepo;

	public Branch saveBranch(Branch branch) {
		Branch save = branchrepo.save(branch);
		if (save != null)
			return save;
		return null;
	}

	public Branch findBranch(int id) {
		Optional<Branch> data = branchrepo.findById(id);
		if (data.isPresent())
			return data.get();
		else
			return null;
	}

	public Branch updateBranch(int id, Branch branch) {
		Branch data = findBranch(id);
		if (data != null) {
			branch.setBranchId(id);
			return branchrepo.save(branch);
		} else
			return null;
	}

	public Branch deleteBranch(int id) {
		Branch data = findBranch(id);
		if (data != null) {
			branchrepo.delete(data);
			return data;
		} else
			return null;

	}

	public List<Branch> getAllBranches() {
		List<Branch> data = branchrepo.findAll();

		if (data != null)
			return data;
		else
			return null;
	}

	public Branch findByBranchName(String branchname) {
		return branchrepo.findByBranchName(branchname);
	}
}
