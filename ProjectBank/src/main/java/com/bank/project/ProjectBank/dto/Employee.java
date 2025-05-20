package com.bank.project.ProjectBank.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	@NotNull(message = "employeeFirstname not null")
	@NotEmpty(message =  "employeeFirstname not empty")
	private  String employeeFirstname;
	@NotNull(message = "employeeLastname not null")
	@NotEmpty(message =  "employeeLastname not empty")
	private String employeeLastname;
	@NotNull(message = "employeePosition not null")
	@NotEmpty(message =  "employeePosition not empty")
	private String employeePosition;
	@NotNull(message = "Date of emploteeHiredate is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date emploteeHiredate;
	@Positive(message = "salary shoud be positive")
	private double salary;
	@NotNull(message = "employeeEmail not null")
	@NotEmpty(message =  "employeeEmail not empty")
	private String employeeEmail;
	@Min(value = 6000000000l,message = "invalid contact") 
    @Max(value = 9999999999l,message = "invalid contact")
	private long employeeContact;
	
	    @Enumerated(EnumType.STRING)
	    private EmployeeType employeetype;

	    @OneToOne(cascade = CascadeType.ALL)
	    private Address address;

	    @ManyToOne
	    @JsonIgnore
	    private Branch assignedbranch;
	
	
}
