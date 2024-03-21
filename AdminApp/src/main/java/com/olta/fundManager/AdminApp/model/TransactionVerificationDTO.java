package com.olta.fundManager.AdminApp.model;

import lombok.Data;

@Data
public class TransactionVerificationDTO {
    private String verification;

    private String monthYear;

    private Integer monthCounter;
}
