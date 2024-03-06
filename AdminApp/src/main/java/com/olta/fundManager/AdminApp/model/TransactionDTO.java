package com.olta.fundManager.AdminApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private Long transactionId;

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
