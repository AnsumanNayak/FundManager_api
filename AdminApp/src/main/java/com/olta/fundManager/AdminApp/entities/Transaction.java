package com.olta.fundManager.AdminApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "transaction"/*,uniqueConstraints= @UniqueConstraint(name = "uk_fund_member_month",columnNames={"fund_id", "member_id", "month_year"})*/)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

	@Column(name = "is_principalAmt_paid")
    private Boolean isPrincipalAmtPaid = Boolean.FALSE;

	@Column(name = "month_year")
    private String monthYear;

	@Column(name = "month_counter")
    private Integer monthCounter;

	@Column(name = "interest_amount")
    private BigDecimal interestAmount = BigDecimal.ZERO;

    @Column(name = "is_interestAmt_paid")
    private Boolean isInterestAmtPaid = Boolean.FALSE;

    @Column(name = "loan_returned")
    private BigDecimal loanReturned = BigDecimal.ZERO;

    @Column(name = "loan_borrowed")
    private BigDecimal loanBorrowed = BigDecimal.ZERO;

    @Column(name = "total_loan ")
    private BigDecimal totalLoan = BigDecimal.ZERO;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fund_id")
    private Fund fund;
}
