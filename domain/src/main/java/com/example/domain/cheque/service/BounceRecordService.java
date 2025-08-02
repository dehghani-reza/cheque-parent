package com.example.domain.cheque.service;

import java.time.LocalDate;
import java.util.List;

import com.example.domain.cheque.BounceRecord;

public interface BounceRecordService {

	List<BounceRecord> findAccountBounceInRange(Long drawerId, LocalDate from, LocalDate to);

}
