package com.example.domain.cheque;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.example.domain.basic.MainDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Entity
@Table(name = "TBL_BOUNCE_RECORD")
public class BounceRecord extends MainDomain {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHEQUE_ID", nullable = false, updatable = false)
	private Cheque cheque;

	@Column(name = "DATE", nullable = false, updatable = false)
	private LocalDate date;

	@Column(name = "REASON", updatable = false)
	private String reason;

}
