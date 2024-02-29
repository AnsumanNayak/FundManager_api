package com.olta.fundManager.AdminApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {

    private Long fundId;

    private Long memberId;

    private String memberName;

    private Character isPrincipalAmtPaid = 'N';

    private String monthYear;

    private Integer monthCounter;

    private BigDecimal interestAmount=BigDecimal.ZERO;

    private Character isInterestAmtPaid = 'N';

    private BigDecimal loanReturned=BigDecimal.ZERO;

    private BigDecimal loanBorrowed=BigDecimal.ZERO ;

    private BigDecimal totalLoan=BigDecimal.ZERO;

}
