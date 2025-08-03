package com.example.infra.exception;

public class BusinessException extends RuntimeException {
	public BusinessException(String message) {
		super(message);
	}
}
