package com.bank.project.ProjectBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Manager;

@Component
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.managerName = ?1")
	public Manager findByManagerName(String name);
	
	@Query("select m from Manager m where m.location =?1")
	public Manager searchManagersByLocation(String location);
	
	@Query("select m from Manager m where m.managerEmail =?1")
	public Manager findManagerByEmail(String email);

}
