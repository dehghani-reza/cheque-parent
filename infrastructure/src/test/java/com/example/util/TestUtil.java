package com.example.util;

import java.math.BigDecimal;

import com.example.domain.account.Account;
import com.example.domain.account.AccountStatus;

public class TestUtil {

	public static Account createSampleAccount() {
		Account account = new Account();
		account.setStatus(AccountStatus.ACTIVE);
		account.setBalance(new BigDecimal(1_000_000));
		return account;
	}
}
