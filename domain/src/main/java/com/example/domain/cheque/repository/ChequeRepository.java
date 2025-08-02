package com.example.domain.cheque.repository;

import com.example.domain.cheque.Cheque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
}
