package com.bank.project.ProjectBank.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@NotNull(message = "customerFirstName not null")
	@NotEmpty(message = "customerFirstName not empty")
	private String customerFirstName;
	@NotNull(message = "customerLastName not null")
	@NotEmpty(message = "customerLastName not empty")
	private String customerLastName;
	@NotNull(message = "Date of birth is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String customerdob;
	@NotNull(message = "customerEmail not null")
	@NotEmpty(message = "customerEmail not empty")
	@Email
	private String customerEmail;
	@Min(value = 6000000000l, message = "invalid contact")
	@Max(value = 9999999999l, message = "invalid contact")
	private long customerContact;
	@NotNull(message = "customerAddress not null")
	@NotEmpty(message = "customerAddress not empty")
	private String customerAddress;
    
	@NotNull(message = "customerage not null")
	@NotEmpty(message = "customerage not empty")
	private String customerage;
	
	@NotNull(message = "customergender not null")
	@NotEmpty(message = "customergender not empty")
	private String customergender;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Account> accounts;

	@ManyToOne
	@JsonIgnoreProperties("branch")
	private Branch branch;

	@ManyToOne
	private Employee createdBy;
}