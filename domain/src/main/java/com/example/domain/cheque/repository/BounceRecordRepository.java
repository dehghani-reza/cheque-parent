package com.example.domain.cheque.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.domain.cheque.BounceRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BounceRecordRepository extends JpaRepository<BounceRecord, Long> {


	List<BounceRecord> findByCheque_Drawer_IdAndDateAfterAndDateBefore(Long drawerId, LocalDate from, LocalDate to);



}
