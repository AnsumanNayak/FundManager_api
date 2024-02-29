package com.olta.fundManager.AdminApp.model;


import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MemberDTO {
    private Long memberId;

    private String name;


    private BigDecimal totalLoan = BigDecimal.ZERO;


    private BigDecimal totalInterest = BigDecimal.ZERO;


    private Integer totalPrincipal = 0;


    private LocalDate effBeginDt;


    private LocalDate effEndDt;


    private Long[] fundIds;


    private String gender;


    private String address;


    private LocalDate dob;


    private Long phnNum;


    private String[] guarantors;

    private Long aadharNum;

    private int fundTenure;

}
