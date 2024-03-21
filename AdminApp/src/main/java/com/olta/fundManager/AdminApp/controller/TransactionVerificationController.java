package com.olta.fundManager.AdminApp.controller;

import com.olta.fundManager.AdminApp.model.FundVerificationDTO;
import com.olta.fundManager.AdminApp.service.TransactionVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactionVerifications")
public class TransactionVerificationController {

    @Autowired
    TransactionVerificationService transactionVerificationService;
    @GetMapping("/admins")
    public List<FundVerificationDTO> getFundsWithVerifications(@RequestParam("adminId") Integer adminId){
        return transactionVerificationService.getAllFundsWithVerifications(adminId);
    }
}
