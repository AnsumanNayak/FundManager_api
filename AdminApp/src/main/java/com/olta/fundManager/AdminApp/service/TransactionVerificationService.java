package com.olta.fundManager.AdminApp.service;




import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.entities.TransactionVerification;
import com.olta.fundManager.AdminApp.model.FundVerificationDTO;
import com.olta.fundManager.AdminApp.model.TransactionDTO;

import java.util.List;

public interface TransactionVerificationService {
    public List<FundVerificationDTO> getAllFundsWithVerifications(Integer adminId);
}
