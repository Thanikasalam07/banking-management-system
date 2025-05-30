package com.bank.project.ProjectBank.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNumber;
	@Positive(message = "balance should be positive")
	private double accountBalance;
	@NotNull(message = "Date of birth is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@NotNull(message = "accountType is not null")
	private AccountType accountype;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Transaction> transaction;

	@ManyToOne
	@JsonIgnore
	Customer customer;
	
	@OneToMany(mappedBy = "senderAccount")
	private List<Transaction> sentTransactions;

	@OneToMany(mappedBy = "receiverAccount")
	private List<Transaction> receivedTransactions;

	public Account(int accountNumber, @Positive(message = "balance should be positive") double accountBalance,
			@NotNull(message = "Date of birth is required") Date createdAt,
			@NotNull(message = "accountType is not null") AccountType accountype, List<Transaction> transaction,
			Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.createdAt = createdAt;
		this.accountype = accountype;
		this.transaction = transaction;
		this.customer = customer;
	}

	public Account() {
		super();
	}

}
