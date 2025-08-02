package com.example.domain.cheque;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import com.example.domain.account.Account;
import com.example.domain.basic.MainDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_CHEQUE")
public class Cheque extends MainDomain {

	@Column(name = "NUMBER", nullable = false, unique = true, updatable = false)
	private String number;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "DRAWER_ID", nullable = false, updatable = false)
	private Account drawer;

	@Column(name = "AMOUNT", nullable = false, updatable = false)
	private BigDecimal amount;

	@Column(name = "DATE", nullable = false, updatable = false)
	private LocalDate issueDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private ChequeStatus status;

	@Column(name = "PAYEE_ID", nullable = false, updatable = false)
	private String payeeId;

	@Version
	private Long version;
}
