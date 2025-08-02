package com.example.cheque.web;

import com.example.cheque.dto.ChequeResponse;
import com.example.cheque.dto.IssueChequeRequest;
import com.example.cheque.mapper.ChequeResourceMapper;
import com.example.domain.cheque.Cheque;
import com.example.domain.cheque.service.ChequeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cheques")
public class ChequeResource {

	private final ChequeResourceMapper mapper;

	private final ChequeService service;

	@PostMapping
	public ResponseEntity<ChequeResponse> issue(@RequestBody IssueChequeRequest issueChequeRequest) {
		Cheque created = service.persist(mapper.toCheque(issueChequeRequest));
		ChequeResponse response = mapper.toResponse(created);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{id}/present")
	public ResponseEntity<Boolean> present(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.present(id));
	}
}
