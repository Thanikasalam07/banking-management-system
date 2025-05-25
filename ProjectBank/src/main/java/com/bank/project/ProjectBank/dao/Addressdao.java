package com.bank.project.ProjectBank.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bank.project.ProjectBank.Repository.AddressRepository;
import com.bank.project.ProjectBank.dto.Address;

@Repository
public class Addressdao {

	@Autowired
	AddressRepository addressrepo;

	public Address saveAddress(Address address) {
		return addressrepo.save(address);
	}

	public Address findAddressbyId(int id) {
		Optional<Address> data = addressrepo.findById(id);
		if (data.isPresent())
			return data.get();
		else
			return null;
	}

	public Address deleteAddress(int id) {
		Address address = findAddressbyId(id);
		if (address != null) {
			addressrepo.delete(address);
			return address;
		} else
			return null;
	}

	public Address updateAddress(int id, Address address) {
		Address data = findAddressbyId(id);
		if (data != null) {
			address.setAddressId(id);
			return addressrepo.save(address);

		} else
			return null;
	}
}
