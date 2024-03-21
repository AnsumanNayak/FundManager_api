package com.olta.fundManager.AdminApp.model;

import lombok.Data;

import java.util.List;

@Data
public class FundVerificationDTO {

    private Long fundId;

    private String fundName;

    private Integer meetingDay;

    private List<TransactionVerificationDTO> verificationDTOList;
}
