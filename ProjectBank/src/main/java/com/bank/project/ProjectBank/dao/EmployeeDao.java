package com.bank.project.ProjectBank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.EmployeeRepository;
import com.bank.project.ProjectBank.dto.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	EmployeeRepository employeerepo;

	public Employee saveEmployee(Employee employee) {
		return employeerepo.save(employee);
	}

	public Employee findEmployeebyId(int id) {
		Optional<Employee> data = employeerepo.findById(id);
		if (data.isPresent())
			return data.get();
		else
			return null;

	}

	public Employee deleteEmployee(int id) {
		Employee data = findEmployeebyId(id);
		if (data != null) {
			employeerepo.delete(data);
			return data;
		} else
			return null;
	}

	public Employee updateEmployee(int id, Employee employee) {
		Employee data = findEmployeebyId(id);
		if (data != null) {
			employee.setEmployeeId(id);
			Employee save = employeerepo.save(employee);
			return save;
		} else
			return null;
	}

	public List<Employee> getAllEmployee() {
		List<Employee> data = employeerepo.findAll();
		if (data != null)
			return data;
		else
			return null;
	}
}
