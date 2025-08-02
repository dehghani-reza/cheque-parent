package com.example.cheque.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class IssueChequeRequest {

	@NotBlank
	private String number;

	@NotBlank
	private Long drawerId;

	@Positive
	private BigDecimal amount;

	@FutureOrPresent
	private LocalDate issueDate;

	@Pattern(regexp = "^(\\d){10}$")
	private String payeeId;
}
