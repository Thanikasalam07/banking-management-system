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

	   
	    @NotEmpty(message = "senderAccount not empty")
	    @NotNull(message = "senderAccount not null")
	    private String senderAccount; 

	    @NotEmpty(message = "receiverAccount not empty")
	    @NotNull(message = "receiverAccount not null")
	    private String receiverAccount; 

	    @NotNull(message = "Transaction type is required")
	    private TransactionType type; 

	    @Positive(message = "Transaction amount must be positive")
	    private double transactionAmount;

	    @NotNull
	    private LocalDateTime timestamp;; 

	    private String status;
	
     
	    

	
}
