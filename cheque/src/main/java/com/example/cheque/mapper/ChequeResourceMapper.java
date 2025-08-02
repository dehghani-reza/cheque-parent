package com.example.cheque.mapper;

import com.example.cheque.dto.ChequeResponse;
import com.example.cheque.dto.IssueChequeRequest;
import com.example.domain.account.Account;
import com.example.domain.cheque.Cheque;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChequeResourceMapper {

	@Mapping(source = "drawerId", target = "drawer")
	Cheque toCheque(IssueChequeRequest issueChequeRequest);

	default Account mapIdToAccount(Long drawerId) {
		if (drawerId == null) {
			return null;
		}
		Account a = new Account();
		a.setId(drawerId);
		return a;
	}

	@Mapping(source = "drawer.id", target = "drawerId")
	ChequeResponse toResponse(Cheque created);
}
