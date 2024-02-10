package com.olta.fundManager.MemberService.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "funds")
    private String[] funds;

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

}
