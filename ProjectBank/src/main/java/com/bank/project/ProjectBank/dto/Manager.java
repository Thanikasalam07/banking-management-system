package com.bank.project.ProjectBank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	@NotNull(message = "managerName not null")
	@NotEmpty(message = "managerName not empty")
	private String managerName;
	@NotNull(message = "managerEmail not null")
	@NotEmpty(message = "managerEmail not empty")
	private String managerEmail;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", 
	            message = "Password must contain at least one digit, one lowercase and one uppercase letter")
	@Size(min = 8, message = "Password must be at least 8 characters")
	@NotNull(message = "managerPassword not null")
	private String managerPassword;
	@NotNull(message = "managerContact not null")
	@Min(value = 6000000000l,message = "invalid contact") 
    @Max(value = 9999999999l,message = "invalid contact")
	private Long managerContact;

	@OneToOne
	@JsonIgnore
	private Branch branch;
}
