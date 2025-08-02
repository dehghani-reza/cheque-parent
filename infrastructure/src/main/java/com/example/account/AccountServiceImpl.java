package com.example.account;

import java.math.BigDecimal;

import com.example.domain.account.Account;
import com.example.domain.account.AccountStatus;
import com.example.domain.account.repository.AccountRepository;
import com.example.domain.account.service.AccountService;
import com.example.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account findById(Long accountId) {
		return accountRepository.findById(accountId)
				.orElseThrow(() -> new BusinessException("ACCOUNT NOT EXISTS"));
	}

	@Override
	public void block(Long accountId) {
		Account account = this.findById(accountId);
		account.setStatus(AccountStatus.BLOCKED);
		accountRepository.save(account);
	}

	@Override
	public void subtractMoney(Long accountId, BigDecimal amount) {
		Account account = this.findById(accountId);
		checkSubtraction(account, amount);
		account.setBalance(account.getBalance()
				.subtract(amount));
		accountRepository.save(account);
	}

	@Override
	public void checkSubtraction(Long accountId, BigDecimal amount) {
		checkSubtraction(this.findById(accountId), amount);
	}

	private void checkSubtraction(Account account, BigDecimal amount) {
		checkAccount(account);
		checkAmountForSubtraction(account, amount);
	}

	private void checkAmountForSubtraction(Account account, BigDecimal amount) {
		if (account.getBalance()
				.compareTo(amount) <= 0) {
			throw new BusinessException("ACCOUNT INSUFFICIENT");
		}
	}

	private void checkAccount(Account account) {
		if (!account.getStatus()
				.equals(AccountStatus.ACTIVE)) {
			throw new BusinessException("ACCOUNT INACTIVE");
		}
	}
}
