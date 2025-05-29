package com.bank.project.ProjectBank.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	@NotNull(message = "bankcode should not null")
	@NotEmpty(message = "bankcode not empty")
	private String bankifsccode;
	@NotNull(message = "name should not null")
	@NotEmpty(message = "name not empty")
	private String bankName;
	@NotEmpty(message = "headoffice not empty")
	@NotNull(message = "headoffice not null")
	private String headquartersAddress;
	@Min(value = 6000000000l, message = "invalid contact")
	@Max(value = 9999999999l, message = "invalid contact")
	private long bankContactNumber;
	@NotNull(message = "bankstate should not null")
	@NotEmpty(message = "bankstate not empty")
	private String bankstate;
	@NotNull(message = "bankcity should not null")
	@NotEmpty(message = "bankcity not empty")
	private String bankcity;

	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Branch> branch;

}
