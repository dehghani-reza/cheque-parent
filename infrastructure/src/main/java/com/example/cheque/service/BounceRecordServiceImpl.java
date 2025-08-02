package com.example.cheque.service;

import java.time.LocalDate;
import java.util.List;

import com.example.config.InfraConfig;
import com.example.domain.cheque.BounceRecord;
import com.example.domain.cheque.repository.BounceRecordRepository;
import com.example.domain.cheque.service.BounceRecordService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BounceRecordServiceImpl implements BounceRecordService {

	private final InfraConfig config;

	private final BounceRecordRepository bounceRecordRepository;

	@Override
	public List<BounceRecord> findAccountBounceInRange(Long drawerId, LocalDate from, LocalDate to) {
		return bounceRecordRepository.findByCheque_Drawer_IdAndDateAfterAndDateBefore(drawerId, from, to);
	}

	@Override
	public boolean isAccountBlockedBecauseOfBounce(Long accountId) {
		LocalDate now = LocalDate.now();
		return this.findAccountBounceInRange(accountId, now.minusMonths(config.getMaxChequeRangeMonth()), now)
				.size() < config.getMaxChequeBounce();
	}


	@Override
	public BounceRecord save(BounceRecord insufficientMoney) {
		return bounceRecordRepository.save(insufficientMoney);
	}

}
