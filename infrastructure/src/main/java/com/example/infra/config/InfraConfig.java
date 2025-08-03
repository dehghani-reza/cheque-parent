package com.example.infra.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Getter
@RequiredArgsConstructor
@Configuration
public class InfraConfig {

	private final Environment env;

	public Integer getMaxChequeBounce() {
		return env.getProperty("cheque.bounce.max", Integer.class);
	}

	public Integer getMaxChequeRangeMonth() {
		return env.getProperty("cheque.bounce.range-month", Integer.class);
	}

	public Integer getMaxIssueDateMonth() {
		return env.getProperty("cheque.bounce.issue-date-month", Integer.class);
	}


}
