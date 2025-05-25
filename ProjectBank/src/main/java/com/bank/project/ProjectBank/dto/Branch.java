package com.bank.project.ProjectBank.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchId;
	@NotNull(message = "branchName not null")
	@NotEmpty(message = "branchName not empty")
	private String branchName;
	@NotNull(message = "ifsccode not null")
	@NotEmpty(message = "ifsccode not empty")
	private String ifsccode;
	@Min(value = 6000000000l, message = "invalid contact")
	@Max(value = 9999999999l, message = "invalid contact")
	private long branchcontact;
	@NotNull(message = "city not null")
	@NotEmpty(message = "city not empty")
	private String branchcity;
	@NotNull(message = "branchAddress not null")
	@NotEmpty(message = "branchAddress not empty")
	private String branchAddress;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Customer> customer;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employee;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Manager manager;

	@ManyToOne
	@JsonIgnore
	private Bank bank;
}
