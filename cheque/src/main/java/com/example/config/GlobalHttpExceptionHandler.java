package com.example.config;

import com.example.exception.CheckBounceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class GlobalHttpExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CheckBounceException.class)
	public ErrorResponse handleBusinessException(CheckBounceException ex) {
		return ErrorResponse.create(ex, HttpStatusCode.valueOf(409), "Cheque bounced.");
	}
}
