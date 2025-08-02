package com.example.cheque.service;

import java.time.LocalDate;
import java.util.List;

import com.example.domain.cheque.BounceRecord;
import com.example.domain.cheque.repository.BounceRecordRepository;
import com.example.domain.cheque.service.BounceRecordService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BounceRecordServiceImpl implements BounceRecordService {

	private final BounceRecordRepository bounceRecordRepository;

	@Override
	public List<BounceRecord> findAccountBounceInRange(Long drawerId, LocalDate from, LocalDate to) {
		return bounceRecordRepository.findByCheque_Drawer_IdAndDateAfterAndDateBefore(drawerId, from, to);
	}
}
