package com.example.account;

import java.time.LocalDate;
import java.util.List;

import com.example.config.InfraConfig;
import com.example.domain.account.repository.AccountRepository;
import com.example.domain.account.service.AccountService;
import com.example.domain.cheque.BounceRecord;
import com.example.domain.cheque.service.BounceRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

	private final InfraConfig config;

	private final BounceRecordService bounceRecordService;

	private final AccountRepository accountRepository;

	@Override
	public boolean eligibleForChequeDrawer(Long accountId) {
		accountRepository.findById(accountId);
		LocalDate now = LocalDate.now();
		List<BounceRecord> list = bounceRecordService
				.findAccountBounceInRange(accountId, now.minusMonths(config.getMaxChequeRangeMonth()), now);
		return list.size() < config.getMaxChequeBounce();
	}
}
