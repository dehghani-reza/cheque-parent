package com.example.domain.account;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import com.example.domain.basic.MainDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_ACCOUNT")
public class Account extends MainDomain {

	@Column(name = "BALANCE", nullable = false)
	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private AccountStatus status;

	@Version
	private Long version;

}
