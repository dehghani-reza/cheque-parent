package com.example.cheque.service;

import java.time.LocalDate;
import java.util.Objects;

import com.example.client.SayadClient;
import com.example.config.InfraConfig;
import com.example.domain.account.service.AccountService;
import com.example.domain.cheque.BounceRecord;
import com.example.domain.cheque.Cheque;
import com.example.domain.cheque.ChequeStatus;
import com.example.domain.cheque.repository.ChequeRepository;
import com.example.domain.cheque.service.BounceRecordService;
import com.example.domain.cheque.service.ChequeService;
import com.example.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChequeServiceImpl implements ChequeService {

	private final InfraConfig config;

	private final SayadClient sayadClient;

	private final AccountService accountService;

	private final BounceRecordService bounceRecordService;

	private final ChequeRepository chequeRepository;

	@Override
	public Cheque persist(Cheque cheque) {
		validateCheque(cheque);
		cheque.setStatus(ChequeStatus.ISSUED);
		log.debug("going to persist cheque: {}", cheque);
		sayadClient.issue(cheque);
		return chequeRepository.save(cheque);
	}

	@Override
	public Boolean present(Long id) {
		Cheque cheque = chequeRepository.findById(id)
				.orElseThrow(() -> new BusinessException("cheque not present"));
		validatePresentRule(cheque);
		accountService.subtractMoney(cheque.getDrawer().getId(), cheque.getAmount());
		cheque.setStatus(ChequeStatus.PAID);
		chequeRepository.save(cheque);
		return true;
	}

	private void validatePresentRule(Cheque cheque) {
		validateIssueDate(cheque);
		validateAccountBalance(cheque);
	}

	private void validateAccountBalance(Cheque cheque) {
		if (cheque.getAmount()
				.compareTo(cheque.getDrawer()
						.getBalance()) > 0) {
			createBounceRecord(cheque);
			throw new BusinessException("cheque's balance is greater than the drawer's balance");
		}
	}

	private void createBounceRecord(Cheque cheque) {
		bounceRecordService.save(BounceRecord.create(cheque, LocalDate.now(), "INSUFFICIENT MONEY"));
		if (bounceRecordService.isAccountBlockedBecauseOfBounce(cheque.getId())) {
			accountService.block(cheque.getDrawer()
					.getId());
		}
	}

	private void validateIssueDate(Cheque cheque) {
		if (LocalDate.now()
				.isAfter(cheque.getIssueDate()
						.plusMonths(config.getMaxIssueDateMonth()))) {
			throw new BusinessException("cheque date is too old");
		}
	}

	private void validateCheque(Cheque cheque) {
		validateChequeIssueTime(cheque);
		validateChequePayee(cheque);
		validateDrawer(cheque);
	}

	private void validateDrawer(Cheque cheque) {
		if (Objects.isNull(cheque.getDrawer()) || Objects.isNull(cheque.getDrawer()
				.getId()) || !accountService.eligibleForChequeDrawer(cheque.getDrawer()
				.getId())) {
			log.warn("drawer not valid");
			throw new BusinessException("Drawer is blocked");
		}
	}

	private void validateChequePayee(Cheque cheque) {
		if (Objects.nonNull(cheque.getPayeeId()) && cheque.getPayeeId()
				.matches("^([0-9]){10}$")) {
			return;
		}
		log.warn("payee not valid");
		throw new BusinessException("bad payee id");
	}

	private void validateChequeIssueTime(Cheque cheque) {
		if (cheque.getIssueDate()
				.isBefore(LocalDate.now())) {
			log.warn("issue date not valid");
			throw new BusinessException("invalid issue date");
		}
	}

}
