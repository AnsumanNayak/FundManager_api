package com.olta.fundManager.TransactionService.Repository;

import java.math.BigDecimal;

public interface TransactionDetails {
    Long getTransactionId();

    Long getFundId();

    Long getMemberId();

    Character getIsPrincipalAmtPaid();

    String getMonthYear();

    Integer getMonthCounter();

    BigDecimal getInterestAmount();

    Character getIsInterestAmtPaid();

    BigDecimal getLoanReturned();

    BigDecimal getLoanBorrowed();

    BigDecimal getTotalLoan();

    String getName();
}
