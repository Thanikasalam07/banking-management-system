package com.bank.project.ProjectBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Employee;
import com.bank.project.ProjectBank.dto.EmployeeType;

@Component
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	@Query("select e from Employee e where e.employeetype = ?1")
	public Employee findEmployeesByRole(EmployeeType employeeType);
}
