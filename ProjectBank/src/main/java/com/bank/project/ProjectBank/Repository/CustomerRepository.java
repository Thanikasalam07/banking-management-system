package com.bank.project.ProjectBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Customer;

@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	
	@Query("select c from Customer c where c.customerFirstName = ?1 and c.customerLastName = ?2")
	public Customer findByCustomerName(String fname, String lname);

}
