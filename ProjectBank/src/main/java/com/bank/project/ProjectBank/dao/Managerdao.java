package com.bank.project.ProjectBank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.ManagerRepository;
import com.bank.project.ProjectBank.dto.Manager;

@Repository
public class Managerdao {

	@Autowired
	ManagerRepository managerrepo;

	public Manager saveManager(Manager manager) {
		Manager data = managerrepo.save(manager);
		if (data != null)
			return data;
		else
			return null;
	}

	public Manager findManager(int id) {
		Optional<Manager> data = managerrepo.findById(id);
		if (data.isPresent())
			return data.get();
		else
			return null;
	}

	public Manager deleteManager(int id) {
		Manager data = findManager(id);
		if (data != null) {
			managerrepo.delete(data);
			return data;
		} else
			return null;
	}

	public Manager updateManager(int id, Manager manager) {
		Manager data = findManager(id);
		if (data != null) {
			manager.setManagerId(id);
			return managerrepo.save(manager);
		} else
			return null;
	}

	public List<Manager> getManager() {
		List<Manager> data = managerrepo.findAll();
		if (data != null)
			return data;
		else
			return null;
	}

	public Manager findByManagerName(String name) {
		Manager byManagerName = managerrepo.findByManagerName(name);
		if (byManagerName != null)
			return byManagerName;
		else
			return null;
	}

	public Manager searchManagersByLocation(String location) {
		Manager searchManagersByLocation = managerrepo.searchManagersByLocation(location);
		if (searchManagersByLocation != null)
			return searchManagersByLocation;
		else
			return null;
	}
	
	public Manager findManagerByEmail(String email)
	{
		Manager managerByEmail = managerrepo.findManagerByEmail(email);
		if(managerByEmail!=null) return managerByEmail;
		else return null;
	}
}
