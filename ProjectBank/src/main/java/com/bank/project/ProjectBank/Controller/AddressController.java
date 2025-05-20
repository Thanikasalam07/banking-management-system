package com.bank.project.ProjectBank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.project.ProjectBank.Service.AddressService;
import com.bank.project.ProjectBank.dto.Address;

public class AddressController {

	@Autowired
	AddressService addressser;
	
	@PostMapping
	public ResponseEntity<Address> saveAdress( @RequestBody Address address)
	{
		return addressser.saveAddress(address);
	}
	
	@GetMapping
	public ResponseEntity<Address> findAddressById(@RequestParam int id)
	{
		 return addressser.findAddressById(id);
	}
	
	@DeleteMapping
	public ResponseEntity<Address> deleteAddress(@RequestParam int id)
	{
		return addressser.deleteAddress(id);
	}
	
	@PutMapping
	public ResponseEntity<Address> updateAddress(@RequestParam int id,@RequestBody Address address)
	{
		return addressser.updateAddress(id, address);
	}  
}
