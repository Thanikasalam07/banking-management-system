package com.bank.project.ProjectBank.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	@NotNull(message = "street not null")
	@NotEmpty(message = "street not empty")
	private String street;
	@NotNull(message = "city not null")
	@NotEmpty(message = "city not empty")
	private String city;
	@NotNull(message = "state not null")
	@NotEmpty(message = "state not empty")
	private String state;
	@NotNull(message = "pincode not null")
	@NotEmpty(message = "pincode not empty")
	private String pincode;;
	@NotNull(message = "country not null")
	@NotEmpty(message = "country not empty")
	private String country;

}
