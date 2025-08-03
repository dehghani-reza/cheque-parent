package com.example.infra.client;

import com.example.domain.cheque.Cheque;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SayadClient {

	public void issue(Cheque cheque) {
		log.info("Issue cheque in SAYAD: {}", cheque);
	}
}
