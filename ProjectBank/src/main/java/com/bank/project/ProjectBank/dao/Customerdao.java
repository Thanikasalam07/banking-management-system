package com.bank.project.ProjectBank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.CustomerRepository;
import com.bank.project.ProjectBank.dto.Customer;

@Repository
public class Customerdao {

	@Autowired
	CustomerRepository customerrepo;
	
	public Customer saveCustomer(Customer customer) 
	{
		Customer save = customerrepo.save(customer);
		if(save!=null) return save;
		else return null;
	}
	
	public Customer findCustomer(int id)
	{
		Optional<Customer> data = customerrepo.findById(id);
		if(data.isPresent()) return data.get();
		else return null;
	}
	
	public Customer deleteCustomer(int id)
	{
		Customer data = findCustomer(id);
		if(data!=null) 
		{
			customerrepo.delete(data);
			return data;
		}
		else return null;
	}
	
	public Customer updateCustomer(int id,Customer customer)
	{
		Customer data = findCustomer(id);
		if(data!=null)
		{
			customer.setCustomerId(id);
			return customerrepo.save(customer);
		}
		else return null;
	}
	
	public List<Customer> getAllCustomer()
	{
		List<Customer> data = customerrepo.findAll();
		if(data!=null)
			return data;
		else return null;
	}
	
	public Customer findByCustomerName(String fname , String lname)
	{
		Customer data = customerrepo.findByCustomerName(fname, lname);
		if(data!=null) return data;
		else return null;
	}
}
