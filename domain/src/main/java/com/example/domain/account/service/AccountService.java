package com.example.domain.account.service;

import java.math.BigDecimal;

import com.example.domain.account.Account;

public interface AccountService {

	Account findById(Long accountId);

	void block(Long accountId);

	void subtractMoney(Long accountId, BigDecimal amount);

	void checkSubtraction(Long accountId, BigDecimal amount);
}
