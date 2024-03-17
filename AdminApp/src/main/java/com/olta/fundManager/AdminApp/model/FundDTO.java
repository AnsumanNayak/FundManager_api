package com.olta.fundManager.AdminApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundDTO {

    private Long fundId;

    private String fundName;

    private BigDecimal totalLoan = BigDecimal.ZERO;

    private BigDecimal monthlyInterestRate = BigDecimal.ZERO;

    private BigDecimal totalInterest = BigDecimal.ZERO;

    private BigDecimal monthlyInstallment = BigDecimal.ZERO;

    private Integer totalPrincipal = 0;

    private Integer tenure = 0;

    private LocalDate effBeginDt = LocalDate.now();

    private LocalDate effEndDt;

    private Integer totalMembers = 0;

    private Integer meetingDay = 1;

    private Integer currentMonthOfInstallment = 1;

    private Integer adminId;

}
