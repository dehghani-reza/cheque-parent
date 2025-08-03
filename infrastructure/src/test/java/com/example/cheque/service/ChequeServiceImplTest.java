package com.example.cheque.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.domain.account.Account;
import com.example.domain.account.service.AccountService;
import com.example.domain.cheque.Cheque;
import com.example.domain.cheque.repository.ChequeRepository;
import com.example.domain.cheque.service.BounceRecordService;
import com.example.infra.cheque.service.ChequeServiceImpl;
import com.example.infra.client.SayadClient;
import com.example.infra.config.InfraConfig;
import com.example.infra.exception.BusinessException;
import com.example.util.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ChequeServiceImplTest {

	@Mock
	private InfraConfig config;

	@Mock
	private SayadClient sayadClient;

	@Mock
	private AccountService accountService;

	@Mock
	private BounceRecordService bounceRecordService;

	@Mock
	private ChequeRepository chequeRepository;

	@InjectMocks
	private ChequeServiceImpl chequeService;

	private Cheque cheque;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		Account drawer = new Account();
		drawer.setId(1L);
		drawer.setBalance(BigDecimal.valueOf(200000));

		cheque = new Cheque();
		cheque.setDrawer(drawer);
		cheque.setAmount(BigDecimal.valueOf(100000));
		cheque.setNumber("CHQ-2025");
		cheque.setIssueDate(LocalDate.now());
		cheque.setPayeeId("1234567890");

		when(config.getMaxIssueDateMonth()).thenReturn(6);
	}

	@Test
	void shouldThrowException_whenPayeeIdIsInvalid() {
		cheque.setPayeeId("abc"); // not 10 digits

		BusinessException ex = assertThrows(BusinessException.class, () -> {
			chequeService.persist(cheque);
		});

		assertEquals("bad payee id", ex.getMessage());
	}

	@Test
	void shouldThrowException_whenIssueDateIsBeforeToday() {
		cheque.setIssueDate(LocalDate.now()
				.minusDays(1));

		BusinessException ex = assertThrows(BusinessException.class, () -> {
			chequeService.persist(cheque);
		});

		assertEquals("invalid issue date", ex.getMessage());
	}

	@Test
	void shouldAllowNullPayeeId() {
		cheque.setPayeeId(null);
		when(chequeRepository.save(any())).thenReturn(cheque);
		assertThrows(BusinessException.class, () -> {
			chequeService.persist(cheque);
		});
	}
}
