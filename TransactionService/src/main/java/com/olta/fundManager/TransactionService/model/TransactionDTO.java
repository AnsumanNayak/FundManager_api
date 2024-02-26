package com.olta.fundManager.TransactionService.model;

import jakarta.annotation.Nonnull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {

    private Long transactionId;

    private Long fundId;

    private Long memberId;

    private Character isPrincipalAmtPaid = 'N';

    private String monthYear;

    private Integer monthCounter;

    private BigDecimal interestAmount=BigDecimal.ZERO;

    private Character isInterestAmtPaid = 'N';

    private BigDecimal loanReturned=BigDecimal.ZERO;

    private BigDecimal loanBorrowed=BigDecimal.ZERO ;

    private BigDecimal totalLoan=BigDecimal.ZERO;

    @Nonnull
    private BigDecimal monthlyInterestRate;
    @Nonnull
    private Integer tenure;

}
