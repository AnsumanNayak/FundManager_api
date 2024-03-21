package com.olta.fundManager.AdminApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "eff_begin_dt")
    private LocalDate effBeginDt = LocalDate.now();

    @Column(name = "eff_end_dt")
    private LocalDate effEndDt;

    @JsonIgnore
    @ManyToMany(mappedBy = "members", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Fund> funds = new HashSet<>();

    @Column(name = "member_gender")
    private String gender;


    @Column(name = "member_address")
    private String address;

    @Column(name = "member_dob")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @Column(name = "member_phnNum")
    private Long phnNum;

    @Column(name = "guarantors")
    private String[] guarantors;

    @Column(name = "member_aadharNum")
    private Long aadharNum;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return Objects.equals(getMemberId(), member.getMemberId()) && Objects.equals(getName(), member.getName()) && Objects.equals(getDob(), member.getDob()) && Objects.equals(getAadharNum(), member.getAadharNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId(), getName(), getDob(), getAadharNum());
    }

}
