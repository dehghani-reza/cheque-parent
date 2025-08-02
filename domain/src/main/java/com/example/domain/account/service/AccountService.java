package com.example.domain.account.service;

import java.math.BigDecimal;

public interface AccountService {

	boolean eligibleForChequeDrawer(Long accountId);

	void block(Long accountId);

	void subtractMoney(Long accountId, BigDecimal amount);
}
