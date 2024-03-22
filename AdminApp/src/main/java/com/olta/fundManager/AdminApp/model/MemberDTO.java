package com.olta.fundManager.AdminApp.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {
    private Long memberId;

    private String name;


    private BigDecimal totalLoan = BigDecimal.ZERO;


    private BigDecimal totalInterest = BigDecimal.ZERO;


    private BigDecimal totalPrincipal = BigDecimal.ZERO;


    private LocalDate effBeginDt = LocalDate.now();


    private LocalDate effEndDt;


    private Long[] fundIds;


    private String gender;


    private String address;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;


    private Long phnNum;


    private String[] guarantors;

    private Long aadharNum;

    private Long fundId;

}
