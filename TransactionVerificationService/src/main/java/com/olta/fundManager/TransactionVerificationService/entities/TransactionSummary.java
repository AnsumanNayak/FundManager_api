package com.olta.fundManager.TransactionService.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "transaction_Summary",uniqueConstraints= @UniqueConstraint(name = "uk_fund_member_month",columnNames={"fund_id", "member_id", "month_year"}))
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

	@Column(name = "fund_id")
    private Long fundId;

	@Column(name = "member_id")
    private Long memberId;

	@Column(name = "is_principalAmt_paid")
    private Character isPrincipalAmtPaid = 'N';

	@Column(name = "month_year")
    private String monthYear;

	@Column(name = "month_counter")
    private Integer monthCounter;

	@Column(name = "interest_amount")
    private Long interestAmount;

    @Column(name = "is_interestAmt_paid")
    private Character isInterestAmtPaid = 'N';

    @Column(name = "loan_returned")
    private Long loanReturned;

    @Column(name = "loan_borrowed")
    private Long loanBorrowed ;

    @Column(name = "total_loan ")
    private Long totalLoan;

}
