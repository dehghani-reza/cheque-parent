package com.example.cheque.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.cheque.dto.basic.MainResponse;
import com.example.domain.cheque.ChequeStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChequeResponse extends MainResponse {

	private String number;

	private Long drawerId;

	private BigDecimal amount;

	private LocalDate issueDate;

	private ChequeStatus status;

	private String payeeId;

	private Long version;
}
