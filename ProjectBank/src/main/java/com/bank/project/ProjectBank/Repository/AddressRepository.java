package com.bank.project.ProjectBank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.bank.project.ProjectBank.dto.Address;

@Component
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
