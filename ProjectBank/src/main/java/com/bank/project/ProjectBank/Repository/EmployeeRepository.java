package com.bank.project.ProjectBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Employee;

@Component
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

	
}
