package com.olta.fundManager.AdminApp.service.Impl;

import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.model.FundVerificationDTO;
import com.olta.fundManager.AdminApp.model.TransactionVerificationDTO;
import com.olta.fundManager.AdminApp.repository.FundRepository;
import com.olta.fundManager.AdminApp.service.TransactionVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionVerificationServiceImpl implements TransactionVerificationService {
    @Autowired
    FundRepository fundRepository;
    @Override
    public List<FundVerificationDTO> getAllFundsWithVerifications(Integer adminId) {
        List<FundVerificationDTO> fundVerificationDTOS = new ArrayList<>();
        List<TransactionVerificationDTO> transactionVerificationDTOS = new ArrayList<>();
        List<Fund> funds = fundRepository.findByAdminId(adminId);
        funds.forEach(fund -> {
            FundVerificationDTO fundVerificationDTO = new FundVerificationDTO();
            fundVerificationDTO.setFundId(fund.getFundId());
            fundVerificationDTO.setFundName(fund.getFundName());
            fundVerificationDTO.setMeetingDay(fund.getMeetingDay());
            fund.getTransactionVerifications().forEach(transactionVerification -> {
                TransactionVerificationDTO transactionVerificationDTO = new TransactionVerificationDTO();
                transactionVerificationDTO.setVerification(transactionVerification.getVerification());
                transactionVerificationDTO.setMonthYear(transactionVerification.getMonthYear());
                transactionVerificationDTO.setMonthCounter(transactionVerification.getMonthCounter());
                transactionVerificationDTOS.add(transactionVerificationDTO);
            });
            fundVerificationDTO.setVerificationDTOList(transactionVerificationDTOS);
            fundVerificationDTOS.add(fundVerificationDTO);
        });
        return fundVerificationDTOS;
    }
}
