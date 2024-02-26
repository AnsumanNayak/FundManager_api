package com.olta.fundManager.FundService.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_id")
    private Long fundId;

    @Nonnull
    @Column(name = "fund_name", nullable = false)
    private String fundName;

    @Column(name = "total_loan")
    private BigDecimal totalLoan = BigDecimal.ZERO;

    @Column(name = "monthly_interest_rate")
    private BigDecimal monthlyInterestRate = BigDecimal.ZERO;

    @Column(name = "total_interest")
    private BigDecimal totalInterest = BigDecimal.ZERO;

    @Column(name = "monthly_installment")
    private BigDecimal monthlyInstallment = BigDecimal.ZERO;

    @Column(name = "total_principal")
    private Integer totalPrincipal = 0;

    @Column(name = "tenure")
    private Integer tenure = 0;

    @Column(name = "eff_begin_dt")
    private LocalDate effBeginDt = LocalDate.now();

    @Column(name = "eff_end_dt")
    private LocalDate effEndDt;

    @Column(name = "total_members")
    private Integer totalMembers = 0;

    @Column(name = "meeting_day")
    private Integer meetingDay = 1;

    @Column(name = "current_month_of_installment")
    private Integer currentMonthOfInstallment = 1;

    @Column(name = "admin_id")
    private Integer adminId;

}
