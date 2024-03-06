package com.olta.fundManager.AdminApp.repository;


import com.olta.fundManager.AdminApp.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByFund_FundIdAndMember_MemberIdAndMonthCounterGreaterThan(Long fundId, Long memberId, Integer monthCounter);
}
