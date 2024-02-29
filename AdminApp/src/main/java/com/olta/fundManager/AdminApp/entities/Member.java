package com.olta.fundManager.AdminApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "total_loan")
    private BigDecimal totalLoan = BigDecimal.ZERO;

    @Column(name = "total_interest")
    private BigDecimal totalInterest = BigDecimal.ZERO;

    @Column(name = "total_principal")
    private Integer totalPrincipal = 0;

    @Column(name = "eff_begin_dt")
    private LocalDate effBeginDt;

    @Column(name = "eff_end_dt")
    private LocalDate effEndDt;
/*
    @Column(name = "fundId")
    private Long fundId;*/
/*
    @ManyToOne
    @JoinColumn(name = "fund_id",nullable = false)
    private Fund fund;*/
/*
    @ManyToMany
    @JoinTable(
            name = "member_fund",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "fund_id")
    )*/

    @ManyToMany(mappedBy = "members", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Set<Fund> funds = new HashSet<>();

    @Column(name = "member_gender")
    private String gender;

    @Column(name = "member_address")
    private String address;

    @Column(name = "member_dob")
    private LocalDate dob;

    @Column(name = "member_phnNum")
    private Long phnNum;

    @Column(name = "guarantors")
    private String[] guarantors;

    @Column(name = "member_aadharNum")
    private Long aadharNum;
/*
    @Transient
    private int fundTenure;*/

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;


    public void addTransaction(Transaction transaction){
        if(CollectionUtils.isEmpty(transactions)){
            transactions = new HashSet<>();
        }
        transaction.setMember(this);
        transactions.add(transaction);

    }
    public void addFund(Fund fund){
        if(CollectionUtils.isEmpty(funds)){
            funds = new HashSet<>();
        }
        funds.add(fund);
        fund.getMembers().add(this);
    }
}
