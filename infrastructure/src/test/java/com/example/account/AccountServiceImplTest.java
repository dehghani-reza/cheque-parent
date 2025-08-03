package com.example.account;

import com.example.domain.account.Account;
import com.example.infra.account.AccountServiceImpl;
import com.example.util.TestConfig;
import com.example.util.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class AccountServiceImplTest {

	@Autowired
	AccountServiceImpl accountService;

	@Test
	public void createAccount() {
		Account account = accountService.createAccount(TestUtil.createSampleAccount());
		Assertions.assertThat(accountService.findById(account.getId()))
				.isNotNull();
	}
}