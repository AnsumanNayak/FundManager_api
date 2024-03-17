package com.olta.fundManager.AdminApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

//    @Column(name = "total_members")
//    private Integer totalMembers = 0;

    @Column(name = "meeting_day")
    private Integer meetingDay = 1;

    @Column(name = "current_month_of_installment")
    private Integer currentMonthOfInstallment = 1;


    @Column(name = "admin_id")
    private Integer adminId;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "member_fund",
            joinColumns = @JoinColumn(name = "fund_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> members = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "fund", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public void addMember(Member member){
        if(CollectionUtils.isEmpty(members)){
            members = new HashSet<>();
        }
        members.add(member);
        member.getFunds().add(this);
    }

    public void addTransaction(Transaction transaction){
        if(CollectionUtils.isEmpty(transactions)){
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
        transaction.setFund(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fund fund)) return false;
        return Objects.equals(getFundId(), fund.getFundId()) && Objects.equals(getFundName(), fund.getFundName()) && Objects.equals(getAdminId(), fund.getAdminId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFundId(), getFundName(), getAdminId());
    }
}
