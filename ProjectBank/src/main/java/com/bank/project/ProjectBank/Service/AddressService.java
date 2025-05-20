package com.bank.project.ProjectBank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Exception.AddressNotFoundException;
import com.bank.project.ProjectBank.dao.Addressdao;
import com.bank.project.ProjectBank.dto.Address;

@Component
public class AddressService {

	@Autowired
	Addressdao addressdao;
	
	public ResponseEntity<Address> saveAddress(Address address)
	{
		Address data = addressdao.saveAddress(address);
		if(data!=null) return new ResponseEntity<Address>(data,HttpStatus.CREATED);
		else throw new AddressNotFoundException("address object has not been craeted");
	}
	
	public ResponseEntity<Address> findAddressById(int id)
	{
		Address data = addressdao.findAddressbyId(id);
		if(data!=null) return new ResponseEntity<Address>(data,HttpStatus.FOUND);
		else throw new AddressNotFoundException("address object not found");
	}
	
	public ResponseEntity<Address> updateAddress(int id , Address address)
	{
		Address data = addressdao.updateAddress(id, address);
		if(data!=null) return new ResponseEntity<Address>(data,HttpStatus.OK);
		else throw new AddressNotFoundException("address object not modified");
	}
	
	public ResponseEntity<Address> deleteAddress(int id)
	{
		Address data = addressdao.deleteAddress(id);
		if(data!=null) return new ResponseEntity<Address>(data,HttpStatus.OK);
		else throw new AddressNotFoundException("address object not removed ");
	}
}
