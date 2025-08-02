package com.example.cheque.mapper;

import com.example.cheque.dto.ChequeResponse;
import com.example.cheque.dto.IssueChequeRequest;
import com.example.domain.cheque.Cheque;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChequeResourceMapper {

	Cheque toCheque(IssueChequeRequest issueChequeRequest);

	ChequeResponse toResponse(Cheque created);
}
