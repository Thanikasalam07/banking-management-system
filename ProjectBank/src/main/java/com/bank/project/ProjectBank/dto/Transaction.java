package com.bank.project.ProjectBank.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@NotNull(message = "fromAccount not null")
	@NotEmpty(message = "fromAccount not empty")
	private String fromAccount;
	@NotNull(message = "toAccount not null")
	@NotEmpty(message = "toAccount not empty")
	private String toAccount;
	@NotNull(message = "transactiontype is not null")
	private TransactionType type;
	@Positive(message = "transactionAmount should be positive")
	private double transactionAmount;
	@NotNull(message = "LocalDateTime of birth is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date timestamp;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "account_id")
	private Account account;
}
