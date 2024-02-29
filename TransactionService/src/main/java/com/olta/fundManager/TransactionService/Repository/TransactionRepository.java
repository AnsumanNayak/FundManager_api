package com.olta.fundManager.TransactionService.Repository;


import com.olta.fundManager.TransactionService.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "SELECT t.transaction_id as transactionId," +
            "t.fund_id as fundId," +
            "t.interest_amount as interestAmount," +
            "t.is_interest_amt_paid as isInterestAmtPaid," +
            "t.is_principal_amt_paid as isPrincipalAmtPaid," +
            "t.loan_borrowed as loanBorrowed," +
            "t.loan_returned as loanReturned," +
            "t.member_id as memberId," +
            "t.month_counter as monthCounter," +
            "t.month_year as monthYear," +
            "t.total_loan as totalLoan," +
            "m.member_name as name  FROM Transaction t INNER JOIN Member m ON t.member_id = m.member_id WHERE t.fund_id =?2 and t.month_counter=?1",nativeQuery = true)
    List<TransactionDetails> findByMonthCounterAndFundId(Integer monthCounter, Long fundId);

    /*@Modifying
    @Query("UPDATE Transaction as t SET t.interest_amount = ?4 WHERE t.fund_id = ?1 and t.member_id = ?2 and t.month_counter = ?3")
    void updateInterestAmount(Long fundId, Long memberId, Integer monthCounter, BigDecimal totalInterestAmount);
*/
    @Modifying
    @Query("UPDATE Transaction t SET t.interestAmount = :totalInterestAmount WHERE t.fundId = :fundId AND t.memberId = :memberId AND t.monthCounter = :monthCounter")
    void updateInterestAmount(@Param("fundId") Long fundId, @Param("memberId") Long memberId, @Param("monthCounter") Integer monthCounter, @Param("totalInterestAmount") BigDecimal totalInterestAmount);

}
