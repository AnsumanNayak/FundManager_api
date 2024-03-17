package com.olta.fundManager.AdminApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private Long transactionId;

    private Long fundId;

    private Long memberId;

    private String name;

    @JsonProperty(value = "isPrincipalAmtPaid")
    private boolean isPrincipalAmtPaid = false;

    private String monthYear;

    private Integer monthCounter;

    private BigDecimal interestAmount=BigDecimal.ZERO;

    @JsonProperty(value = "isInterestAmtPaid")
    private boolean isInterestAmtPaid = false;

    private BigDecimal loanReturned=BigDecimal.ZERO;

    private BigDecimal loanBorrowed=BigDecimal.ZERO ;

    private BigDecimal totalLoan=BigDecimal.ZERO;

    private BigDecimal monthlyInstallment = BigDecimal.ZERO;

}
