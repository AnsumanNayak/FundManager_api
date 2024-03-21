package com.olta.fundManager.AdminApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olta.fundManager.AdminApp.constants.Verification;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "transaction_verification",uniqueConstraints= @UniqueConstraint(name = "uk_fund_month",columnNames={"fund_id",  "month_counter"}))
public class TransactionVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_verification_id")
    private Long transactionVerificationId;

    @Column(name = "month_year")
    private String monthYear;

    @Column(name = "month_counter")
    private Integer monthCounter;

    @Column(name = "rest_amount")
    private BigDecimal restAmount = BigDecimal.ZERO;

    @Column(name="total_principal")
    private BigDecimal totalPrincipal = BigDecimal.ZERO;

    @Column(name="total_interest")
    private BigDecimal totalInterest = BigDecimal.ZERO;

    @Column(name="total_loan")
    private BigDecimal totalLoan = BigDecimal.ZERO;

    @Column(name="total_loan_returned")
    private BigDecimal totalLoanReturned = BigDecimal.ZERO;

    @Column(name="verification")
    private String verification = Verification.NOTSTARTED.getValue();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fund_id")
    private Fund fund;
}
